package gui.visitor;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by Albin on 2016-05-31.
 */
public class BandSchedulePanel extends JPanel implements ItemListener {
    private JComboBox<String> bandsCB = new JComboBox<>();
    private JTextArea taInfo = new JTextArea();
    private JButton btnBack = new JButton("Back");
    private Database database;

    public BandSchedulePanel(Database database) {
        setLayout(new GridLayout(3,0));
        this.database = database;
        fillComboBoxBands();
        taInfo.setEditable(false);
        bandsCB.addItemListener(this);
        add(bandsCB);
        add(taInfo);
        add(btnBack);
    }

    public void fillComboBoxBands() {
        for(String b : database.getAllBands()) {
            bandsCB.addItem(b);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        taInfo.append(database.getScheduleForBand((String)e.getItem()));
    }

    public JComboBox getComboBox() {
        return bandsCB;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
