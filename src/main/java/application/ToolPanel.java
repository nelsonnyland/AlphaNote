package application;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

import application.model.Settings;
import application.model.SpringContext;
import application.model.Team;
import application.repository.SettingsDAO;
import application.ui.AboutDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel implements ActionListener {

    /** A button to display about info. */
    private MainFrame mainFrame;
    private JButton aboutButton;
    private JButton settingsButton;
    private JButton exportSettingsButton;
    private JButton importSettingsButton;
    
    
    public ToolPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        buildComponents();
        addComponents();
        addListeners();
    }
    

    private void buildComponents() {
        aboutButton = new JButton("About");
        settingsButton = new JButton("Settings");
        exportSettingsButton = new JButton("Export settings");
        importSettingsButton = new JButton("Import settings");
    }

    private void addComponents() {
        add(aboutButton);
        add(settingsButton);
        add(exportSettingsButton);
        add(importSettingsButton);
    }

    private void addListeners() {
        aboutButton.addActionListener(this);
        settingsButton.addActionListener(this::saveSettings);
        exportSettingsButton.addActionListener(this::exportSettings);
        importSettingsButton.addActionListener(this::importSettings);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == aboutButton) {
            Team team = new Team();
            team.addMember("Mario Vidal, nickname: McMarioMan");
            team.addMember("Nelson Nyland, nickname: Nelly");            
            team.addMember("Khoi Nguyen");
            new AboutDialog(mainFrame, team);
        }
    }
    
    private void saveSettings(final ActionEvent theEvent) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Enter first name");
    	String first_name = input.nextLine();
    	System.out.println("Enter email");
    	String email = input.nextLine();
    	
    	
    	Settings.saveSettings(first_name, email);
    }
    
    private void exportSettings(final ActionEvent theEvent) {
    	//We could send the path as a parameter here
    	Settings.exportSettings();
    }
    
    private void importSettings(final ActionEvent theEvent) {
    	Settings.importSettings();
    }
    
}
