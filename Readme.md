# Spring Boot Rest API Example Project

This is a sample Java / Maven / Srping Boot (version 2.1.3.RELEASE)

## How to Run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x
* Once successfully built, you can run the service:
	- mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
	
	
### LIST ALL TASKS

	GET http://localhost:8090/api/v1/tasks/
	
	Response: HTTP 200

### Create Task
	
	POST http://localhost:8090/api/v1/tasks/
	
	Content-Type: application/json
	{
	 	"title": "Task Infinite",
    	"description": "Description Task Infinite",
    	"completed": true,
    	"owner": "Jamal"
	}
	
	Response: HTTP 201 (CREATED)
	
### Update Task

	PUT http://localhost:8090/api/v1/tasks/{id}
	
	Content-Type: application/json
	{
	   	"title": "Task Infinite",
    	"description": "Description Task Infinite",
    	"completed": true,
    	"owner": "Jamal"
	}
	Response: HTTP 200 (OK) OR HTTP 404 (NOT FOUND)
	
### Delete Task

	DELETE http://localhost:8090/api/v1/tasks/{id}
	
	Response: HTTP 200 (OK) OR HTTP 404 (NOT FOUND)