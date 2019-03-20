package com.demo.restapi.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.restapi.model.Task;
import com.demo.restapi.service.TaskService;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	// =========================================== Get All Tasks ==========================================

    @RequestMapping(value = "tasks/", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> loadAll() {
        List<Task> tasks = taskService.loadAll();

        if (tasks == null || tasks.isEmpty()){
    
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    // =========================================== Get Task By ID =========================================

    @RequestMapping(value = "tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> get(@PathVariable("id") int id){

        Task task = taskService.findById(id);

        if (task == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    // =========================================== Create New Task ========================================

    @RequestMapping(value = "tasks/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestBody Task task){

        if (taskService.exists(task)){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        taskService.create(task);

        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    // =========================================== Update Existing Task ===================================

    @RequestMapping(value = "tasks/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> update(@PathVariable int id, @RequestBody Task task){
       
        Task currentTask = taskService.findById(id);

        if (currentTask == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        task.setId(currentTask.getId());
        currentTask.setTitle(task.getTitle());
        currentTask.setUpdateDate(new Timestamp(new Date().getTime()));
        currentTask.setCompleted(task.getCompleted());

        taskService.update(task);
        return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
    }

    // =========================================== Delete Task ============================================

    @RequestMapping(value = "tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        Task task = taskService.findById(id);

        if (task == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        taskService.delete(id);
        return new ResponseEntity<List<Task>>(taskService.loadAll(), HttpStatus.OK);
    }
	
}
