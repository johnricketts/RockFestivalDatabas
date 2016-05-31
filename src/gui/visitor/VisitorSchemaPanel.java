package gui.visitor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Albin on 2016-05-31.
 */
public class VisitorSchemaPanel extends JPanel {
    private JButton back = new JButton("Back");


    public VisitorSchemaPanel() {
        setLayout(new FlowLayout());
        add(back);
    }

    public JButton getBtnBack() {
        return back;
    }
}
