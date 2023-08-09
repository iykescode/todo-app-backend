package com.in28minutes.rest.webservices.restfulwebservices.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequiredArgsConstructor
public class TodoResource {

    private final TodoService todoService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoService.findByUsername(username);
    }

    @GetMapping("/todos/{id}")
    public Todo retrieveTodo(@PathVariable Integer id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping("/todos/add")
    public Todo updateTodo(@RequestBody Todo todo) {
        Todo addedTodo = todoService.addTodo("iykescode", todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return addedTodo;
    }
}
