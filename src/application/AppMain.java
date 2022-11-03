package application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.Team;
import ui.AboutDialog;

public class AppMain extends JFrame{
	
    /** A button to display about info. */
    private JButton myAboutButton;
    private JPanel mainPanel;
    
    public AppMain(String title) {
    	super(title);
        buildComponents();
        layoutComponents();
        addListeners();
    }
    
    
	public static void main(String[] args)
	{
		EventQueue.invokeLater(AppMain::createAndShowGui);
	}
	
	private void buildComponents() {
		myAboutButton = new JButton("About");
		mainPanel = new JPanel(new BorderLayout());		
	}
	
    /**
     * Lay out the components and makes this frame visible.
     */
    private void layoutComponents() {
    	    	
    	final JPanel buttonPanel = new JPanel();
    	buttonPanel.add(myAboutButton);    	
    	mainPanel.add(buttonPanel, BorderLayout.NORTH);
    	setContentPane(mainPanel);
    }
    
    /**
     * Add actionListeners to the buttons. 
     */
    private void addListeners() {
    	//Add an action listener using a method reference.
    	myAboutButton.addActionListener(this::about);
    }
	
    public static void createAndShowGui() {
    	final AppMain window = new AppMain("AlphaNote");
        //A size for the JFrame.
        final Dimension frameSize = new Dimension(800, 480);        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        window.pack();

        window.setSize(frameSize);        
        window.setVisible(true);
    }
    
    private void about(final ActionEvent theEvent) { 

    	Team team = new Team();
    	
		team.addMember("Mario Vidal, nickname: McMarioMan");
		team.addMember("Nelson Nyland");
		team.addMember("Danait Asefa");
		team.addMember("Khoi Nguyen");
    	
    	AboutDialog aboutDialog = new AboutDialog(this, team);    	
    	
    }
}
