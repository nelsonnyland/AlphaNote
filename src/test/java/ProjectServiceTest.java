import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import application.model.Project;
import application.repository.ProjectDAO;
import application.service.ProjectService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProjectServiceTest {

	
	@InjectMocks
	private ProjectService projectService;
	
	@Mock
	private ProjectDAO projectDAO;
	
	private Project p1;
	
	@BeforeAll	
	public void setUp() {
		p1 = new Project();
		p1.setId(1);
		p1.setName("Test project 1");
	}
	
	@Test
	public void testGetAllProjects() {

		
		Project p2 = new Project();
		p2.setId(2);
		p2.setName("Test project 2");
		
		Project p3 = new Project();
		p3.setId(3);
		p3.setName("Test project 3");
		
		List<Project> projects = new ArrayList<>();
		projects.add(p1);
		projects.add(p2);
		projects.add(p3);
		
		Mockito.when(projectDAO.findAll()).thenReturn(projects);
		
		List<Project> projects1 = projectService.getAllProjects();
		assertEquals(projects1.size(), 3);
		verify(projectDAO, times(1)).findAll();
	}
	
	@Test
	public void testSaveProject() {
			
		projectService.saveProject(p1);		
		verify(projectDAO, times(1)).save(p1);		
	}
	
	@Test
	public void testDeleteProject() {
				
		projectService.deleteProject(p1);		
		verify(projectDAO, times(1)).delete(p1);
	}
	
}
