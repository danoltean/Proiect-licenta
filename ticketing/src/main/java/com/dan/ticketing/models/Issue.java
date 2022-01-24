package com.dan.ticketing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")

public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idissue;
    String summary;
    String description;
    String status;
    String severity;
    Date createdAt;
    Date closedAt;
    String submitter;
    String assignee;


    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine")
    private Machine machine;

    public Issue() {
    }

    public Integer getIdissue() {
        return idissue;
    }

    public void setIdissue(Integer idissue) {
        this.idissue = idissue;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Issue(int idissue, String summary, String description, String status, String severity) {
        this.idissue = idissue;
        this.summary = summary;
        this.description = description;
        this.status = status;
        this.severity = severity;
    }
}
