package application.ui;

import application.model.Note;
import application.ui.dialog.NoteDialog;

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
    private JButton newNoteButton;
    private static JList<Note> notes;
    private DefaultListModel<Note> noteModel;

    public NotePanel() {
        noteModel = new DefaultListModel<>();
        notes = new JList<>(noteModel);
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
        scrollPane.setColumnHeaderView(new JLabel("Notes"));
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

    private void newNote(ActionEvent actionEvent) {
        new NoteDialog(this);
    }

    public void addNote(Note note) {
        noteModel.addElement(note);
    }

    public void setNotePanel(List<Note> notesList) {
        noteModel.clear();
        noteModel.addAll(notesList);
    }

    public int getNoteCount() {
        return noteModel.getSize();
    }

    public static void saveNote() {
        //TODO: send notes.getSelectedValue() to db
        Note selected = notes.getSelectedValue();
        selected.setContent(ViewPanel.getText());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            notes = (JList<Note>) e.getSource();
            Note selected = notes.getSelectedValue();
            if (selected != null) {
                ViewPanel.setText(selected.getContent()); // set view panel
            }
        }
    }
}
