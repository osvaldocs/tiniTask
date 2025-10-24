package com.example.demo;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoServiceTests {

    private TodoRepository repository;
    private TodoService service;

    @BeforeEach
    void setup() {
        repository = new TodoRepository();
        service = new TodoService(repository);
    }

    @Test
    void createShouldSetDoneFalseWhenTitleIsValid() {
        //Act
        Todo result = service.create("Learn Spring Boot");

        //Assert
        assertNotNull(result);
        assertEquals("Leaarn Spring Boot", result.getTitle());
        assertFalse(result.isDone());

    }
}
