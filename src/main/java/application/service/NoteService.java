package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import application.model.Note;
import application.model.Project;
import application.repository.NoteDAO;

/**
 * NoteService is for handling note persistence to the database.
 *
 * @author Mario Vidal
 * @author Nelson Nyland
 */
@Service
public class NoteService {
	
	private NoteDAO noteDAO;

	/**
	 * NoteService instantiates the NoteService class.
	 *
	 * @author Mario Vidal
	 * @param noteDAO
	 */
	public NoteService(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}

	/**
	 * getNotesByProject is for querying the database for notes by project.
	 *
	 * @author Mario Vidal
	 * @param project
	 * @return
	 */
	public List<Note> getNotesByProject(Project project) {    	 
        List<Note> notes = noteDAO.findAll();
        return notes.stream().
        		filter(e -> e.getProject().getId() == project.getId()).
        		collect(Collectors.toList());
	}

	/**
	 * saveNote is for saving notes to the database.
	 *
	 * @author Mario Vidal
	 * @param note
	 */
	public void saveNote(Note note) {
		noteDAO.save(note);
	}

	/**
	 * deleteNote is for deleting notes in the database.
	 *
	 * @author Nelson Nyland
	 * @param note
	 */
	public void deleteNote(Note note) {
		noteDAO.delete(note);
	}

}
