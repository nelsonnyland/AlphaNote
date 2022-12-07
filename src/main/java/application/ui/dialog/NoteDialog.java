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

public class NoteDialog extends JDialog {

    private NotePanel notePanel;
    private JLabel noteNameLabel;
    private JLabel noteTagsLabel;
    private JTextField noteNameText;
    private JTextField noteTagsText;
    private JButton createButton;
    private JButton cancelButton;

    public NoteDialog(NotePanel notePanel) {
        super(MainFrame.MAIN_FRAME, "New Note");
        this.notePanel = notePanel;
        setLayout(new SpringLayout());
        buildComponents();
        addComponents();
        addListeners();
        layoutComponents(MainFrame.MAIN_FRAME);
    }

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

    private void addComponents() {
        add(noteNameLabel);
        add(noteNameText);
        add(noteTagsLabel);
        add(noteTagsText);
        add(createButton);
        add(cancelButton);
    }

    private void addListeners() {
        createButton.addActionListener(this::createNote);
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

    private void cancel(ActionEvent actionEvent) {
        this.dispose();
    }

}
