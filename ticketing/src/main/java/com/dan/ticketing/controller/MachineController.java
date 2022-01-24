package com.dan.ticketing.controller;

import com.dan.ticketing.models.Machine;
import com.dan.ticketing.models.User;
import com.dan.ticketing.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping("/addMachine")
    public String addMachine(Model model) {
        model.addAttribute("machine", new Machine());
        return "addMachine";
    }

    @PostMapping("/addMachine")
    public String postAddMachine(@ModelAttribute("machine") Machine machine, Model model) {
        machineService.save(machine);
        return "redirect:/addMachine";
    }

    @GetMapping("/viewMachines")
    public String viewMachines(Model model) {
        model.addAttribute("machines", machineService.getAllMachines());
        return "viewMachines";
    }

    @GetMapping("/deleteMachine/{id}")
    public String deleteMachine(@PathVariable("id") String id){
        machineService.delete(Integer.valueOf(id));
        return "redirect:/viewMachines";
    }
}
