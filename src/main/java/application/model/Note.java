package application.model;

import java.util.List;

public class Note {

    private int id;
    private int projectId; // parent id
    private String name;
    private List<String> tags;
    private String content;

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
