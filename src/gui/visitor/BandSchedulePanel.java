package gui.visitor;

import database.Database;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Albin on 2016-05-31.
 */
public class BandSchedulePanel extends JPanel {
    private JComboBox<String> bandsCB = new JComboBox<>();
    private JButton btnBack = new JButton("Back");
    private Database database;

    public BandSchedulePanel(Database database) {
        this.database = database;
        fillComboBoxBands();
        add(bandsCB);
        add(btnBack);
    }

    public void fillComboBoxBands() {
        for(String b : database.getAllBands()) {
            bandsCB.addItem(b);
        }
    }

    public JComboBox getComboBox() {
        return bandsCB;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
