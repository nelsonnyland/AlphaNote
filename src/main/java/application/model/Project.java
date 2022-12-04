package application.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(nullable = false)
    private String name;
    private List<Integer> notes; // child ids
    private List<String> tags;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getNotes() {
        return notes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(List<Integer> notes) {
        this.notes = notes;
    }

    public void addNoteId(int noteId) {
        if (this.notes != null) {
            this.notes.add(noteId);
        }
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return name;
    }

}
