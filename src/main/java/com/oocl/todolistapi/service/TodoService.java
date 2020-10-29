package com.oocl.todolistapi.service;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (todo != null){
            updatedTodo.setId(id);
            updatedTodo.setText(todo.getText());
            return todoRepository.save(updatedTodo);
        }
        return null;
    }

    public void deleteById(Integer id) {
        todoRepository.deleteById(id);
    }
}
