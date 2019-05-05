package com.springweb.application.controller;


import com.springweb.application.controller.helper.ProjectHelper;
import com.springweb.application.model.ProjectEntity;
import com.springweb.application.model.ProjectMemberEntity;
import com.springweb.application.repository.ProjectMemberRepo;
import com.springweb.application.repository.ProjectRepo;
import com.springweb.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
        try{
            newProject = projectRepo.save(newProject);
            ProjectMemberEntity newProjectMember = new ProjectMemberEntity();
            newProjectMember.setProject(newProject);
            newProjectMember.setMember(userRepo.findByUsername(owner));
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
            List<ProjectEntity> listProjectAvailable = projectRepo.findProjectEntitiesByOwnerIdAndStatusIdAndType( owner, condData.get("status_id"), condData.get("type"));
            result.put("code", "1000");
            result.put("listProject", listProjectAvailable);
        }catch ( Exception e){
            result.put("code", "1001");
            e.printStackTrace();
        }
        return result;
    }
}
