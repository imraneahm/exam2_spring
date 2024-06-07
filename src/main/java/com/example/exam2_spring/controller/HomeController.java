package com.example.exam2_spring.controller;

import com.example.exam2_spring.entities.Employe;
import com.example.exam2_spring.entities.Project;
import com.example.exam2_spring.repository.EmployeRepository;
import com.example.exam2_spring.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/assign")
    public String showAssignForm(Model model) {
        List<Employe> employes = employeRepository.findAll();
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("employes", employes);
        model.addAttribute("projects", projects);
        return "assign";
    }

    @PostMapping("/assign")
    public String assignProject(@RequestParam("employeId") Long employeId, @RequestParam("projectId") Long projectId, Model model) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);

        if (employe != null && project != null) {
            employe.getProjects().add(project);
            employeRepository.save(employe);
        }
        return "redirect:/assign";
    }

    @GetMapping("/employe-list")
    public String showEmployeList(Model model) {
        model.addAttribute("employes", employeRepository.findAll());
        return "employe-list";
    }

    @PostMapping("/remove-employe")
    public String removeEmploye(@RequestParam("employeId") Long employeId) {
        employeRepository.deleteById(employeId);
        return "redirect:/employe-list";
    }
}
