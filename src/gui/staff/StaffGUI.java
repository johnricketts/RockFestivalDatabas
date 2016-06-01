package gui.staff;

import database.Database;

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
    private Database  db;

    public StaffGUI() {
        db = new Database();
        db.connect();
        setContentPane(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rockfestival - Staff");
        setPreferredSize(new Dimension(640, 480));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        btnAddStaffMember.addActionListener(e-> addStaff());
        btnScenAnsvar.addActionListener(e-> getStageResponsibility());
    }


    public void addStaff(){
        String id, name, phoneNbr;
        id =  JOptionPane.showInputDialog("Personnummer: ");
        name = JOptionPane.showInputDialog("Namn: ");
        phoneNbr = JOptionPane.showInputDialog("Telefonnummer: ");
        db.addStaffMember(id,name,phoneNbr);
        JOptionPane.showMessageDialog(null, "Ny anställd tillagd.");
    }

    public void getStageResponsibility(){
        String stage = JOptionPane.showInputDialog("Vilken scen vill du ha ansvarsschema för?");
        String result = db.getStaffResponsibility(stage);
        JOptionPane.showMessageDialog(null,result);
    }

    public static void main(String[] args) {
        new StaffGUI();
    }
}
