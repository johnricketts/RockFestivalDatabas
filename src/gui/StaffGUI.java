package gui;

import gui.visitor.VisitorMainPanel;
import gui.visitor.VisitorSchemaPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by John on 2016-05-31.
 */
public class StaffGUI extends JFrame implements ActionListener {
    JPanel cards = new JPanel();

    public StaffGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rockfestival - Staff");
        setContent();
        setPreferredSize(new Dimension(640, 480));
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    public void setContent() {
        cards = new JPanel(new CardLayout());
        //TODO: Add cards.
        add(cards);
        addListeners();
    }

    public void addListeners() {
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StaffGUI());
    }
}
