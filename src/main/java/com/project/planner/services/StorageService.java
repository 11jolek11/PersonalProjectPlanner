//package com.project.planner.services;
//
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Path;
//import java.util.stream.Stream;
//
//@Service
//public interface StorageService {
//    void initialize();
//    void store(MultipartFile multipartFile);
//    Stream<Path> loadAllFilesPath();
//    Path loadFileByName(String filename);
//    Resource loadAsResource(String filename);
//
//    Boolean exists(String filename);
//
//    void deleteAll();
//    void deleteFile(String filename);
//}
