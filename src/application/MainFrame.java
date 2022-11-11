package application;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    public static MainFrame mainFrame;
    private JPanel toolPanel;

    // starts the application
    public static void main(String[] args)
    {
        EventQueue.invokeLater(MainFrame::createAndShowGui);
    }

    public MainFrame(String title) {
    	super(title);
        mainFrame = this;
        setLayout(new BorderLayout());
        buildComponents();
        layoutComponents();
        addListeners();
    }

	private void buildComponents() {
        toolPanel = new ToolPanel();
	}
	
    /**
     * Add JPanels to JFrame
     */
    private void layoutComponents() {
    	add(toolPanel, BorderLayout.NORTH);
    }
    
    /**
     * Add actionListeners.
     */
    private void addListeners() {
        //
    }
	
    public static void createAndShowGui() {
    	final MainFrame window = new MainFrame("AlphaNote");
        final Dimension frameSize = new Dimension(800, 480);        
        // configure frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setSize(frameSize);
        window.setLocationRelativeTo(null); // centers the screen
        window.setVisible(true);
    }

}
