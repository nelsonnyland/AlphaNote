package application.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.model.Note;
import application.model.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * ProjectPanel is the selection pane for projects.
 *
 * @author Nelson Nyland
 */
public class ProjectPanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private NotePanel notePanel;
    private JList<Project> projects;

    public ProjectPanel(NotePanel notePanel, List<Project> projectList) {
        this.notePanel = notePanel;
        this.projects = new JList<>(projectList.toArray(new Project[0]));
        buildLayout();
        buildComponents();
        addComponents();
        addListeners();
    }

    private void buildLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(150, ProjectPanel.HEIGHT));
    }

    private void buildComponents() {
        scrollPane = new JScrollPane(projects);
    }

    private void addComponents() {
        add(scrollPane);
    }

    private void addListeners() {
        projects.addListSelectionListener(this);
    }

    private void loadProject(Project selected) {
        //TODO: get notes affiliated with selected project by note ids
        // set notes panel
        notePanel.setNotePanel(buildNotes());
        // build display string
        StringBuilder tags = new StringBuilder();
        if (selected.getTags() != null) {
            for (String tag : selected.getTags()) {
                tags.append(tag);
            }
        }
        ViewPanel.setTextArea(
                selected.getName() + "\n" +
                        "Tags: " + tags
        );
    }

    private List<Note> buildNotes() {
        List<Note> notes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setId((int)(Math.random() * 99999));
            note.setName("Note " + i);
            note.setContent("Here is content for note # " + i);
            notes.add(note);
        }
        return notes;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            projects = (JList<Project>) e.getSource();
            Project selected = projects.getSelectedValue();
            loadProject(selected);
        }
    }
}
