package com.dan.ticketing.controller;

import com.dan.ticketing.models.Issue;
import com.dan.ticketing.service.IssueService;
import com.dan.ticketing.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IssueController {

    @Autowired
    private IssueService issueService;
    @Autowired
    private MachineService machineService;

    @GetMapping("/addIssue")
    public String addIssue(Model model) {
        model.addAttribute("issue", new Issue());
        model.addAttribute("availableseverities", Arrays.asList("low", "medium", "high"));
        model.addAttribute("availablestatus", Arrays.asList("pending", "in progress", "done", "canceled"));
        model.addAttribute("machines", machineService.getAllMachines());
        return "addIssue";
    }

    @PostMapping("/addIssue")
    public String postAddMachine(@ModelAttribute("issue") Issue issue, Model model) {
        issueService.save(issue);
        return "redirect:/addIssue";
    }

    @GetMapping("/viewIssues")
    public String viewIssues(Model model) {
        model.addAttribute("issues", issueService.getAllIssues());
        return "viewIssues";
    }

    @GetMapping("/completedIssues")
    public String completedIssues(Model model) {
        model.addAttribute("issues", issueService.getCompletedIssues());
        return "completedIssues";
    }

    @GetMapping("/viewIssueDetails/{id}")
    public String viewIssueDetails(@PathVariable("id") Integer id, Model model) {
        Issue iss = issueService.getIssueById(id);
        model.addAttribute("issue", iss);
        model.addAttribute("mockIssue", issueService.getIssueById(id));
        List<String> statuses = new ArrayList<>();
        statuses.add("done");
        statuses.add("canceled");
        if (iss.getStatus().equals("pending")) {
            statuses.add("pending");
            statuses.add("in progress");
        } else if (iss.getStatus().equals("in progress")) {
            statuses.add("in progress");
        }
        model.addAttribute("statuses", statuses);
        model.addAttribute("status", "");
        return "viewIssueDetails";
    }

    @PostMapping("/changeStatus/{id}")
    public String changeStatus(Model model, @ModelAttribute("mockIssue") Issue status, @PathVariable("id") Integer id) {
        issueService.updateStatus(id, status.getStatus());
        return "redirect:/viewIssueDetails/" + id;
    }

    @GetMapping("/deleteIssue/{id}")
    public String deleteIssue(@PathVariable("id") String id) {
        issueService.delete(Integer.valueOf(id));
        return "redirect:/viewIssues";
    }
}
