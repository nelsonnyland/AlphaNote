package application;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.model.AlphaNote;
import application.model.Project;

import java.awt.*;
import java.util.Collection;

/**
 * SidePanel is the selection pane for projects and notes.
 *
 * @author Nelson Nyland
 */
public class SidePanel extends JPanel implements ListSelectionListener {

    JScrollPane scrollPane;
    JList<AlphaNote> projects;

    public SidePanel(AlphaNote[] projects) {
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
            projects = (JList<AlphaNote>) e.getSource();
            AlphaNote selected = projects.getSelectedValue();
            //TODO: send content to view pane...
            System.out.println("You selected: " + selected.getName());
        }
    }
}
