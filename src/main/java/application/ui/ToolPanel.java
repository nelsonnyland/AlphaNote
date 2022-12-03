package application.ui;

import javax.swing.*;

import application.MainFrame;
import application.utilities.Settings;
import application.model.Team;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * ToolPanel houses buttons that control the application.
 *
 * @author Nelson Nyland
 * @author Mario Vidal
 */
public class ToolPanel extends JPanel {//implements ActionListener {

    /** A button to display about info. */
    private JMenuBar menuBar;
    private JMenuItem aboutMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem exportSettingsMenuItem;
    private JMenuItem importSettingsMenuItem;

    public ToolPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        buildComponents();
        addComponents();
        addListeners();
    }
    

    private void buildComponents() {
        menuBar = new JMenuBar();
        var optionsMenu = new JMenu();
        var optionsIcon = new ImageIcon("src/main/resources/icons/glyphicons-137-cogwheel.png", "Options");
        optionsMenu.setIcon(optionsIcon);
        menuBar.add(optionsMenu);
        aboutMenuItem = new JMenuItem("About");
        settingsMenuItem = new JMenuItem("Settings");
        exportSettingsMenuItem = new JMenuItem("Export Settings");
        importSettingsMenuItem = new JMenuItem("Import Settings");
        optionsMenu.add(aboutMenuItem);
        optionsMenu.add(settingsMenuItem);
        optionsMenu.add(exportSettingsMenuItem);
        optionsMenu.add(importSettingsMenuItem);
    }

    private void addComponents() {
        add(menuBar);
    }

    private void addListeners() {
        aboutMenuItem.addActionListener(this::aboutDialog);
        settingsMenuItem.addActionListener(this::settingsDialog);
        exportSettingsMenuItem.addActionListener(this::exportSettings);
        importSettingsMenuItem.addActionListener(this::importSettings);
    }

    private void aboutDialog(final ActionEvent theEvent) {
        Team team = new Team();
        team.addMember("Mario Vidal, nickname: McMarioMan");
        team.addMember("Nelson Nyland, nickname: Nelly");
        team.addMember("Khoi Nguyen");
        new AboutDialog(team);
    }

    private void settingsDialog(final ActionEvent theEvent) {
        new SettingsDialog();
    }
    
    private void exportSettings(final ActionEvent theEvent) {
    	//We could send the path as a parameter here
    	//TODO: file chooser -- save
        Settings.exportSettings();
    }
    
    private void importSettings(final ActionEvent theEvent) {
    	//TODO: file chooser -- open
        Settings.importSettings();
    }

}
