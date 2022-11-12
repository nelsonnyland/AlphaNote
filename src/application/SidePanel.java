package application;

import model.Note;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class SidePanel extends JPanel implements ListSelectionListener {

    JScrollPane pane;
    JList<Note> notes;

    public SidePanel(Note[] notes) {
        this.notes = new JList<Note>(notes);
        buildLayout();
        buildComponents();
        layoutComponents();
        addListeners();
    }

    private void buildLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(150, SidePanel.HEIGHT));
    }

    private void buildComponents() {
        pane = new JScrollPane(notes);
    }

    private void layoutComponents() {
        add(pane);
    }

    private void addListeners() {
        notes.addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            notes = (JList<Note>) e.getSource();
            Note selectedNote = notes.getSelectedValue();
            //TODO: send content to view pane...
            System.out.println("You selected: " + selectedNote.getName());
        }
    }
}
