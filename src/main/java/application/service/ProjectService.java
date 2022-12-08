package application.service;

import application.model.Project;
import application.repository.ProjectDAO;
import org.springframework.stereotype.Service;

/**
 * ProjectService is for handling project persistence to the database.
 *
 * @author Nelson Nyland
 */
@Service
public class ProjectService {

	private ProjectDAO projectDAO;

	/**
	 * ProjectService instantiates the ProjectService class.
	 *
	 * @author Nelson Nyland
	 * @param projectDAO
	 */
	public ProjectService(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

    /**
     * deleteProject is for deleting projects in the database.
	 *
	 * @author Nelson Nyland
	 * @param project
	 */
	public void deleteProject(Project project) {
		projectDAO.delete(project);
	}

}
