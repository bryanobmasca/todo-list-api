package com.oocl.todolistapi.mapper;

import com.oocl.todolistapi.dto.TodoRequest;
import com.oocl.todolistapi.dto.TodoResponse;
import com.oocl.todolistapi.model.Todo;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class TodoMapper {
    public TodoResponse toResponse(Todo todo){
        TodoResponse todoResponse = new TodoResponse();
        copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public Todo toEntity(TodoRequest todoRequest){
        Todo todo = new Todo();
        copyProperties(todoRequest, todo);
        return todo;
    }
}
