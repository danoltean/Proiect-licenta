package com.dan.ticketing.repo;

import com.dan.ticketing.models.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepo extends JpaRepository<Machine, Integer> {
}

