package application.ui;

import application.model.Note;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.awt.*;

/**
 * NotePanel is the selection pane for notes.
 *
 * @author Nelson Nyland
 */
public class NotePanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private JList<Note> notes;

    public NotePanel(List<Note> notesList) {
        this.notes = new JList<>(notesList.toArray(new Note[0]));
        buildLayout();
        buildComponents();
        addComponents();
        addListeners();
    }

    private void buildLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(150, NotePanel.HEIGHT));
    }

    private void buildComponents() {
        scrollPane = new JScrollPane(notes);
    }

    private void addComponents() {
        add(scrollPane);
    }

    private void addListeners() {
        notes.addListSelectionListener(this);
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
