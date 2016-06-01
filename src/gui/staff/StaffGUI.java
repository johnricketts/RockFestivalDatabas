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
    private JButton btnStaffResponsibility;
    private JButton btnAddNewBand;
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
        btnStageSchedule.addActionListener(e-> getStageSchedule());
        btnBandSchedule.addActionListener(e-> getBandSchedule());
        btnBandAnsvar.addActionListener(e-> getBandContact());
        btnStaffResponsibility.addActionListener(e-> getStaffBandResponsibility());
        btnAddPerformance.addActionListener(e-> addPerformance());
        btnAddNewBand.addActionListener(e -> addNewBand());
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

    public void getStageSchedule(){
        String stage = JOptionPane.showInputDialog("Vilken scen vill du ha spelschema för?");
        String result = db.getScheduleForStage(stage);
        JOptionPane.showMessageDialog(null, result);
    }

    public void getBandSchedule(){
        String band = JOptionPane.showInputDialog("Vilket band vill du ha spelschema för?");
        String result = db.getScheduleForBand(band);
        JOptionPane.showMessageDialog(null, result);
    }

    public void getBandContact(){
        String band = JOptionPane.showInputDialog("Vilket band vill du hitta kontaktperson för?");
        String result = db.getBandContact(band);
        JOptionPane.showMessageDialog(null,result);
    }

    public void getStaffBandResponsibility(){
        String staff = JOptionPane.showInputDialog("Personnummer för anställd: ");
        String result = db.getStaffBandResponsibility(staff);
        JOptionPane.showMessageDialog(null,result);
    }

    public void addPerformance(){
        String time = JOptionPane.showInputDialog("Vilken tid?");
        String stage = JOptionPane.showInputDialog("Vilken scen?");
        String band = JOptionPane.showInputDialog("Vilket band?");
        db.addPerformance(band, stage,time);
        JOptionPane.showMessageDialog(null, "Ny spelning tillagd");
    }

    public void addNewBand(){
        String band = JOptionPane.showInputDialog("Namn på band:");
        String country = JOptionPane.showInputDialog("Vilket land kommer bandet ifrån?");
        String genre = JOptionPane.showInputDialog("Vilken genre tillhör bandet?");
        String bio = JOptionPane.showInputDialog("Skriv en kort biografi om bandet:");
        String contactID = JOptionPane.showInputDialog("Personnummer på kontaktpersonen: ");
        db.addNewBand(band,country,genre,bio,contactID);

        int choice = JOptionPane.showConfirmDialog(null, "Vill du lägga till bandmedlemmar?");
        if(choice == JOptionPane.YES_OPTION){
            boolean running = true;
            while(running){
                String bandmember = JOptionPane.showInputDialog("Namn på bandmedlem: ");
                String role = JOptionPane.showInputDialog("Roll i bandet: ");
                String memberbio = JOptionPane.showInputDialog("Kort biografi om medlem:");
                db.addBandMember(bandmember,role,memberbio,band);
                int newMemberChoice = JOptionPane.showConfirmDialog(null, "Vill du lägga till en till bandmedlem?");
                if(newMemberChoice == JOptionPane.NO_OPTION){
                    running = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        new StaffGUI();
    }
}
