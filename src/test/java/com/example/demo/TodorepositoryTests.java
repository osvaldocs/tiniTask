package com.example.demo;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TodorepositoryTests {

    private TodoRepository repository;

	@BeforeEach
    void setup() {
        repository = new TodoRepository(); // Esto es para que cada test se cree aislado, por eso una nueva instancia antes de cada test
    }

    @Test
    void addShouldGenerateUniqueIds() {
        Todo t1 = new Todo();
        t1.setTitle("task1");
        t1.setDone(false);

        Todo t2 = new Todo();
        t2.setTitle("task2");
        t2.setDone(false);

        repository.add(t1);
        repository.add(t2);

        assertNotEquals(t1.getId(), t2.getId(), "Ids should be unique");
    }

    @Test
    void findByIdshouldReturnTodoWhenExists() {
        Todo todo = new Todo();
        todo.setTitle("Learn Junit");
        todo.setDone(false);

        repository.add(todo);

        Optional<Todo> found = repository.findById(todo.getId());

        assertTrue(found.isPresent());
        assertEquals("Learn Junit", found.get().getTitle());
    }

    @Test
    void findByIdShouldReturnEmptyWhenNotExists() {
        Optional<Todo> found = repository.findById(999); // ID inexistente
        assertTrue(found.isEmpty());
    }

}
