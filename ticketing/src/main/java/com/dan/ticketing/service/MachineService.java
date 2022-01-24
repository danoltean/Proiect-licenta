package com.dan.ticketing.service;

import com.dan.ticketing.models.Machine;
import com.dan.ticketing.models.User;
import com.dan.ticketing.repo.MachineRepo;
import com.dan.ticketing.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MachineService {
    @Autowired
    private MachineRepo machineRepo;

    @PostConstruct
    void test (){
        // add mock data
    }

    public void save(Machine machine) { machineRepo.save(machine); }

    public List<Machine> getAllMachines(){ return machineRepo.findAll(); }

    public void delete(Integer id) {
        machineRepo.deleteById(id);
    }

}
