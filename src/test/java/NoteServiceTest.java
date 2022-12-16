
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import application.model.Note;
import application.model.Project;
import application.repository.NoteDAO;
import application.service.NoteService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class NoteServiceTest {
	
	@InjectMocks
	private NoteService noteService;
	
	@Mock
	private NoteDAO noteDAO;
	
	private Project p;
	
	private Note n1;
	
	
	@BeforeAll	
	public void setUp() {
		p = new Project();
		p.setId(1);
		p.setName("Test project");
		
		n1 = new Note();
		n1.setProject(p);
		n1.setName("Note 1");
		n1.setContent("Content 1");		
	}

	@Test
	public void testGetNotesByProject() {
		
		Note n2 = new Note();
		n2.setProject(p);
		n2.setName("Note 2");
		n2.setContent("Content 2");
		
		Note n3 = new Note();
		n3.setProject(p);
		n3.setName("Note 3");
		n3.setContent("Content 3");
		
		List<Note> notes = new ArrayList<>();
		notes.add(n1);
		notes.add(n2);
		notes.add(n3);
		
		Mockito.when(noteDAO.findAll()).thenReturn(notes);
						
		List<Note> notes1 = noteService.getNotesByProject(p);
		assertEquals(notes1.size(), 3);
		verify(noteDAO, times(1)).findAll();
	}
	
	@Test
	public void testSaveNote() {
			
		noteService.saveNote(n1);		
		verify(noteDAO, times(1)).save(n1);		
	}
	
	@Test
	public void testDeleteNote() {
				
		noteService.deleteNote(n1);		
		verify(noteDAO, times(1)).delete(n1);
	}
	
	
	
}
