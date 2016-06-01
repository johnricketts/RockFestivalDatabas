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
        setLayout(new GridLayout(3,0));
        this.database = database;
        fillComboBoxScenes();
        taInfo.setEditable(false);
        scenesCB.addItemListener(this);
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
        taInfo.setText(database.getScheduleForStage((String)e.getItem()));
    }

    public JComboBox getComboBox() {
        return scenesCB;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
