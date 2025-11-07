package com.project.service;

import com.project.Model.Issue;
import com.project.Model.User;
import com.project.request.IssueRequest;

import java.util.List;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;


    void deleteIssue(Long issueId, Long userId)throws Exception;

    Issue addIssueToUser(Long issueId,Long userId)throws Exception;

    Issue  updateStatus(Long issueId, String status)throws Exception;
}
