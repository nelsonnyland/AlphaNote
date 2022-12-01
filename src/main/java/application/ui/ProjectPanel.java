package application.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.model.Project;

import java.awt.*;
import java.util.List;


/**
 * ProjectPanel is the selection pane for projects.
 *
 * @author Nelson Nyland
 */
public class ProjectPanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private JList<Project> projects;

    public ProjectPanel(List<Project> projectList) {
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
//            projects = (JList<AlphaNote>) e.getSource();
//            AlphaNote selected = projects.getSelectedValue();
//            //TODO: send content to view pane...
//            System.out.println("You selected: " + selected.getName());
        }
    }
}
