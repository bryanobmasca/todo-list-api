package com.oocl.todolistapi.service;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TodoServiceTest {
    @Test
    public void should_return_todos_when_find_all_todo() {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        List<Todo> todoList = asList(new Todo(), new Todo());
        when(todoRepository.findAll()).thenReturn(todoList);
        TodoService todoService = new TodoService(todoRepository);
        //when
        List<Todo> actual = todoService.findAll();
        //then
        assertEquals(todoList.size(), actual.size());
    }
}
