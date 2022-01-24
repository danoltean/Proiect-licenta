package com.dan.ticketing.service;

import com.dan.ticketing.models.Issue;
import com.dan.ticketing.repo.IssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepo issueRepo;

    @PostConstruct
    void test() {
        // add mock data
    }

    public void save(Issue issue) {
        issue.setStatus("pending");
        issue.setCreatedAt(Calendar.getInstance().getTime());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        issue.setSubmitter(username);
        issueRepo.save(issue);
    }

    public List<Issue> getAllIssues() {
        List<Issue> issues = issueRepo.findByStatus("pending");
        issues.addAll(issueRepo.findByStatus("in progress"));
        return issues;
    }

    public void delete(Integer id) {
        issueRepo.deleteById(id);
    }

    public Issue getIssueById(Integer id) {
        return issueRepo.findById(id).orElseGet(Issue::new);
    }


    public void updateStatus(Integer id, String status) {
        Issue fromDb = issueRepo.findById(id).get();
        fromDb.setStatus(status);
        if (status.equals("done") && fromDb.getClosedAt() == null || status.equals("canceled") && fromDb.getClosedAt() == null ) {
            fromDb.setClosedAt(Calendar.getInstance().getTime());
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        fromDb.setAssignee(username);
        issueRepo.save(fromDb);
    }

    public  List<Issue> getCompletedIssues() {
        List<Issue> issues = issueRepo.findByStatus("canceled");
        issues.addAll(issueRepo.findByStatus("done"));
        return issues;
    }

}
