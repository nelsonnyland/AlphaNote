package application.ui;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * ViewPanel is the main view pane of for the application.
 *
 * @author Nelson Nyland
 */
public class ViewPanel extends JPanel {

    JTextPane textPane;
    JScrollPane scrollPane;

    // WIDTH is based on main frame width minus sidebar width:
    // 1000 - 150 = 850 (- 20 aberration) = 830
    private final int WIDTH = 830;

    // HEIGHT is based on main frame height minus appx toolbar height:
    // 700 - 50 = 650 (+ 20 aberration) = 670
    private final int HEIGHT = 670;

    public ViewPanel() {
        buildLayout();
        buildComponents();
        addComponents();
    }

    private void buildLayout() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //setAlignmentX(Component.CENTER_ALIGNMENT);
        //setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    private void buildComponents() {
        textPane = new JTextPane();
        textPane.setCaretPosition(0);
        //textPane.setMargin(new Insets(10, 10, 10, 10));
        //StyledDocument styledDocument = textPane.getStyledDocument();
        scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
    }

    private void addComponents() {
        add(scrollPane);
    }

}
