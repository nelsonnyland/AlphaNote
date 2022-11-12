package application;

import model.Note;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JPanel toolPanel;
    private JPanel sidePanel;
    private Note[] notes;

    // starts the application
    public static void main(String[] args)
    {
        EventQueue.invokeLater(MainFrame::createAndShowGui);
    }

    public MainFrame(String title) {
    	super(title);
        setLayout(new BorderLayout());
        getNotes();
        buildComponents();
        layoutComponents();
        addListeners();
    }

    private void getNotes() {
        //TODO: implement getting notes from database
        List<String> tags = new ArrayList<>();
        tags.add("Test notes");
        notes = new Note[10];
        for (int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setId((int)(Math.random() * 99999));
            note.setName("Test Name " + i);
            note.setTags(tags);
            notes[i] = note;
        }
    }

    private void buildComponents() {
        toolPanel = new ToolPanel(this);
        sidePanel = new SidePanel(notes);
	}
	
    /**
     * Add JPanels to JFrame
     */
    private void layoutComponents() {
    	add(toolPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
    }
    
    /**
     * Add actionListeners.
     */
    private void addListeners() {
        //
    }
	
    public static void createAndShowGui() {
    	final MainFrame window = new MainFrame("AlphaNote");
        final Dimension frameSize = new Dimension(1000, 700);
        // configure frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setSize(frameSize);
        window.setLocationRelativeTo(null); // centers the screen
        window.setVisible(true);
    }

}
