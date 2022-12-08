package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Note;

/**
 * NoteDAO is for extending the Note object to the database.
 *
 * @author Mario Vidal
 */
public interface NoteDAO extends JpaRepository<Note,Integer>{

}
