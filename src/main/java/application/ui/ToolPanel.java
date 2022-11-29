package application.ui;

import javax.swing.*;

import application.main.MainFrame;
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
    private MainFrame mainFrame;
    private JMenuBar menuBar;
    private JMenuItem aboutMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem exportSettingsMenuItem;
    private JMenuItem importSettingsMenuItem;

    public ToolPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        buildComponents();
        addComponents();
        addListeners();
    }
    

    private void buildComponents() {
        menuBar = new JMenuBar();
        var optionsMenu = new JMenu("...");
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

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JMenuItem selectedItem = (JMenuItem) e.getSource();
//        if (selectedItem == aboutMenuItem) {
//            Team team = new Team();
//            team.addMember("Mario Vidal, nickname: McMarioMan");
//            team.addMember("Nelson Nyland, nickname: Nelly");
//            team.addMember("Khoi Nguyen");
//            new AboutDialog(mainFrame, team);
//        }
//    }

    private void aboutDialog(final ActionEvent theEvent) {
        Team team = new Team();
        team.addMember("Mario Vidal, nickname: McMarioMan");
        team.addMember("Nelson Nyland, nickname: Nelly");
        team.addMember("Khoi Nguyen");
        new AboutDialog(mainFrame, team);
    }

    private void settingsDialog(final ActionEvent theEvent) {
//    	Scanner input = new Scanner(System.in);
//    	System.out.println("Enter first name");
//    	String first_name = input.nextLine();
//    	System.out.println("Enter email");
//    	String email = input.nextLine();
//    	Settings.saveSettings(first_name, email);
        new SettingsDialog(mainFrame);
    }
    
    private void exportSettings(final ActionEvent theEvent) {
    	//We could send the path as a parameter here
    	Settings.exportSettings();
    }
    
    private void importSettings(final ActionEvent theEvent) {
    	Settings.importSettings();
    }

}
