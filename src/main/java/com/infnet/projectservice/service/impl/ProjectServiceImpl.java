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

        if (!projectRepository.existsById(id)) {
            throw new Exception("Projeto não encontrado, tente outro ID.");
        }
        project.setId(id);
        projectRepository.save(project);

        return projectRepository.findById(id);
    }

    @Override
    public Optional<Project> delete(Long id) {
        Optional<Project> deletedProject = projectRepository.findById(id);
        projectRepository.deleteById(id);
        return deletedProject;
    }
}
