package gui.visitor;

import javax.swing.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class MainBandInfoPanel extends JPanel {
    private JButton btnBack = new JButton("Back");

    public MainBandInfoPanel() {
        add(btnBack);
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
