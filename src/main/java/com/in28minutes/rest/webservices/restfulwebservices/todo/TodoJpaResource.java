package com.in28minutes.rest.webservices.restfulwebservices.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoJpaResource {

    private final TodoRepository repository;

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return repository.findByUsername(username);
    }

    @GetMapping("/todos/{id}")
    public Todo retrieveTodo(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        if(repository.findById(id).isPresent())
            repository.save(todo);

        return todo;
    }

    @PostMapping("/users/{username}/todos/add")
    public Todo createTodo(@RequestBody Todo todo, @PathVariable String username) {
        todo.setUsername(username);
        return repository.save(todo);
    }
}
