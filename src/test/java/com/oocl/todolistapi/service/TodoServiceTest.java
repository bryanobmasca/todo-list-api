package com.oocl.todolistapi.service;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TodoServiceTest {
    private TodoRepository todoRepository;

    @BeforeEach
    void setup() {
        todoRepository = Mockito.mock(TodoRepository.class);
    }

    @Test
    public void should_return_todos_when_find_all_todo() {
        //given
        List<Todo> todoList = asList(new Todo(), new Todo());
        when(todoRepository.findAll()).thenReturn(todoList);
        TodoService todoService = new TodoService(todoRepository);
        //when
        List<Todo> actual = todoService.findAll();
        //then
        assertEquals(todoList.size(), actual.size());
    }

    @Test
    public void should_return_todo_when_save_given_one_todo() {
        //given
        Todo todo = new Todo("Create one todo.");
        when(todoRepository.save(todo)).thenReturn(todo);
        TodoService todoService = new TodoService(todoRepository);
        //when
        Todo actual = todoService.save(todo);
        //then
        assertEquals(todo, actual);
    }

    @Test
    public void should_update_done_state_when_update_given_done_is_false() {
        //given
        Todo todo = new Todo(1, "To update done state");
        Todo updatedTodo = new Todo(1, true);
        when(todoRepository.findById(todo.getId())).thenReturn(java.util.Optional.of(todo));
        when(todoRepository.save(updatedTodo)).thenReturn(updatedTodo);
        TodoService todoService = new TodoService(todoRepository);
        //when
        Todo actual = todoService.update(todo.getId(), updatedTodo);
        //then
        assertEquals(true, actual.isDone());
        assertEquals("To update done state", actual.getText());
    }

    @Test
    public void should_delete_todo_when_delete_given_todo_id() {
        //given
        Todo todo = new Todo(1, "Delete this todo.");
        Integer todoId = todo.getId();
        when(todoRepository.findById(todoId)).thenReturn(java.util.Optional.of(todo));
        TodoService todoService = new TodoService(todoRepository);
        //when
        todoService.deleteById(todoId);
        // then
        Mockito.verify(todoRepository, Mockito.times(1)).deleteById(todoId);
    }
}