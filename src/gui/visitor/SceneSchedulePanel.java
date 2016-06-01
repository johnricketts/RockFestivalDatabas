package gui.visitor;

import javax.swing.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class SceneSchedulePanel extends JPanel {
    private JComboBox<String> scenesCB = new JComboBox<>();
    private JButton btnBack = new JButton("Back");
    private String scenScenen, mallorcascenen, dieseltältet;

    public SceneSchedulePanel() {
        scenScenen = "Scenscenen";
        mallorcascenen = "Mallorcascenen";
        dieseltältet = "Dieseltältet";
        scenesCB.addItem(scenScenen);
        scenesCB.addItem(mallorcascenen);
        scenesCB.addItem(dieseltältet);
        add(scenesCB);
        add(btnBack);
    }

    public JComboBox getComboBox() {
        return scenesCB;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
