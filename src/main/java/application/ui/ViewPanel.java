package application.ui;

import application.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * ViewPanel is the main view pane of for the application.
 *
 * @author Nelson Nyland
 */
public class ViewPanel extends JPanel {

    private static JTextArea textArea;
    private JScrollPane scrollPane;

    public ViewPanel() {
        buildLayout();
        buildComponents();
        addComponents();
    }

    private void buildLayout() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(850, MainFrame.HEIGHT));
    }

    private void buildComponents() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
    }

    private void addComponents() {
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void setTextArea(String text) {
        textArea.setText(text);
    }

}
