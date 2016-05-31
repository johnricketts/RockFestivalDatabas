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
public class VisitorGui extends JFrame implements ActionListener {
    JPanel cards = new JPanel();
    VisitorMainPanel mainPnl = new VisitorMainPanel();
    VisitorSchemaPanel schemaPnl = new VisitorSchemaPanel();

    public VisitorGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rockfestival");
        setContent();
        setPreferredSize(new Dimension(640, 480));
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    public void setContent() {
        cards = new JPanel(new CardLayout());
        cards.add(mainPnl, "Card 1");
        cards.add(schemaPnl, "Card 2");
        add(cards);
        addListeners();
    }

    public void addListeners() {
        //VisitorMainPanel
        mainPnl.getBtnSchema().addActionListener(this);
        mainPnl.getBtnInfo().addActionListener(this);
        mainPnl.getBtnMap().addActionListener(this);

        //VisitorSchemaPanel
        schemaPnl.getBtnBack().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainPnl.getBtnSchema()) {
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "Card 2");
        } else if(e.getSource() == schemaPnl.getBtnBack()) {
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "Card 1");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new VisitorGui());
    }
}
