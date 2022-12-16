package application.ui.dialog;

import application.MainFrame;
import application.model.Project;
import application.service.ProjectService;
import application.ui.ProjectPanel;
import application.utilities.SpringContext;
import application.utilities.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * ProjectDialog is a JDialog for creating projects.
 *
 * @author Nelson Nyland
 * @author Mario Vidal
 */
public class ProjectDialog extends JDialog {

    private ProjectPanel projectPanel;
    private JLabel projectNameLabel;
    private JLabel projectTagsLabel;
    private JTextField projectNameText;
    private JTextField projectTagsText;
    private JButton createButton;
    private JButton cancelButton;

    /**
     * ProjectDialog creates an instance of JDialog
     *
     * @author Nelson Nyland
     * @param projectPanel
     */
    public ProjectDialog(ProjectPanel projectPanel) {
        super(MainFrame.MAIN_FRAME, "New Project");
        this.projectPanel = projectPanel;
        setLayout(new SpringLayout());
        buildComponents();
        addComponents();
        addListeners();
        layoutComponents(MainFrame.MAIN_FRAME);
    }

    /**
     * buildComponents builds the components for the JDialog
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        projectNameLabel = new JLabel("Project Name: ", JLabel.TRAILING);
        projectNameText = new JTextField(15);
        projectNameLabel.setLabelFor(projectNameText);

        projectTagsLabel = new JLabel("Tags: ", JLabel.TRAILING);
        projectTagsText = new JTextField(15);
        projectNameLabel.setLabelFor(projectTagsText);

        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");
    }

    /**
     * addComponents adds the components for the JDialog
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(projectNameLabel);
        add(projectNameText);
        add(projectTagsLabel);
        add(projectTagsText);
        add(createButton);
        add(cancelButton);
    }

    /**
     * addListeners adds actionListeners to the JDialog
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        createButton.addActionListener(this::createProject);
        cancelButton.addActionListener(this::cancel);
        getRootPane().setDefaultButton(createButton);
    }

    /**
     * layoutCompactGrid builds the JDialog
     *
     * @author Nelson Nyland
     * @param owner
     */
    private void layoutComponents(Frame owner) {
        SpringUtilities.makeCompactGrid(getContentPane(), 3, 2, 6, 6, 6, 6);
        this.setModalityType(ModalityType.MODELESS);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }

    /**
     * createProject creates a project and adds it to the database
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     * @param actionEvent
     */
    private void createProject(ActionEvent actionEvent) {
        String projectName = projectNameText.getText();
        String projectTags = projectTagsText.getText();
        String[] tags = projectTags.trim().split(",");
        Project project = new Project();
        project.setName(projectName);
        project.setTags(Arrays.stream(tags).toList());
        projectPanel.addProject(project);
        // save to db
        ProjectService projectDAO = SpringContext.getBean(ProjectService.class);
        projectDAO.saveProject(project);        
        this.dispose();
    }

    /**
     * cancel closes the JDialog
     *
     * @author Nelson Nyland
     * @param actionEvent
     */
    private void cancel(ActionEvent actionEvent) {
        this.dispose();
    }

}
