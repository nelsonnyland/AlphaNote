package application;

import application.ui.NotePanel;
import application.ui.ProjectPanel;
import application.ui.ToolPanel;
import application.ui.ViewPanel;
import application.utilities.SpringContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import application.model.Project;
import application.repository.ProjectDAO;
import application.service.ProjectService;

import java.awt.*;
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
    private DefaultListModel<Project> projectModel;

    public static MainFrame MAIN_FRAME;

    /**
     * starts the application
     *
     * @author Mario Vidal
     * @param args
     */
    public static void main(String[] args)
    {    	
        var ctx = new SpringApplicationBuilder(MainFrame.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(MainFrame.class);
            ex.createAndShowGui();            
        });
    }

    /**
     * 0 arg constructor
     *
     * @author Mario Vidal
     * @author Nelson Nyland
     */
    public MainFrame() {}

    /**
     * Initializes MainFrame
     *
     * @author Mario Vidal
     * @author Nelson Nyland
     * @param title
     */
    public MainFrame(String title) {
    	super(title);
        MAIN_FRAME = this;
        setLayout(new BorderLayout());
        buildProjects();
        buildComponents();
        addComponents();        
    }

    /**
     * Builds the project view
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     */
    private void buildProjects() {
    	ProjectService projectService = SpringContext.getBean(ProjectService.class);
    	projectModel = new DefaultListModel<>();
    	projectModel.addAll(projectService.getAllProjects());    	
    }

    /**
     * Builds the components
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        toolPanel = new ToolPanel();
        notePanel = new NotePanel();
        projectPanel = new ProjectPanel(notePanel, projectModel);
        viewPanel = new ViewPanel();
	}
	
    /**
     * Add JPanels to JFrame
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(toolPanel, BorderLayout.NORTH);
        add(projectPanel, BorderLayout.WEST);
        add(notePanel, BorderLayout.CENTER);
        add(viewPanel, BorderLayout.EAST);
    }

    /**
     * Builds the Frame
     *
     * @author Mario Vidal
     * @author Nelson Nyland
     */
    public static void createAndShowGui() {
    	final MainFrame window = new MainFrame("AlphaNote");
        final Dimension frameSize = new Dimension(1150, 700);
        // configure frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setSize(frameSize);
        window.setLocationRelativeTo(null); // centers the screen
        window.setVisible(true);
    }

}
