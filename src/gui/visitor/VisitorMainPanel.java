package gui.visitor;

import javax.swing.*;
import java.awt.*;

import static java.awt.Label.CENTER;

/**
 * Created by Albin on 2016-05-31.
 */
public class VisitorMainPanel extends JPanel {
    private JPanel pnlLbl = new JPanel();
    private JPanel pnlButtons = new JPanel();
    private JLabel lblMain = new JLabel("Festivalinformation");
    private JButton btnSchema = new JButton("Spelscheman");
    private JButton btnInfo = new JButton("Bandinformation");
    private JButton btnMap = new JButton("Karta");

    public VisitorMainPanel() {
        setLayout(new GridLayout(0, 1));
        pnlLbl.setLayout(new GridLayout(0, 1));
        lblMain.setHorizontalAlignment(SwingConstants.CENTER);
        lblMain.setFont(new Font(lblMain.getName(), Font.PLAIN, 28));
        pnlLbl.add(lblMain);
        add(pnlLbl);

        pnlButtons.setLayout(new GridLayout(0, 3));
        pnlButtons.add(btnSchema);
        pnlButtons.add(btnInfo);
        pnlButtons.add(btnMap);
        add(pnlButtons);
    }

    public JButton getBtnSchema() {
        return btnSchema;
    }

    public JButton getBtnInfo() {
        return btnInfo;
    }

    public JButton getBtnMap() {
        return btnMap;
    }
}
