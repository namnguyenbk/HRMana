package com.springweb.application.controller;

import com.springweb.application.controller.util.ValidateUtil;
import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.TaskEntity;
import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.ProjectRepo;
import com.springweb.application.repository.TaskRepo;
import com.springweb.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping( path = "/task", produces="application/json")
public class TaskController {

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TaskRepo taskRepo;

    @RequestMapping( value = "/addtask", method = RequestMethod.POST)
    public HashMap<String, String> addTask(@RequestBody HashMap<String, String> taskData, Principal principal){
        HashMap<String, String> result = new HashMap<>();
        String projectId = taskData.get("project_id");
        ProjectEntity project = projectRepo.findByProjectId(projectId);
        String taskCode = "T" + String.valueOf(taskRepo.findAll().size());

        String taskName = taskData.get("task_name");
        String author = principal.getName();
        UsersEntity ownerTask = userRepo.findByUsername(author);
        if( ownerTask == null){
            result.put("code", "1001");
            result.put("Not found username", author);
            return result;
        }

        String assigned = taskData.get("assigned");
        UsersEntity assignedUser;
        if( assigned != null){
            assignedUser = userRepo.findByUsername(assigned);
            if( ownerTask == null){
                result.put("code", "1001");
                result.put("Not found username", assigned);
                return result;
            }
        }else {
            assignedUser = ownerTask;
        }

        String description = taskData.get("description");
        String tag = taskData.get("tag");
//        ArrayList<String> listRequiredParam = new ArrayList<>();
//        listRequiredParam.add("task_name");
//        listRequiredParam.add("assigned");
//        listRequiredParam.add("description");
//        listRequiredParam.add("tag");
//        String checkListParam  = ValidateUtil.checkRequiredParam( taskData, listRequiredParam );
        String dueDate = "";
        if( taskData.get("due_date") != null){
            dueDate =  taskData.get("due_date");
        }
        String created = DateFormat.getInstance().format(new Date());

        TaskEntity newTask = new TaskEntity(
                taskCode, taskName, project, "CREATED",
                "", "", created,
                ownerTask, assignedUser, tag, dueDate, description
        );

        try{
            newTask = taskRepo.save( newTask);
            result.put("projectId", projectId);
            result.put("taskId", newTask.getTaskId());
        }catch ( Exception e){
            e.printStackTrace();
            result.put("code", "1002");
            return result;
        }

        result.put("code", "1000");
        return result;
    }
}
