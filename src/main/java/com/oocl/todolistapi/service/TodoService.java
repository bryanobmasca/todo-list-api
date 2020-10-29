package com.oocl.todolistapi.service;

import com.oocl.todolistapi.exception.TodoNotFoundException;
import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.text.MessageFormat.format;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(Integer id, Todo updatedTodo) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            updatedTodo.setId(id);
            updatedTodo.setText(todo.getText());
            return todoRepository.save(updatedTodo);
        }
        throw new TodoNotFoundException(format("{0} not found", id));
    }

    public void deleteById(Integer id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            throw new TodoNotFoundException(format("{0} not found", id));
        }
        todoRepository.deleteById(id);
    }
}
