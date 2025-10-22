package com.example.demo.repository;

import com.example.demo.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository {

    private final List<Todo> todoList = new ArrayList<>();
    private int nextId = 1;

    public List<Todo> getAll() {
       return todoList;
    }

    public void add(Todo todo) {
        todo.setId(nextId);
        nextId++;
        todoList.add(todo);
    }

    public Optional<Todo> findById(int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
       Optional<Todo> todoFound = findById(id);

       if (todoFound.isPresent()) {
           todoList.remove(todoFound.get());
           return true;
       }
       return false;
    }

    public boolean update(int id, String newTitle, boolean newDone) {
        Optional<Todo> todoFound = findById(id);

        if (todoFound.isPresent()) {
            Todo todo = todoFound.get();
            todo.setTitle(newTitle);
            todo.setDone(newDone);

            return true;
        }
        return false;
    }
    
}
