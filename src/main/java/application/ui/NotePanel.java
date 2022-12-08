package application.ui;

import application.model.Note;
import application.service.NoteService;
import application.ui.dialog.NoteDialog;
import application.utilities.SpringContext;

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
 * @author Mario Vidal
 */
public class NotePanel extends JPanel implements ListSelectionListener {

    private JScrollPane scrollPane;
    private JButton newNoteButton;
    private static JList<Note> notes;
    private static DefaultListModel<Note> noteModel;

    /**
     * NotePanel instantiates the NotePanel
     *
     * @author Nelson Nyland
     */
    public NotePanel() {
        noteModel = new DefaultListModel<>();
        notes = new JList<>(noteModel);
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
        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BorderLayout());
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(150, NotePanel.HEIGHT));
    }

    /**
     * buildComponents builds the components for the JPanel
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        scrollPane = new JScrollPane(notes);
        scrollPane.setColumnHeaderView(new JLabel("Notes"));
        newNoteButton = new JButton("New Note");
    }

    /**
     * addComponents adds components to the JPanel
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(scrollPane, BorderLayout.CENTER);
        add(newNoteButton, BorderLayout.SOUTH);
    }

    /**
     * addListeners adds actionListeners to the JPanel
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        notes.addListSelectionListener(this);
        newNoteButton.addActionListener(this::newNote);;
    }

    /**
     * newNote creates a NoteDialog
     *
     * @author Nelson Nyland
     * @param actionEvent
     */
    private void newNote(ActionEvent actionEvent) {
        new NoteDialog(this);
    }

    /**
     * addNote adds a note to the NoteModel
     *
     * @author Nelson Nyland
     * @param note
     */
    public void addNote(Note note) {
        noteModel.addElement(note);
    }

    /**
     * removeNote removes a note from the NoteModel
     *
     * @author Nelson Nyland
     */
    private static void removeNote() {
        noteModel.removeElement(notes.getSelectedValue());
    }

    /**
     * setNotePanel sets the NoteModel
     *
     * @author Nelson Nyland
     * @param notesList
     */
    public void setNotePanel(List<Note> notesList) {
        noteModel.clear();
        notes = new JList<>(noteModel);
        noteModel.addAll(notesList);
    }

    /**
     * saveNote saves the note to the object and database
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     */
    public static void saveNote() {
        Note selected = notes.getSelectedValue();
        selected.setContent(ViewPanel.getText());
        NoteService noteService = SpringContext.getBean(NoteService.class); 
        noteService.saveNote(selected);
        System.out.println(selected.getName() + " saved");
    }

    /**
     * deleteNote deletes the selected note from the database
     *
     * @author Nelson Nyland
     */
    public static void deleteNote() {
        Note selected = notes.getSelectedValue();
        NoteService noteService = SpringContext.getBean(NoteService.class);
        noteService.deleteNote(selected);
        removeNote();
        System.out.println(selected.getName() + " deleted");
    }

    /**
     * deleteAllNotes deletes all the notes in the NoteModel and the database
     *
     * @author Nelson Nyland
     */
    public static void deleteAllNotes() {
        NoteService noteService = SpringContext.getBean(NoteService.class);
        for (int i = 0; i < noteModel.getSize(); i++) {
            noteService.deleteNote(noteModel.get(i));
        }
        noteModel.clear();
    }

    /**
     * isNoteSelected returns whether a note is selected
     *
     * @author Nelson Nyland
     * @return
     */
    public static boolean isNoteSelected() {
        return notes.getSelectedValue() != null;
    }

    /**
     * getNoteCount gets the size of the NoteModel
     *
     * @author Nelson Nyland
     * @return size of NoteModel
     */
    public int getNoteCount() {
        return noteModel.getSize();
    }

    /**
     * valueChanged raises the ListSelectionEvent for the NoteModel
     *
     * @author Nelson Nyland
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            notes = (JList<Note>) e.getSource();
            Note selected = notes.getSelectedValue();
            if (selected != null) {
                ViewPanel.setText(selected.getContent()); // set view panel
                ToolPanel.enableSaveButton();
            }
        }
    }
}
