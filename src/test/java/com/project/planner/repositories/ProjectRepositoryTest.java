package com.project.planner.repositories;

import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private DataSource dataSource;
     @Autowired private TestEntityManager testEntityManager;
    @Autowired private ProjectRepository projectRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(projectRepository).isNotNull();
    }

    @Test
    public void should_FindAllTasks_When_TasksAttachedToAProject() {

    }

    @Test
    public void should_FindNoTasks_When_TasksAttachedNotToAProject() {

    }


}