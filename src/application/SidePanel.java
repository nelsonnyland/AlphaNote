package application;

import model.Project;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class SidePanel extends JPanel implements ListSelectionListener {

    JScrollPane scrollPane;
    JList<Project> projects;

    public SidePanel(Project[] projects) {
        this.projects = new JList<>(projects);
        buildLayout();
        buildComponents();
        addComponents();
        addListeners();
    }

    private void buildLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(150, SidePanel.HEIGHT));
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            projects = (JList<Project>) e.getSource();
            Project selectedProject = projects.getSelectedValue();
            //TODO: send content to view pane...
            System.out.println("You selected: " + selectedProject.getName());
        }
    }
}
