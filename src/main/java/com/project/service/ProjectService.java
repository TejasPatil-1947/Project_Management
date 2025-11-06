package com.project.service;

import com.project.Model.Chat;
import com.project.Model.Project;
import com.project.Model.User;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project,User user) throws Exception;

    List<Project> getProjectByTeam(User user,String category,String tag) throws Exception;

    Project getProjectById(Long projectId) throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updateProject(Project updatedProject, Long id) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;

    void removerUserFromProject(Long projectId, Long userId) throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project> searchProjects(String keyword,User user) throws Exception;

}
