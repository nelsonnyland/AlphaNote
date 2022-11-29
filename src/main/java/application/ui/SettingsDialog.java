package application.ui;

import application.utilities.Settings;
import application.utilities.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsDialog extends JDialog {

    private JLabel firstNameLabel;
    private JLabel emailLabel;
    private JTextField firstNameText;
    private JTextField emailText;
    private JButton saveButton;
    private JButton cancelButton;

    public SettingsDialog(Frame owner) {
        super(owner, "Settings");
        setLayout(new SpringLayout());
        buildComponents();
        addComponents();
        addListeners();
        layoutComponents(owner);
    }

    private void buildComponents() {
        firstNameLabel = new JLabel("First Name: ", JLabel.TRAILING);
        firstNameText = new JTextField(15);
        firstNameLabel.setLabelFor(firstNameText);

        emailLabel = new JLabel("Email: ", JLabel.TRAILING);
        emailText = new JTextField(15);
        emailLabel.setLabelFor(emailText);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
    }

    private void addComponents() {
        add(firstNameLabel);
        add(firstNameText);
        add(emailLabel);
        add(emailText);
        add(saveButton);
        add(cancelButton);
    }

    private void addListeners() {
        saveButton.addActionListener(this::saveSettings);
        cancelButton.addActionListener(this::cancel);
    }

    private void layoutComponents(Frame owner) {
        SpringUtilities.makeCompactGrid(getContentPane(), 3, 2, 6, 6, 6, 6);

        this.setModalityType(ModalityType.MODELESS);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }

    private void saveSettings(final ActionEvent theEvent) {
        String firstName = firstNameText.getText();
        String email = emailText.getText();
        Settings.saveSettings(firstName, email);
    }

    private void cancel(final ActionEvent theEvent) {
        this.dispose();
    }

}
