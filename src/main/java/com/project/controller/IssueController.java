package com.project.controller;

import com.project.Dto.IssueDto;
import com.project.Model.Issue;
import com.project.Model.User;
import com.project.request.IssueRequest;
import com.project.response.AuthResponse;
import com.project.response.MessageResponse;
import com.project.service.IssueService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;


    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception{
        Issue issue = issueService.getIssueById(issueId);
        return ResponseEntity.ok(issue);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception{
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }


    @GetMapping
    public ResponseEntity<IssueDto> createIssue(@RequestBody IssueRequest issue,
                                    @RequestHeader("Authorization") String token
    ) throws Exception{

        User userToken = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(userToken.getId());

        Issue cratedIssue = issueService.createIssue(issue, userToken);
        IssueDto issueDto = new IssueDto();
        issueDto.setDescription(cratedIssue.getDescription());
        issueDto.setDueDate(cratedIssue.getDueDate());
        issueDto.setId(cratedIssue.getId());
        issueDto.setPriority(cratedIssue.getPriority());
        issueDto.setProject(cratedIssue.getProject());
        issueDto.setProjectId(cratedIssue.getProjectID());
        issueDto.setStatus(cratedIssue.getStatus());
        issueDto.setTitle(cratedIssue.getTitle());
        issueDto.setAssignee(cratedIssue.getAssignee());
        issueDto.setTags(cratedIssue.getTags());

        return ResponseEntity.ok(issueDto);


    }


    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                    @RequestHeader("Authorization") String token) throws Exception{
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId,user.getId());
        MessageResponse response = new MessageResponse();
        response.setMessage("Issue deleted successfully");

        return ResponseEntity.ok(response);
    }



    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId
                                                )throws Exception{
        Issue issue = issueService.addIssueToUser(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId
    )throws Exception{
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }
}
