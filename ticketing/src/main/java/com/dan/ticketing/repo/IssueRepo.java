package com.dan.ticketing.repo;

import com.dan.ticketing.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Integer> {
    List<Issue> findByStatus(String done);
}

