package com.infnet.projectservice.repository;

import com.infnet.projectservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT u FROM Project u WHERE u.name = :name")
    List<Project> findByName(@Param("name") String name);
}
