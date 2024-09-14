package com.infnet.projectservice.service;

import com.infnet.projectservice.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project create(Project project);

    List<Project> findAll();

    Optional<Project> findById(Long id);

    List<Project> findByName(String name);

    Optional<Project> update(Long id, Project project) throws Exception;

    Optional<Project> delete(Long id);
}
