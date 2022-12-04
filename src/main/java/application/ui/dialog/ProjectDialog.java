package application.ui.dialog;

import application.MainFrame;
import application.model.Project;
import application.ui.ProjectPanel;
import application.utilities.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class ProjectDialog extends JDialog {

    private ProjectPanel projectPanel;
    private JLabel projectNameLabel;
    private JLabel projectTagsLabel;
    private JTextField projectNameText;
    private JTextField projectTagsText;
    private JButton createButton;
    private JButton cancelButton;

    public ProjectDialog(ProjectPanel projectPanel) {
        super(MainFrame.MAIN_FRAME, "New Project");
        this.projectPanel = projectPanel;
        setLayout(new SpringLayout());
        buildComponents();
        addComponents();
        addListeners();
        layoutComponents(MainFrame.MAIN_FRAME);
    }

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

    private void addComponents() {
        add(projectNameLabel);
        add(projectNameText);
        add(projectTagsLabel);
        add(projectTagsText);
        add(createButton);
        add(cancelButton);
    }

    private void addListeners() {
        createButton.addActionListener(this::createProject);
        cancelButton.addActionListener(this::cancel);
    }

    private void layoutComponents(Frame owner) {
        SpringUtilities.makeCompactGrid(getContentPane(), 3, 2, 6, 6, 6, 6);
        this.setModalityType(ModalityType.MODELESS);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }

    private void createProject(ActionEvent actionEvent) {
        String projectName = projectNameText.getText();
        String projectTags = projectTagsText.getText();
        String[] tags = projectTags.trim().split(",");
        Project project = new Project();
        project.setId(projectPanel.getProjectCount());
        project.setName(projectName);
        project.setTags(Arrays.stream(tags).toList());
        projectPanel.addProject(project);
        //TODO: save into db
        this.dispose();
    }

    private void cancel(ActionEvent actionEvent) {
        this.dispose();
    }

}
