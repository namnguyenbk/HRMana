package com.springweb.application.controller;


import com.springweb.application.controller.helper.ProjectHelper;
import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.ProjectMemberEntity;
import com.springweb.application.model.TaskEntity;
import com.springweb.application.model.UsersEntity;
import com.springweb.application.repository.ProjectMemberRepo;
import com.springweb.application.repository.ProjectRepo;
import com.springweb.application.repository.TaskRepo;
import com.springweb.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@RestController
@RequestMapping(path="/project", produces="application/json")
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ProjectMemberRepo projectMemberRepo;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private ProjectHelper projectHelper;

    @Autowired
    private TaskRepo taskRepo;

    @RequestMapping(value = "/addpro", method = RequestMethod.POST)
    public HashMap<String, String> addProject(@RequestBody HashMap<String, String> projectData, Principal principal){
        ProjectEntity newProject = new ProjectEntity();
        HashMap<String, String> result = new HashMap<>();
        newProject.setProjectName(projectData.get("project_name"));
        if( principal.getName() == null){
            result.put("code", "998");
            return result;
        }
        String owner = principal.getName();
        newProject.setOwnerId(owner);
        newProject.setStatusId("CREATED");
        newProject.setCreated(DateFormat.getInstance().format( new Date()));
        newProject.setType(projectData.get("type"));
        newProject.setTheme(projectData.get("theme"));
        newProject.setDescription(projectData.get("description"));
        newProject.setListMember(owner);
        try{
            newProject = projectRepo.save(newProject);
            ProjectMemberEntity newProjectMember = new ProjectMemberEntity();
            newProjectMember.setProject(newProject);
            newProjectMember.setMember(owner);
            newProjectMember.setMemRoleId("AUTHOR");
            projectMemberRepo.save(newProjectMember);
            result.put("projectId", newProject.getProjectId());
            result.put("code", "1000");
        }catch ( Exception e){
            e.printStackTrace();
            result.put("code", "1002");
        }
        return result;
    }

    @RequestMapping( value = "/listpro", method = RequestMethod.POST)
    public HashMap<String, Object> listProject( @RequestBody HashMap<String, String> condData, Principal principal){
        HashMap<String, Object> result = new HashMap<>();
        try{
            // Neu co yeu cau lay danh sach cua 1 user la owner thi lay danh sach du an cua owner do, con null thi lay cua user  dang dang nhap
            String owner = condData.get("owner");
            if ( owner == null){
                owner = principal.getName();
            }

//            List<ProjectEntity> listProjectAvailable = projectRepo.findProjectEntitiesByOwnerIdAndStatusIdAndType( owner, condData.get("status_id"), condData.get("type"));
            List<ProjectMemberEntity> listProjectAvailable = projectMemberRepo.findProjectMemberEntitiesByMember(owner);
            result.put("code", "1000");
            result.put("listProject", listProjectAvailable);
        }catch ( Exception e){
            result.put("code", "1001");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping( value = "/detailPro", method = RequestMethod.POST)
    public HashMap<String, Object> detailProject( @RequestBody HashMap<String, String> condData){
        HashMap<String, Object> result = new HashMap<>();
        try{
            // Neu co yeu cau lay danh sach cua 1 user la owner thi lay danh sach du an cua owner do, con null thi lay cua user  dang dang nhap
            String owner = condData.get("projectId");
            if(owner == null){
                result.put("code", "999");
                result.put("param", "projectId");
                return result;
            }
            ProjectEntity project = projectRepo.findByProjectId( owner);
//            project.setListTasks(project.getListTasks());
            result.put("code", "1000");
            result.put("listTask", taskRepo.findTaskEntitiesByProject(project.getProjectId()));
            result.put("project", project);
        }catch ( Exception e){
            result.put("code", "1001");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping( value = "/addMember", method = RequestMethod.POST)
    public HashMap<String, Object> listMember( @RequestBody HashMap<String, String> addMemData){
        HashMap<String, Object> result = new HashMap<>();
        try{
            String id = addMemData.get("projectId");
            ProjectEntity projectEntity = projectRepo.findByProjectId(id);
            UsersEntity mem = userRepo.findByUsername(addMemData.get("memberId"));
            if( mem == null){
                result.put("code", "1111");
                return result;
            }
            String members = projectEntity.getListMember() + "," + addMemData.get("memberId");
            projectEntity.setListMember(members);
            projectRepo.save(projectEntity);

            ProjectMemberEntity newProjectMember = new ProjectMemberEntity();
            newProjectMember.setProject(projectEntity);
            newProjectMember.setMember(addMemData.get("memberId"));
            newProjectMember.setMemRoleId("MEMBER");
            projectMemberRepo.save(newProjectMember);

            result.put("code", "1000");
            result.put("listMember", members);
        }catch ( Exception e){
            result.put("code", "1001");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping( value = "/deleteProject", method = RequestMethod.POST)
    public HashMap<String, Object> deleteProject( @RequestBody HashMap<String, String> req){
        HashMap<String, Object> result = new HashMap<>();
        try{
            String id = req.get("projectId");
            projectMemberRepo.deleteAllByProject(projectRepo.findByProjectId(id));
            projectRepo.deleteById(id);
            result.put("code", "1000");
        }catch ( Exception e){
            result.put("code", "1001");
            e.printStackTrace();
        }
        return result;
    }

//    @RequestMapping( value = "/deleteTask", method = RequestMethod.POST)
//    public HashMap<String, Object> deleteTask( @RequestBody HashMap<String, String> req){
//        HashMap<String, Object> result = new HashMap<>();
//        try{
//            String id = req.get("taskId");
//            projectRepo.de
//            taskRepo.deleteById(id);
//            result.put("code", "1000");
//        }catch ( Exception e){
//            result.put("code", "1001");
//            e.printStackTrace();
//        }
//        return result;
//    }

    @RequestMapping( value = "/updateTask", method = RequestMethod.POST)
    public HashMap<String, Object> updateTask( @RequestBody HashMap<String, String> req){
        HashMap<String, Object> result = new HashMap<>();
        try{
            String id = req.get("taskId");
            String status = req.get("statusId");
            TaskEntity task = taskRepo.findByTaskId(id);
            task.setStatusId(status);
            taskRepo.save(task);
            result.put("code", "1000");
        }catch ( Exception e){
            result.put("code", "1001");
            e.printStackTrace();
        }
        return result;
    }
}
