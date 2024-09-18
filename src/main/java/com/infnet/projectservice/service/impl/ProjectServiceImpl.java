package com.infnet.projectservice.service.impl;

import com.infnet.projectservice.model.Project;
import com.infnet.projectservice.repository.ProjectRepository;
import com.infnet.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public Optional<Project> update(Long id, Project project) throws Exception {
        Optional<Project> existingProjectOpt = projectRepository.findById(id);
        if (existingProjectOpt.isEmpty()) {
            throw new Exception("Projeto não encontrado, tente outro ID.");
        }
        Project existingProject = existingProjectOpt.get();

        if (project.getName() != null) {
            existingProject.setName(project.getName());
        }
        if (project.getDescription() != null) {
            existingProject.setDescription(project.getDescription());
        }
        if (project.getActive() != null) {
            existingProject.setActive(project.getActive());
        }
        if (project.getTotalCost() != null) {
            existingProject.setTotalCost(project.getTotalCost());
        }
        if (project.getEstimatedHours() != null) {
            existingProject.setEstimatedHours(project.getEstimatedHours());
        }
        if (project.getBudget() != null) {
            existingProject.setBudget(project.getBudget());
        }
        if (project.getAddress() != null) {
            existingProject.setAddress(project.getAddress());
        }

        projectRepository.save(existingProject);

        return Optional.of(existingProject);
    }

    @Override
    public Optional<Project> delete(Long id) {
        Optional<Project> deletedProject = projectRepository.findById(id);
        projectRepository.deleteById(id);
        return deletedProject;
    }
}
