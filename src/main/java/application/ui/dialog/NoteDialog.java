package application.ui.dialog;

import application.MainFrame;
import application.model.Note;
import application.model.Project;
import application.repository.NoteDAO;
import application.ui.NotePanel;
import application.ui.ProjectPanel;
import application.utilities.SpringContext;
import application.utilities.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * NoteDialog enables the creation of notes in the UI.
 *
 * @author Nelson Nyland
 */
public class NoteDialog extends JDialog {

    private NotePanel notePanel;
    private JLabel noteNameLabel;
    private JLabel noteTagsLabel;
    private JTextField noteNameText;
    private JTextField noteTagsText;
    private JButton createButton;
    private JButton cancelButton;

    /**
     * NoteDialog is a dialog for creating a new note.
     *
     * @author Nelson Nyland
     * @param notePanel
     */
    public NoteDialog(NotePanel notePanel) {
        super(MainFrame.MAIN_FRAME, "New Note");
        this.notePanel = notePanel;
        setLayout(new SpringLayout());
        buildComponents();
        addComponents();
        addListeners();
        layoutComponents(MainFrame.MAIN_FRAME);
    }

    /**
     * buildComponents builds the components for the dialog
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        noteNameLabel = new JLabel("Note Name: ", JLabel.TRAILING);
        noteNameText = new JTextField(15);
        noteNameLabel.setLabelFor(noteNameText);

        noteTagsLabel = new JLabel("Tags: ", JLabel.TRAILING);
        noteTagsText = new JTextField(15);
        noteNameLabel.setLabelFor(noteTagsText);

        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");
    }

    /**
     * addComponents adds components to the JDialog
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(noteNameLabel);
        add(noteNameText);
        add(noteTagsLabel);
        add(noteTagsText);
        add(createButton);
        add(cancelButton);
    }

    /**
     * addListeners adds actionListeners to components
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        createButton.addActionListener(this::createNote);
        cancelButton.addActionListener(this::cancel);
    }

    /**
     * layoutComponents builds the JDialog
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
     * createNote creates a note and stores it in the database.
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     * @param actionEvent
     */
    private void createNote(ActionEvent actionEvent) {
        String noteName = noteNameText.getText();
        String noteTags = noteTagsText.getText();
        String[] tags = noteTags.trim().split(",");
        Note note = new Note();        
        note.setProject(ProjectPanel.getProject());
        note.setName(noteName);
        note.setTags(Arrays.stream(tags).toList());
        notePanel.addNote(note);
        ProjectPanel.addNoteId(note.getId());
        //TODO: save into db
        NoteDAO noteDAO = SpringContext.getBean(NoteDAO.class);
        noteDAO.save(note);
        this.dispose();
    }

    /**
     * cancel closes out the JDialog.
     *
     * @author Nelson Nyland
     * @param actionEvent
     */
    private void cancel(ActionEvent actionEvent) {
        this.dispose();
    }

}
