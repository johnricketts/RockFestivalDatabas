package gui;

import database.Database;
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
public class VisitorGui extends JFrame {
    private Database database;
    JPanel cards = new JPanel();
    MainPanel mainPanel = new MainPanel();
    MainSchedulePanel mainSchedulePanel;
    MainBandInfoPanel mainBandInfoPanel;
    SceneSchedulePanel sceneSchedulePanel;
    BandSchedulePanel bandSchedulePanel;
    ImageIcon map = new ImageIcon("C:\\Users\\Albin\\IdeaProjects\\RockFestivalDatabas\\src\\gui\\visitor\\karta.jpg");

    public VisitorGui() {
        database = new Database();
        database.connect();
        mainSchedulePanel = new MainSchedulePanel();
        mainBandInfoPanel = new MainBandInfoPanel();
        sceneSchedulePanel = new SceneSchedulePanel(database);
        bandSchedulePanel = new BandSchedulePanel(database);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rockfestival");
        setContent();
        setPreferredSize(new Dimension(640, 480));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setContent() {
        cards = new JPanel(new CardLayout());
        //Main panel
        cards.add(mainPanel, "mainPanel");

        //Schedule panels
        cards.add(mainSchedulePanel, "mainSchedulePanel");
        cards.add(bandSchedulePanel, "bandSchedulePanel");
        cards.add(sceneSchedulePanel, "sceneSchedulePanel");

        //Band information panels
        cards.add(mainBandInfoPanel, "mainBandInfoPanel");

        add(cards);
        addListeners();
    }

    public void addListeners() {
        //MainPanel
        mainPanel.getBtnSchema().addActionListener(e -> changeCard("mainSchedulePanel"));
        mainPanel.getBtnInfo().addActionListener(e -> changeCard("mainBandInfoPanel"));
        mainPanel.getBtnMap().addActionListener(e -> showMap());

        //MainSchedulePanel
        mainSchedulePanel.getBtnScen().addActionListener(e -> changeCard("sceneSchedulePanel"));
        mainSchedulePanel.getBtnBand().addActionListener(e -> changeCard("bandSchedulePanel"));
        mainSchedulePanel.getBtnBack().addActionListener(e -> changeCard("mainPanel"));

        //BandSchedulePanel
        bandSchedulePanel.getBtnBack().addActionListener(e -> changeCard("mainSchedulePanel"));

        //SceneSchedulePanel
        sceneSchedulePanel.getBtnBack().addActionListener(e -> changeCard("mainSchedulePanel"));

        //MainBandInfoPanel
        mainBandInfoPanel.getBtnBack().addActionListener(e -> changeCard("mainPanel"));
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