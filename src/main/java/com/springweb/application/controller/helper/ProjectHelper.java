package com.springweb.application.controller.helper;

import com.springweb.application.repository.ProjectMemberRepo;
import com.springweb.application.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ProjectHelper {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ProjectMemberRepo projectMemberRepo;

    public Boolean hasPermisson( String memberId){

        return true;
    }

}
