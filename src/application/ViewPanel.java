package application;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ViewPanel extends JPanel {

    JTextPane textPane;
    JScrollPane scrollPane;

    public ViewPanel() {
        buildLayout();
        buildComponents();
        addComponents();
    }

    private void buildLayout() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(830, 700));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    private void buildComponents() {
        textPane = new JTextPane();
        textPane.setCaretPosition(0);
        textPane.setMargin(new Insets(10, 10, 10, 10));
        //StyledDocument styledDocument = textPane.getStyledDocument();
        scrollPane = new JScrollPane(textPane);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(830, 700));
    }

    private void addComponents() {
        add(scrollPane);
    }

}
