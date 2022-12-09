package application.ui;

import javax.swing.*;

import application.MainFrame;
import application.ui.dialog.AboutDialog;
import application.ui.dialog.SettingsDialog;
import application.utilities.Settings;
import application.model.Team;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static application.MainFrame.MAIN_FRAME;

/**
 * ToolPanel houses buttons that control the application.
 *
 * @author Nelson Nyland
 * @author Mario Vidal
 */
public class ToolPanel extends JPanel {//implements ActionListener {

    /** A button to display about info. */
    private JToolBar toolBar;
    private JMenuBar menuBar;
    private JMenuItem aboutMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem exportSettingsMenuItem;
    private JMenuItem importSettingsMenuItem;
    private static JButton saveButton;
    private static JButton deleteButton;

    /** File selection dialog box.*/
    private final JFileChooser myFileChooser = 
                    new JFileChooser(System.getProperty("user.dir"));

    /**
     * ToolPanel instantiates the ToolPanel class
     *
     * @author Nelson Nyland
     */
    public ToolPanel() {
        //setLayout(new FlowLayout(FlowLayout.RIGHT));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(MainFrame.WIDTH, 40));
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
        // toolbar
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        // buttons
        var deleteIcon = new ImageIcon("src/main/resources/icons/glyphicons-17-bin.png");
        deleteButton = new JButton(deleteIcon);
        deleteButton.setToolTipText("Delete");
        deleteButton.setEnabled(false);
        var saveIcon = new ImageIcon("src/main/resources/icons/glyphicons-444-floppy-disk.png");
        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save Note");
        saveButton.setEnabled(false);
        // settings drop-down
        menuBar = new JMenuBar();
        var optionsMenu = new JMenu();
        var optionsIcon = new ImageIcon("src/main/resources/icons/glyphicons-137-cogwheel.png",
                "Options");
        optionsMenu.setIcon(optionsIcon);
        optionsMenu.setToolTipText("Settings");
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
        toolBar.add(deleteButton);
        toolBar.addSeparator();
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(menuBar);
        add(toolBar, BorderLayout.EAST);
    }

    /**
     * addListeners adds listeners to the ToolPanel components
     *
     * @author Nelson Nyland
     */
    private void addListeners() {
        deleteButton.addActionListener(this::delete);
        saveButton.addActionListener(this::saveNote);
        aboutMenuItem.addActionListener(this::aboutDialog);
        settingsMenuItem.addActionListener(this::settingsDialog);
        exportSettingsMenuItem.addActionListener(this::exportSettings);
        importSettingsMenuItem.addActionListener(this::importSettings);
    }

    /**
     * delete begins the process of deleting a note or project
     *
     * @author Nelson Nyland
     * @param actionEvent
     */
    private void delete(ActionEvent actionEvent) {
        if (NotePanel.isNoteSelected()) {
            int value = JOptionPane.showConfirmDialog(MAIN_FRAME,
                    "Would you like to delete this note?",
                    "Delete Note", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                NotePanel.deleteNote();
            }
        } else if (ProjectPanel.isProjectSelected()) {
            int value =  JOptionPane.showConfirmDialog(MAIN_FRAME,
                    "Would you like to delete this project" +
                    " and all its related notes?",
                    "Delete Project", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                ProjectPanel.deleteProject();
            }
        }
    }

    /**
     * saveNote saves the note to the database.
     *
     * @author Nelson Nyland
     * @param actionEvent
     */
    private void saveNote(ActionEvent actionEvent) {
        NotePanel.saveNote();
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
    	
    	if (myFileChooser.showSaveDialog(MAIN_FRAME)
                == JFileChooser.APPROVE_OPTION) {
    		
    		myFileChooser.setCurrentDirectory(myFileChooser.getCurrentDirectory());
    		
    		Settings.exportSettings(myFileChooser.getSelectedFile().getAbsolutePath());
    	}        
        
    }

    /**
     * importSettings creates a file chooser for importing the settings
     *
     * @author Nelson Nyland
     * @author Mario Vidal
     * @param theEvent
     */
    private void importSettings(final ActionEvent theEvent) {
    	
    	final int returnVal = myFileChooser.showOpenDialog(MAIN_FRAME);
        
        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return;
        }
        
        myFileChooser.setCurrentDirectory(myFileChooser.getCurrentDirectory());
    	
        Settings.importSettings(myFileChooser.getSelectedFile().getAbsolutePath());
    }

    /**
     * enableSaveButton enables the save button
     *
     * @author Nelson Nyland
     */
    public static void enableSaveButton() {
        saveButton.setEnabled(true);
    }

    /**
     * disableSaveButton disables the save button
     *
     * @author Nelson Nyland
     */
    public static void disableSaveButton() {
        saveButton.setEnabled(false);
    }

    /**
     * enableDeleteButton enables the delete button
     *
     * @author Nelson Nyland
     */
    public static void enableDeleteButton() {
        deleteButton.setEnabled(true);
    }

    /**
     * disableDeleteButton disables the delete button
     */
    public static void disableDeleteButton() {
        deleteButton.setEnabled(false);
    }

}
