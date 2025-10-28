package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5500") // habilita CORS para el fronted local
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // GET /api/todos
    @GetMapping
    public List<Todo> getAll() {
        return service.getAll();
    }

    // POST /api/todos
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        try {
            String title = body.get("title");
            Todo created = service.create(title);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/todos/{id}/toggle
    @PutMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable int id) {
        Optional<Todo> toggled = service.toggleDone(id);
        if (toggled.isPresent()) {
            return ResponseEntity.ok(toggled.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Not found"));
        }
    }

    // DELETE /api/todos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Not found"));
        }
    }
}
