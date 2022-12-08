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

    /**
     * ViewPanel instantiates the ViewPanel
     *
     * @author Nelson Nyland
     */
    public ViewPanel() {
        buildLayout();
        buildComponents();
        addComponents();
    }

    /**
     * buildLayout builds the layout for the ViewPanel
     *
     * @author Nelson Nyland
     */
    private void buildLayout() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(850, MainFrame.HEIGHT));
    }

    /**
     * buildComponents builds the components for the ViewPanel
     *
     * @author Nelson Nyland
     */
    private void buildComponents() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
    }

    /**
     * addComponents adds components for the ViewPanel
     *
     * @author Nelson Nyland
     */
    private void addComponents() {
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * getText gets the text for the JTextArea
     *
     * @author Nelson Nyland
     * @return the text for the TextArea
     */
    public static String getText() {
        return textArea.getText();
    }

    /**
     * setText sets the text for the JTextArea
     *
     * @author Nelson Nyland
     * @param text
     */
    public static void setText(String text) {
        textArea.setText(text);
    }

}
