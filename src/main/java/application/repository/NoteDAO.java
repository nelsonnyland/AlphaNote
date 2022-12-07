package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Note;

public interface NoteDAO extends JpaRepository<Note,Integer>{

}
