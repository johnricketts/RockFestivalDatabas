package gui.staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by John on 2016-05-31.
 */
public class StaffGUI extends JFrame {

    private JPanel main;
    private JButton btnAddStaffMember;
    private JButton btnScenAnsvar;
    private JButton btnBandAnsvar;
    private JButton btnAddPerformance;
    private JButton btnStageSchedule;
    private JButton btnBandSchedule;

    public StaffGUI() {
        setContentPane(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rockfestival - Staff");
        setPreferredSize(new Dimension(640, 480));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void addStaff(){
        String id, name, phoneNbr;
        id =  JOptionPane.showInputDialog("Personnummer: ");
        name = JOptionPane.showInputDialog("Namn: ");
        phoneNbr = JOptionPane.showInputDialog("Telefonnummer: ");

    }

    public static void main(String[] args) {

    }
}
