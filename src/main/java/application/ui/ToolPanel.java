package application.ui;

import javax.swing.*;

import application.ui.dialog.AboutDialog;
import application.ui.dialog.SettingsDialog;
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
    private JButton saveButton;
    private JMenuBar menuBar;
    private JMenuItem aboutMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem exportSettingsMenuItem;
    private JMenuItem importSettingsMenuItem;

    /**
     * ToolPanel instantiates the ToolPanel class
     *
     * @author Nelson Nyland
     */
    public ToolPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        buildComponents();
        addComponents();
        addListeners();
    }

    /**
     * buildComponents builds the components for the ToolPanel
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        //var saveIcon
        var saveIcon = new ImageIcon("src/main/resources/icons/glyphicons-444-floppy-disk.png");
        saveButton = new JButton(saveIcon);
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

    /**
     * addComponents adds components to the ToolPanel
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(saveButton);
        add(menuBar);
    }

    /**
     * addListeners adds listeners to the ToolPanel components
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        saveButton.addActionListener(this::saveNote);
        aboutMenuItem.addActionListener(this::aboutDialog);
        settingsMenuItem.addActionListener(this::settingsDialog);
        exportSettingsMenuItem.addActionListener(this::exportSettings);
        importSettingsMenuItem.addActionListener(this::importSettings);
    }

    /**
     * saveNote saves the note to the database.
     *
     * @author Nelson Nyland
     * @param actionEvent
     */
    private void saveNote(ActionEvent actionEvent) {
        //TODO: save note to db
        NotePanel.saveNote();
        System.out.println("SAVED");
    }

    /**
     * aboutDialog creates the AboutDialog
     *
     * @author Mario Vidal
     * @author Nelson Nyland
     * @param theEvent
     */
    private void aboutDialog(final ActionEvent theEvent) {
        Team team = new Team();
        team.addMember("Mario Vidal, nickname: McMarioMan");
        team.addMember("Nelson Nyland, nickname: Nelly");
        team.addMember("Khoi Nguyen");
        new AboutDialog(team);
    }

    /**
     * settingsDialog creates the SettingsDialog
     *
     * @author Nelson Nyland
     * @param theEvent
     */
    private void settingsDialog(final ActionEvent theEvent) {
        new SettingsDialog();
    }

    /**
     * exportSettings creates a file chooser for exporting the settings
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     * @param theEvent
     */
    private void exportSettings(final ActionEvent theEvent) {
    	//We could send the path as a parameter here
    	//TODO: file chooser -- save
        Settings.exportSettings();
    }

    /**
     * importSettings creates a file chooser for importing the settings
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     * @param theEvent
     */
    private void importSettings(final ActionEvent theEvent) {
    	//TODO: file chooser -- open
        Settings.importSettings();
    }

}
