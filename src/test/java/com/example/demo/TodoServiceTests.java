package com.example.demo;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void createShouldThrowExceptionWhenTitleIsInvalid() {
        // título vacío
        assertThrows(IllegalArgumentException.class, () -> {
            service.create("");
        });

        // título demasiado corto (<3)
        assertThrows(IllegalArgumentException.class, () -> {
            service.create("ab");
        });

        // título null
        assertThrows(IllegalArgumentException.class, () -> {
            service.create(null);
        });
    }

    @Test
    void toggleDoneShouldInvertDoneStatus() {
        // Arrange: crear un todo
        Todo todo = service.create("Learn Spring Boot");

        // Act: alternar el estado
        Optional<Todo> toggled = service.toggleDone(todo.getId());

        // Assert: el estado se cambió
        assertTrue(toggled.isPresent());
        assertTrue(toggled.get().isDone()); // antes era false, ahora debe ser true

        // Act: alternar otra vez
        toggled = service.toggleDone(todo.getId());

        // Assert: vuelve a false
        assertTrue(toggled.isPresent());
        assertFalse(toggled.get().isDone());
    }

    @Test
    void deleteShouldRemoveTodoWhenExists() {
        // Arrange
        Todo todo = service.create("Learn Spring Boot");
        int id = todo.getId();

        // Act
        boolean deleted = service.delete(id);

        // Assert
        assertTrue(deleted); // confirmamos que delete devolvió true
        assertTrue(service.findById(id).isEmpty()); // confirmamos que ya no existe
    }


    @Test
    void deleteShouldReturnFalseWhenIdNotExists() {
        // Act
        boolean deleted = service.delete(999); // un ID que no está

        // Assert
        assertFalse(deleted); // no se eliminó nada
    }




}
