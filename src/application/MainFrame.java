package application;

import model.AlphaNote;
import model.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JPanel toolPanel;
    private JPanel sidePanel;
    private JPanel viewPanel;
    private AlphaNote[] projects;

    // starts the application
    public static void main(String[] args)
    {
        EventQueue.invokeLater(MainFrame::createAndShowGui);
    }

    public MainFrame(String title) {
    	super(title);
        setLayout(new BorderLayout());
        getProjects();
        buildComponents();
        addComponents();
    }

    private void getProjects() {
        //TODO: implement getting projects from database
        List<String> tags = new ArrayList<>();
        tags.add("Project");
        projects = new AlphaNote[10];
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setId((int)(Math.random() * 99999));
            project.setName("Project " + i);
            project.setTags(tags);
            projects[i] = project;
        }
    }

    private void buildComponents() {
        toolPanel = new ToolPanel(this);
        sidePanel = new SidePanel(projects);
        viewPanel = new ViewPanel();
	}
	
    /**
     * Add JPanels to JFrame
     */
    private void addComponents() {
    	add(toolPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(viewPanel, BorderLayout.EAST);
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
