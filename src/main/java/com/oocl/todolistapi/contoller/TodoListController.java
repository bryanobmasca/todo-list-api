package com.oocl.todolistapi.contoller;

import com.oocl.todolistapi.dto.TodoRequest;
import com.oocl.todolistapi.dto.TodoResponse;
import com.oocl.todolistapi.mapper.TodoMapper;
import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    private TodoService todoService;
    private TodoMapper todoMapper;

    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
        this.todoMapper = new TodoMapper();
    }

    @GetMapping
    public List<TodoResponse> findAll() {
        return todoService.findAll().stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse save(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.save(todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(todo);
    }

    @PutMapping("/{todoId}")
    public TodoResponse update(@PathVariable Integer todoId, @RequestBody TodoRequest updatedTodo) {
        Todo todo = todoService.update(todoId, todoMapper.toEntity(updatedTodo));
        return todoMapper.toResponse(todo);
    }
}
