package gui;

import gui.visitor.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by John on 2016-05-31.
 */
public class VisitorGui extends JFrame implements ItemListener {
    JPanel cards = new JPanel();
    MainPanel mainPanel = new MainPanel();
    MainSchedulePanel mainSchedulePanel = new MainSchedulePanel();
    MainBandInfoPanel mainBandInfoPanel = new MainBandInfoPanel();
    SceneSchedulePanel sceneSchedulePanel = new SceneSchedulePanel();
    BandSchedulePanel bandSchedulePanel = new BandSchedulePanel();
    ImageIcon map = new ImageIcon("C:\\Users\\Albin\\IdeaProjects\\RockFestivalDatabas\\src\\gui\\visitor\\karta.jpg");

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
        //Main panel
        cards.add(mainPanel, "Card 1");

        //Schedule panels
        cards.add(mainSchedulePanel, "Card 2");
        cards.add(bandSchedulePanel, "Card 4");
        cards.add(sceneSchedulePanel, "Card 5");

        //Band information panels
        cards.add(mainBandInfoPanel, "Card 3");

        add(cards);
        addListeners();
    }

    public void addListeners() {
        //MainPanel
        mainPanel.getBtnSchema().addActionListener(e -> changeCard("card 2"));
        mainPnl.getBtnInfo().addActionListener(this);
        mainPanel.getBtnMap().addActionListener(e -> showMap());

        //MainSchedulePanel
        schemaPnl.getBtnScen().addActionListener(this);
        schemaPnl.getBtnBand().addActionListener(this);
        schemaPnl.getBtnBack().addActionListener(this);

        //VisitorBandPanel
        bandPnl.getBtnBack().addActionListener(this);
    }

    private void changeCard(String cardname) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardname);
    }

    public void showMap() {
        JOptionPane.showMessageDialog(null, "", "Karta över området", JOptionPane.INFORMATION_MESSAGE, map);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VisitorGui());
    }
}