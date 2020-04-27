package com.ali.bugtracker.repositories;


import com.ali.bugtracker.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Long> {
    public List<Project> findAll();
    public Project findByName(String name);
}
