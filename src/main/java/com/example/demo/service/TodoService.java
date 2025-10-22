package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo create(String title) {
        if (title == null || title.trim().length() < 3) {
            throw new IllegalArgumentException("Title is required and must have at least 3 characters");
        }
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDone(false);
        repository.add(todo);
        return todo;
    }

    public List<Todo> getAll() {
        return repository.getAll();
    }

    public Optional<Todo> toggleDone(int id) {
        Optional<Todo> todoOpt = repository.findById(id);

        if (todoOpt.isPresent())
    }
}
