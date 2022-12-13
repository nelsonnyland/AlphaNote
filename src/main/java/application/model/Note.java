package application.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Note is an object for storing note state and behavior.
 *
 * @author Nelson Nyland
 * @author Mario Vidal
 */
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = false, name="project_id")
    private Project project; // parent id
    @Column(nullable = false)
    private String name;
    private List<String> tags;
    @Column(nullable = true, length = 1000)
    private String content;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }
    
    public void setProject(Project project) {
    	this.project = project;
    }
    public Project getProject() {
    	return this.project;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return name;
    }


}
