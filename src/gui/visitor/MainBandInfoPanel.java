package gui.visitor;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Albin on 2016-05-31.
 */
public class MainBandInfoPanel extends JPanel implements ItemListener {
    private JComboBox<String> bandInfoCB = new JComboBox<>();
    private JTextArea taInfo = new JTextArea();
    private JButton btnBack = new JButton("Back");
    private Database database;

    public MainBandInfoPanel(Database database) {
        setLayout(new GridLayout(3,0));
        this.database = database;
        fillComboBoxBandInfo();
        taInfo.setEditable(false);
        bandInfoCB.addItemListener(this);
        add(bandInfoCB);
        add(taInfo);
        add(btnBack);
    }

    public void fillComboBoxBandInfo() {
        for(String b : database.getAllBands()) {
            bandInfoCB.addItem(b);
        }
    }


    public void itemStateChanged(ItemEvent e) {
        taInfo.setText(database.getBandBio((String)e.getItem()));
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
