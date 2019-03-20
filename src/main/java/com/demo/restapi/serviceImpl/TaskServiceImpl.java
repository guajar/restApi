package com.demo.restapi.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.demo.restapi.model.Task;
import com.demo.restapi.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	private static final AtomicInteger counter = new AtomicInteger();
    static List<Task> tasks = new ArrayList<Task>(
            Arrays.asList(
            		new Task(counter.incrementAndGet(), "Create users in SQL Server", "Description SQL Server", Boolean.TRUE),
            		new Task(counter.incrementAndGet(), "Configure SQL Server Management Studio to connect to SQL Server on Windows", "Description SQL Server Management Studio", Boolean.FALSE),
            		new Task(counter.incrementAndGet(), "Add new ServiceNet routes to a server", "Description ServiceNet", Boolean.FALSE)));


    @Override
    public List<Task> loadAll() {
        return tasks;
    }

    @Override
    public Task findById(int id) {
        for (Task task : tasks){
            if (task.getId() == id){
                return task;
            }
        }
        return null;
    }


    @Override
    public void create(Task task) {
        task.setId(counter.incrementAndGet());
        task.setUuid(UUID.randomUUID().toString());
        task.setInsertDate(new Timestamp(new Date().getTime()));
        if(task.getOwner() == null) {
        	task.setOwner("Default");
        }
        if(task.getCompleted() == null) {
        	task.setCompleted(Boolean.FALSE);
        }
        tasks.add(task);
    }

    @Override
    public void update(Task task) {
        int index = tasks.indexOf(task);
        task.setUpdateDate(new Timestamp(new Date().getTime()));
        if(task.getOwner() == null) {
        	task.setOwner("Default");
        }
        if(task.getCompleted() == null) {
        	task.setCompleted(Boolean.FALSE);
        }
        tasks.set(index, task);
    }

    @Override
    public void delete(int id) {
        Task task = findById(id);
        tasks.remove(task);
    }

    @Override
    public boolean exists(Task task) {
        return findById(task.getId()) != null;
    }

	@Override
	public Task findByTitle(String title) {
		for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(title)){
                return task;
            }
        }
        return null;
	}

}
