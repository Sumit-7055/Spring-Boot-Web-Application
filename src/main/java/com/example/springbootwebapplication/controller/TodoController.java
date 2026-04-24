package com.example.springbootwebapplication.controller;

import com.example.springbootwebapplication.Service.TodoService;
import com.example.springbootwebapplication.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        System.out.println("executing showTodos method");
        String name = (String) model.get("name");
        model.put("todos", todoService.retreveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage() {
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }
        todoService.addTodo((String) model.get("name"), todo.getDesc(), new Date(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";

    }
    @RequestMapping (value = "/update-todo",method = RequestMethod.POST)
    public String showUpdateTodoPage (@RequestParam int id , ModelMap model){
        Todo todo = todoService.retreveTodo(id);
        model.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo,BindingResult result ){
        if (result.hasErrors()) {
            return "todo";
        }
        todo.setUser((String)model.get("name"));
        todoService.updateTodo(todo);
        return "redirect://List-todo";
    }
}
