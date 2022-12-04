package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Project;

public interface ProjectDAO extends JpaRepository<Project,Integer>{

}
