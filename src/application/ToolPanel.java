package application;

import model.Team;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel implements ActionListener {

    /** A button to display about info. */
    private JButton aboutButton;

    public ToolPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        buildComponents();
        layoutComponents();
        addListeners();
    }

    private void buildComponents() {
        aboutButton = new JButton("About");
    }

    private void layoutComponents() {
        add(aboutButton);
    }

    private void addListeners() {
        aboutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == aboutButton) {
            Team team = new Team();
            team.addMember("Mario Vidal, nickname: McMarioMan");
            team.addMember("Nelson Nyland, nickname: Nelly");
            team.addMember("Danait Asefa");
            team.addMember("Khoi Nguyen");
            //TODO: Redo the plumbing for AboutDialog
            //AboutDialog aboutDialog = new AboutDialog(this, team);
        }
    }
}
