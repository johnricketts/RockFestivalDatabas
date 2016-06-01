package gui.visitor;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Albin on 2016-05-31.
 */
public class SceneSchedulePanel extends JPanel implements ItemListener {
    private JComboBox<String> scenesCB = new JComboBox<>();
    private JTextArea taInfo = new JTextArea();
    private JButton btnBack = new JButton("Back");
    private Database database;

    public SceneSchedulePanel(Database database) {
        setLayout(new GridLayout(0,3));
        this.database = database;
        fillComboBoxScenes();
        taInfo.setEditable(false);
        add(scenesCB);
        add(taInfo);
        add(btnBack);
    }

    public void fillComboBoxScenes(){
        for(String s : database.getAllStages()){
            scenesCB.addItem(s);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        taInfo.append(database.getBandBio((String)e.getItem()));
    }

    public JComboBox getComboBox() {
        return scenesCB;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
