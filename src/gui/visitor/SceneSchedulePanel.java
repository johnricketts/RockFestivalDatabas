package gui.visitor;

import javax.swing.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class SceneSchedulePanel extends JPanel {
    private JButton btnBack = new JButton("Back");

    public SceneSchedulePanel() {
        add(btnBack);
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
