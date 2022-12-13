package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Project;

/**
 * Project DAO is for extending the Project object to the database.
 *
 * @author Mario Vidal
 */
public interface ProjectDAO extends JpaRepository<Project,Integer>{

}
