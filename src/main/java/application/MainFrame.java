package application;

import application.model.Note;
import application.ui.NotePanel;
import application.ui.ProjectPanel;
import application.ui.ToolPanel;
import application.ui.ViewPanel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import application.model.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * MainFrame is the entry point of the application.
 *
 * @author Mario Vidal
 * @author Nelson Nyland
 * @version 0.1
 */
@SpringBootApplication
public class MainFrame extends JFrame {

    private ToolPanel toolPanel;
    private ProjectPanel projectPanel;
    private NotePanel notePanel;
    private ViewPanel viewPanel;
    //private List<Project> projects;
    private DefaultListModel<Project> projectModel;
    private List<Note> notes;

    public static MainFrame MAIN_FRAME;

    // starts the application
    public static void main(String[] args)
    {    	
        //EventQueue.invokeLater(MainFrame::createAndShowGui);
    	var ctx = new SpringApplicationBuilder(MainFrame.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            var ex = ctx.getBean(MainFrame.class);
            ex.createAndShowGui();
        });
    }

    public MainFrame() {
    	super("AlphaNote");
        MAIN_FRAME = this;
        setLayout(new BorderLayout());
        buildProjects();
        buildComponents();
        addComponents();
    }

    private void buildProjects() {
        //TODO: implement getting projects from database
        // get projects
        List<String> tags = new ArrayList<>();
        tags.add("Project");
        //projects = new ArrayList<>();
        projectModel = new DefaultListModel<>();
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            //project.setId((int)(Math.random() * 99999));
            project.setId(i);
            project.setName("Project " + i);
            project.setTags(tags);
            //projects.add(project);
            projectModel.addElement(project);
        }
    }

    private void buildComponents() {
        toolPanel = new ToolPanel();
        notePanel = new NotePanel();
        projectPanel = new ProjectPanel(notePanel, projectModel);
        viewPanel = new ViewPanel();
	}
	
    /**
     * Add JPanels to JFrame
     */
    private void addComponents() {
        add(toolPanel, BorderLayout.NORTH);
        add(projectPanel, BorderLayout.WEST);
        add(notePanel, BorderLayout.CENTER);
        add(viewPanel, BorderLayout.EAST);
    }
	
    public static void createAndShowGui() {
    	final MainFrame window = new MainFrame();
        final Dimension frameSize = new Dimension(1150, 700);
        // configure frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setSize(frameSize);
        window.setLocationRelativeTo(null); // centers the screen
        window.setVisible(true);
    }

}
