package application.ui.dialog;

import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import application.MainFrame;
import application.model.Constants;
import application.model.Team;

/**
 * AboutDialog is for displaying application specific information.
 *
 * @author Mario Vidal
 */
public class AboutDialog extends JDialog {

    private JLabel myRegisteredToLabel;    
    private JLabel myProvidedByLabel;
    private JLabel myVersionLabel;
    
    private Map<TextAttribute, Object> p_style;
    private Team team;

	/**
	 * AboutDialog instantiates the AboutDialog.
	 *
	 * @author Mario Vidal
	 * @param team
	 */
	public AboutDialog(Team team) {
   	  	super(MainFrame.MAIN_FRAME, "About");
   	  	this.team = team;
		buildComponents();
		layoutComponents(MainFrame.MAIN_FRAME);
	}

	/**
	 * buildComponents creates the components necessary for the AboutDialog.
	 *
	 * @author Mario Vidal
	 */
	private void buildComponents() {
		    	
		p_style = new HashMap<>();
		p_style.put(TextAttribute.FAMILY, Font.DIALOG);
		p_style.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT);
		p_style.put(TextAttribute.SIZE, 12);
		
		myRegisteredToLabel = new JLabel("This app is registered to: Jeffrey Weiss");
		myProvidedByLabel = new JLabel("This app provided by Team Alpha:");
		myVersionLabel = new JLabel("AlphaNote " + Constants.VERSION_NUMBER);
	}

	/**
	 * layoutComponents builds the components necessary for the AboutDialog.
	 *
	 * @author Mario Vidal
	 * @param owner
	 */
	private void layoutComponents(Frame owner) {
		Container contentPane = this.getContentPane();    	
    	SpringLayout layout = new SpringLayout();
    	contentPane.setLayout(layout);
    	
    	contentPane.add(myRegisteredToLabel);
    	contentPane.add(myProvidedByLabel);
    	contentPane.add(myVersionLabel);
    	
    	layout.putConstraint(SpringLayout.WEST, myRegisteredToLabel, 5, SpringLayout.WEST, contentPane);    	
    	layout.putConstraint(SpringLayout.NORTH, myRegisteredToLabel, 5, SpringLayout.NORTH, contentPane);
    	
    	layout.putConstraint(SpringLayout.WEST, myProvidedByLabel, 5, SpringLayout.WEST, contentPane);    	
		layout.putConstraint(SpringLayout.NORTH, myProvidedByLabel, 35, SpringLayout.NORTH, contentPane);
		
		int x = 55;
		
		for(String s : team.getMembers()) {
			JLabel member = new JLabel(s);
			contentPane.add(member);
			layout.putConstraint(SpringLayout.WEST, member, 5, SpringLayout.WEST, contentPane);    	
			layout.putConstraint(SpringLayout.NORTH, member, x, SpringLayout.NORTH, contentPane);
			member.setFont(Font.getFont(p_style));
			x+=20;
		}
		
		x+=40;
		
		layout.putConstraint(SpringLayout.EAST, myVersionLabel, -5, SpringLayout.EAST, contentPane);    	
		layout.putConstraint(SpringLayout.NORTH, myVersionLabel, x, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.EAST, contentPane, 5, SpringLayout.EAST, myRegisteredToLabel);
		layout.putConstraint(SpringLayout.SOUTH, contentPane, 200, SpringLayout.NORTH, contentPane);
		
		
    
    	this.setModal(true);
    	this.setResizable(false);
    	this.pack();
    	this.setLocationRelativeTo(owner);    	
    	this.setVisible(true);
	}
	
}
