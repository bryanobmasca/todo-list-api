package com.oocl.todolistapi.integration;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoServiceIntegration {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    public void should_return_todos_when_find_all_todo() throws Exception {
        //given
        Todo todo = new Todo("New todo");
        Todo savedTodo = todoRepository.save(todo);
        //when then
        mockMvc.perform(get("/todolist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedTodo.getId()))
                .andExpect(jsonPath("$[0].text").value(savedTodo.getText()))
                .andExpect(jsonPath("$[0].done").value(savedTodo.isDone()));
    }

    @Test
    public void should_save_todo_when_save_given_one_todo() throws Exception {
        //given
        String stringAsJson = "{\n" +
                "    \"text\" : \"Add this todo.\"\n" +
                "}";
        //when then
        mockMvc.perform(post("/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("Add this todo."))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    public void should_update_done_state_when_update_given_todo_id() throws Exception {
        //given
        Todo todo = new Todo("Update the done state");
        Integer todoId = todoRepository.save(todo).getId();
        String todoText = todoRepository.save(todo).getText();

        String stringAsJson = "{\n" +
                "    \"done\" : true\n" +
                "}";
        //when then
        mockMvc.perform(put("/todolist/" + todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(todoId))
                .andExpect(jsonPath("$.text").value(todoText))
                .andExpect(jsonPath("$.done").value(true));
    }
}
