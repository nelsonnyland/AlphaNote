package application;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	
    private JPanel toolPanel;

    // starts the application
    public static void main(String[] args)
    {
        EventQueue.invokeLater(MainFrame::createAndShowGui);
    }

    public MainFrame(String title) {
    	super(title);
        setLayout(new BorderLayout());
        buildComponents();
        layoutComponents();
        addListeners();
    }

	private void buildComponents() {
        toolPanel = new ToolPanel();
	}
	
    /**
     * Lay out the components and makes this frame visible.
     */
    private void layoutComponents() {
    	add(toolPanel, BorderLayout.NORTH);
//    	final JPanel buttonPanel = new JPanel();
//    	buttonPanel.add(myAboutButton);
//    	mainPanel.add(buttonPanel, BorderLayout.NORTH);
//    	setContentPane(mainPanel);
    }
    
    /**
     * Add actionListeners to the buttons. 
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
