package gui.visitor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class MainSchedulePanel extends JPanel {
    private JPanel pnlLbl = new JPanel();
    private JPanel pnlButtons = new JPanel();
    private JPanel pnlBack = new JPanel();
    private JLabel lblMain = new JLabel("Schema");
    private JButton btnScen = new JButton("Scenschema");
    private JButton btnBand = new JButton("Bandschema");
    private JButton btnBack = new JButton("Back");

    public MainSchedulePanel() {
        setLayout(new GridLayout(2, 0));

        pnlLbl.setLayout(new GridLayout(2, 1));
        lblMain.setHorizontalAlignment(SwingConstants.CENTER);
        lblMain.setFont(new Font(lblMain.getName(), Font.PLAIN, 28));
        pnlLbl.add(lblMain);

        pnlLbl.add(btnScen);
        add(pnlLbl);

        pnlButtons.setLayout(new GridLayout(2, 1));
        pnlButtons.add(btnBand);
        pnlButtons.add(btnBack);
        add(pnlButtons);
    }

    public JButton getBtnScen() {
        return btnScen;
    }

    public JButton getBtnBand() {
        return btnBand;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
