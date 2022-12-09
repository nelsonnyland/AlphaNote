package application.ui.dialog;

import application.MainFrame;
import application.repository.SettingsDAO;
import application.utilities.Settings;
import application.utilities.SpringContext;
import application.utilities.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * SettingsDialog is the UI for adjusting the settings of the application.
 *
 * @author Nelson Nyland
 * @author Mario Vidal
 */
public class SettingsDialog extends JDialog {

    private JLabel firstNameLabel;
    private JLabel emailLabel;
    private JTextField firstNameText;
    private JTextField emailText;
    private JButton saveButton;
    private JButton cancelButton;

    /**
     * SettingsDialog constructs the JDialog
     *
     * @author Nelson Nyland
     */
    public SettingsDialog() {
        super(MainFrame.MAIN_FRAME, "Settings");
        setLayout(new SpringLayout());
        buildComponents();
        addComponents();
        addListeners();
        layoutComponents(MainFrame.MAIN_FRAME);
    }

    /**
     * buildComponents builds the components for the JDialog
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
    	    	    	
        firstNameLabel = new JLabel("First Name: ", JLabel.TRAILING);
        firstNameText = new JTextField(15);
        firstNameLabel.setLabelFor(firstNameText);

        emailLabel = new JLabel("Email: ", JLabel.TRAILING);
        emailText = new JTextField(15);
        emailLabel.setLabelFor(emailText);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        SettingsDAO settingsDAO = SpringContext.getBean(SettingsDAO.class);
		
		List<Settings> settingsList = settingsDAO.findAll();    	
    	if(settingsList.size() == 0) {
    		return;    		
    	}
		    	   	
    	Settings settings = settingsList.get(0);
    	
    	firstNameText.setText(settings.getFirst_name());
    	emailText.setText(settings.getEmail());
        
    }

    /**
     * addComponents adds components to the JDialog
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(firstNameLabel);
        add(firstNameText);
        add(emailLabel);
        add(emailText);
        add(saveButton);
        add(cancelButton);
    }

    /**
     * addListeners adds listeners to the JDialog
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        saveButton.addActionListener(this::saveSettings);
        cancelButton.addActionListener(this::cancel);
        getRootPane().setDefaultButton(saveButton);
    }

    /**
     * layoutComponents builds the JDialog
     *
     * @author Nelson Nyland
     * @param owner
     */
    private void layoutComponents(Frame owner) {
        SpringUtilities.makeCompactGrid(getContentPane(), 3, 2, 6, 6, 6, 6);
        this.setModalityType(ModalityType.MODELESS);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }

    /**
     * saveSettings saves the settings for the application
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     * @param theEvent
     */
    private void saveSettings(final ActionEvent theEvent) {
        String firstName = firstNameText.getText();
        String email = emailText.getText();
        Settings.saveSettings(firstName, email);
        this.dispose();
    }

    /**
     * cancel closes out the JDialog
     *
     * @author Nelson Nyland
     * @param theEvent
     */
    private void cancel(final ActionEvent theEvent) {
        this.dispose();
    }

}
