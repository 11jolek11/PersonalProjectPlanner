package com.project.planner.services;

import com.project.planner.configs.StorageConfig;
import com.project.planner.exceptions.LocalFileNotFoundException;
import com.project.planner.exceptions.PathCorrupted;
import com.project.planner.exceptions.StorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class LocalFileSystemService implements StorageService{

    private final Path root;

    public LocalFileSystemService(StorageConfig storageConfig) {
        String newRoot = storageConfig.getStringRootPath();

        if (newRoot.trim().isEmpty()) {
            throw new PathCorrupted(newRoot + " is corrupted");
        }

        this.root = Path.of(newRoot);
    }

    @Override
    public void initialize() {
        try {
            Files.createDirectories(this.root);
        } catch (IOException exception) {
            throw new StorageException("Couldn't create a root directory", exception);
        }
    }

    @Override
    public void store(MultipartFile multipartFile) {
        // FIXME(11jolek11): What to do when file already exists?
        try {
            if (multipartFile.isEmpty()) {
                throw new StorageException("Can't upload empty file");
            }

            Path fileDestination = this.root.resolve(
                    Path.of(Objects
                            .requireNonNull(
                                    multipartFile.getOriginalFilename()
                            ))).normalize().toAbsolutePath();
            if (!fileDestination.getParent().equals(this.root.toAbsolutePath())) {
                throw new StorageException("Destination path outside of root directory");
            }

            try (InputStream inputStream = multipartFile.getInputStream()){
                Files.copy(inputStream, fileDestination, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException exception) {
            throw new StorageException("Error when uploading a file!", exception);
        }
    }

    @Override
    public Stream<Path> loadAllFilesPath() {
        try {
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);

        } catch (IOException e) {
            throw new StorageException("Error when traversing a directory", e);
        }
    }

    @Override
    public Path loadFileByName(String filename) {
        return this.root.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = loadFileByName(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new LocalFileNotFoundException("Can't read a " + file);
            }

        } catch (MalformedURLException exception) {
            throw new LocalFileNotFoundException("Couldn't read a " + filename, exception);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.root.toFile());
    }

    @Override
    public void deleteFile(String filename) {

    }
}
