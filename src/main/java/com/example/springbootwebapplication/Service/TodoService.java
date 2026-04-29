package com.example.springbootwebapplication.Service;

import com.example.springbootwebapplication.model.Todo;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "Sumit", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "kumar", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "soni", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retreveTodos(String user) {
        List<Todo> TodoList = new ArrayList<>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                TodoList.add(todo);
            }
        }
        return TodoList;
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));

    }
//    we can not use the below method to delete the todo because we are using for each loop and we can not remove the element from the list while iterating it using for each loop because it will throw ConcurrentModificationException
//    public void DeleteTodo(int id){
//        for(Todo todo : todos){
//            if(todo.getId()==id){
//                todos.remove(todo);
//            }
//        }
//    }


    //insted we can use this:
    public void deleteTodo(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

//    or we can use this:
    public void deleteTodoByIterator(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Todo retreveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public void updateTodo(@Valid Todo todo) {
        todos.remove(todo);
        todos.add(todo);
    }
}
