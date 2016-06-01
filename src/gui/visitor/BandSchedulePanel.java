package gui.visitor;

import javax.swing.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class BandSchedulePanel extends JPanel {
    private JButton btnBack = new JButton("Back");

    public BandSchedulePanel() {
        add(btnBack);
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
