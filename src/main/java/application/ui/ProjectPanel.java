package application.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.model.Note;
import application.model.Project;
import application.repository.NoteDAO;
import application.repository.ProjectDAO;
import application.service.NoteService;
import application.service.ProjectService;
import application.ui.dialog.ProjectDialog;
import application.utilities.SpringContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * ProjectPanel is the selection pane for projects.
 *
 * @author Nelson Nyland
 * @author Mario Vidal
 */
public class ProjectPanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private NotePanel notePanel;
    private JButton newProjectButton;
    private static JList<Project> projects;
    private static DefaultListModel<Project> projectModel;

    /**
     * ProjectPanel instantiates the ProjectPanel
     *
     * @author Nelson Nyland
     * @param notePanel
     * @param projectModel
     */
    public ProjectPanel(NotePanel notePanel, DefaultListModel<Project> projectModel) {
        this.notePanel = notePanel;
        this.projectModel = projectModel;
        this.projects = new JList<>(projectModel);
        buildLayout();
        buildComponents();
        addComponents();
        addListeners();
    }

    /**
     * buildLayout builds the layout for the JPanel
     *
     * @author Nelson Nyland
     */
    private void buildLayout() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(150, ProjectPanel.HEIGHT));
    }

    /**
     * buildComponents builds the components for the JPanel
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        scrollPane = new JScrollPane(projects);
        scrollPane.setColumnHeaderView(new JLabel("Projects"));
        newProjectButton = new JButton("New Project");
    }

    /**
     * addComponents adds components to the JPanel
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(scrollPane, BorderLayout.CENTER);
        add(newProjectButton, BorderLayout.SOUTH);
    }

    /**
     * addListeners adds listeners to the components
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        projects.addListSelectionListener(this);
        newProjectButton.addActionListener(this::newProject);
    }

    /**
     * loadProject loads the project into the ViewPanel
     *
     * @author Nelson Nyland
     * @param selected project
     */
    private void loadProject(Project selected) {
        notePanel.setNotePanel(buildNotes(selected)); // set notes panel
        StringBuilder tags = new StringBuilder();
        if (selected.getTags() != null) {
            for (String tag : selected.getTags()) {
                tags.append(tag);
            }
        }
        ViewPanel.setText("Name: " + selected.getName() + "\n" + "Tags: " + tags);
    }

    /**
     * buildNotes retrieves the notes for the project
     *
     * @author Mario Vidal
     * @param selected project
     * @return the notes for the project
     */
    private List<Note> buildNotes(Project selected) {
        // get notes affiliated with selected project
    	NoteService noteService = SpringContext.getBean(NoteService.class);
        return noteService.getNotesByProject(selected);
    }

    /**
     * newProject opens the ProjectDialog
     *
     * @author Nelson Nyland
     * @param actionEvent new project
     */
    private void newProject(ActionEvent actionEvent) {
        new ProjectDialog(this);
    }

    /**
     * addProject adds a project to the ProjectModel
     *
     * @author Nelson Nyland
     * @param project add project
     */
    public void addProject(Project project) {
        projectModel.addElement(project);
    }

    /**
     * removeProject removes a project from the ProjectModel
     *
     * @author Nelson Nyland
     */
    private static void removeProject() {
        projectModel.removeElement(projects.getSelectedValue());
    }

    /**
     * setProjectPanel sets the projects for the ProjectModel
     *
     * @author Nelson Nyland
     * @param projectList
     */
    public void setProjectPanel(List<Project> projectList) {
        projectModel.clear();
        projectModel.addAll(projectList);
    }

    /**
     * getProjectCount gets the size of the ProjectModel
     *
     * @author Nelson Nyland
     * @return the size of the ProjectModel
     */
    public int getProjectCount() {
        return projectModel.getSize();
    }

    /**
     * getProject gets the selected project from the ProjectModel
     *
     * @author Nelson Nyland
     * @return the selected project
     */
    public static Project getProject() {
        return projects.getSelectedValue();
    }

    /**
     * getProjectId gets the selected project id
     *
     * @author Nelson Nyland
     * @return project id
     */
    public static int getProjectId() {
        return projects.getSelectedValue().getId();
    }

    /**
     * deleteProject deletes the selected project and its notes from the model and database
     *
     * @author Nelson Nyland
     */
    public static void deleteProject() {
        NotePanel.deleteAllNotes();
        Project selected = projects.getSelectedValue();
        ProjectService projectService = SpringContext.getBean(ProjectService.class);
        projectService.deleteProject(selected);
        removeProject();
        System.out.println(selected.getName() + " deleted");
    }

    /**
     * addNoteId adds the note id to the current project
     *
     * @author Nelson Nyland
     * @param noteId
     */
    public static void addNoteId(int noteId) {
        if (projects.getSelectedValue() != null) {
            //projects.getSelectedValue().addNoteId(noteId);
        }
    }

    public static boolean isProjectSelected() {
        return projects.getSelectedValue() != null;
    }

    /**
     * valueChanged gets the selected project
     *
     * @author Nelson Nyland
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            projects = (JList<Project>) e.getSource();
            Project selected = projects.getSelectedValue();
            if (selected != null) {
                loadProject(selected);
                ToolPanel.disableSaveButton();
                ToolPanel.enableDeleteButton();
            }
        }
    }
}
