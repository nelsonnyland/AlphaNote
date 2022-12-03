package application.ui;

import application.model.Note;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.*;

/**
 * NotePanel is the selection pane for notes.
 *
 * @author Nelson Nyland
 */
public class NotePanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private static JList<Note> notes;
    private JButton newNoteButton;

    public NotePanel() {
        notes = new JList<>();
        buildLayout();
        buildComponents();
        addComponents();
        addListeners();
    }

    private void buildLayout() {
        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BorderLayout());
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(150, NotePanel.HEIGHT));
    }

    private void buildComponents() {
        scrollPane = new JScrollPane(notes);
        newNoteButton = new JButton("New Note");
    }

    private void addComponents() {
        add(scrollPane, BorderLayout.CENTER);
        add(newNoteButton, BorderLayout.SOUTH);
    }

    public void addListeners() {
        notes.addListSelectionListener(this);
        newNoteButton.addActionListener(this::newNote);;
    }

    public void setNotePanel(List<Note> notesList) {
        notes.setListData(notesList.toArray(new Note[0]));
        revalidate();
        repaint();
    }

    private void newNote(ActionEvent actionEvent) {
        System.out.println("NEW NOTE");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            notes = (JList<Note>) e.getSource();
            Note selected = notes.getSelectedValue();
            if (selected != null) {
                ViewPanel.setTextArea(selected.getContent()); // set view panel
            }
        }
    }
}
