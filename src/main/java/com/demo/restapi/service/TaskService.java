package com.demo.restapi.service;

import java.util.List;

import com.demo.restapi.model.Task;


public interface TaskService {
	
	List<Task> loadAll();

	Task findById(int id);

	Task findByTitle(String title);

    void create(Task task);

    void update(Task task);

    void delete(int id);

    boolean exists(Task task);

}
