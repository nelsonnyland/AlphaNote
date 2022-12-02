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
    private static JList<Note> notes;

    public NotePanel() {
        notes = new JList<>();
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

    public void addListeners() {
        notes.addListSelectionListener(this);
    }

    public void setNotePanel(List<Note> notesList) {
        notes.setListData(notesList.toArray(new Note[0]));
        revalidate();
        repaint();
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
