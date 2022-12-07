package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import application.model.Note;
import application.model.Project;
import application.repository.NoteDAO;

@Service
public class NoteService {
	
	private NoteDAO noteDAO;
	
	public NoteService(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}
	
	public List<Note> getNotesByProject(Project project) {    	 
        List<Note> notes = noteDAO.findAll();
        return notes.stream().
        		filter(e -> e.getProject().getId() == project.getId()).
        		collect(Collectors.toList());
	}
	
	public void saveNote(Note note) {
		noteDAO.save(note);
	}
	
}
