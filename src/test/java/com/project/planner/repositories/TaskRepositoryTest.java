package com.project.planner.repositories;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired private DataSource dataSource;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private TaskRepository taskRepository;
}