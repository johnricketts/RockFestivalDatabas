package gui.visitor;

import database.Database;

import javax.swing.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class SceneSchedulePanel extends JPanel {
    private JComboBox<String> scenesCB = new JComboBox<>();
    private JButton btnBack = new JButton("Back");
    private Database database;

    public SceneSchedulePanel(Database database) {
        this.database = database;
        fillComboBoxScenes();
        add(scenesCB);
        add(btnBack);
    }

    public void fillComboBoxScenes(){
        for(String s : database.getAllStages()){
            scenesCB.addItem(s);
        }
    }

    public JComboBox getComboBox() {
        return scenesCB;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
