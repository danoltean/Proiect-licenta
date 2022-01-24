package com.dan.ticketing.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "machines")

public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idmachine;
    String section;
    String name;

    @OneToMany(
            mappedBy = "machine",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Issue> issues = new ArrayList<>();

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Machine() {
    }

    public Machine(int idmachine, String name, String section) {
        this.idmachine = idmachine;
        this.name = name;
        this.section = section;
    }

    public Integer getIdmachine() {
        return idmachine;
    }

    public void setIdmachine(Integer idmachine) {
        this.idmachine = idmachine;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
