package database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by John Ricketts on 2016-05-31.
 */
public class Database {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private static final String host =  "jdbc:mysql://195.178.232.16:3306/m11p1108";
    private String uname;
    private String password;
    private File file = new File("C:\\Users\\johnn\\IdeaProjects\\RockFestivalDatabas\\res\\login");

    /**
     * Fetches log in information from a file
     */
    public void fetchLogin(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            uname = br.readLine();
            password = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the connection to the database
     */
    public void connect (){
        fetchLogin();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(host,uname,password);
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the database
     */
    public void disconnect(){
    if(con != null){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con = null;
    }
    }

    /**
     * Fetches a list of all the stages from the database
     * @return - stages
     */
    public ArrayList<String> getAllStages(){
        ArrayList<String> stages = new ArrayList<>();
        String query = "select namn from scen;";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                stages.add(rs.getString("namn"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stages;
    }

    /**
     * Fetches a list of all bands from the database
     * @return - bands
     */
    public ArrayList<String> getAllBands(){
        ArrayList<String> bands = new ArrayList<>();
        String query = "select namn from band;";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                bands.add(rs.getString("namn"));
            }
            st.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bands;
    }

    /**
     * Fetches a list of all the bandmembers in a given band
     * @param band - the band to fetch members from
     * @return - bandmembers - a list of bandmembers
     */
    public ArrayList<String> getBandMembers(String band){
        ArrayList<String> bandMembers = new ArrayList<>();
        String query = "select * from bandmedlem where band = '" + band + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                bandMembers.add(rs.getString("namn"));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandMembers;
    }

    /**
     * Fetches the band biography and member biography
     * @param band the band to get biography for
     * @return - bandBio
     */
    public String getBandBio(String band){
        String bandBio = band + "\n\n";
        String query = "select biografi from band where namn = '" + band + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                bandBio += rs.getString("biografi") + "\n\n";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "select namn, biografi from bandmedlem where band = '" + band + "';";
        bandBio+= "Medlemmar: \n";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                bandBio += rs.getString("namn") + "\n";
                bandBio += rs.getString("biografi") + "\n\n";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandBio;
    }

    /**
     * Gets a list of a schedule for a given stage
     * @param stage the stage to find the schedule for
     * @return schedule for the stage
     */
    public String getScheduleForStage(String stage){
        String schedule = "Spelschema för scen: " + stage + "\n\n";
        String query = "select tid, bandnamn from spelning where scennamn = '" + stage + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                schedule += "Band: " + rs.getString("bandnamn");
                schedule += " | Tid: " + rs.getTimestamp("tid") + "\n";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    /**
     * Gets the entire schedule for a given band
     * @param band the name of the band
     * @return schedule for the band
     */
    public String getScheduleForBand(String band){
        String schedule = "Spelschema för: " + band + "\n\n";
        String query = "select * from spelning where bandnamn ='" + band + "' order by tid asc;";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                schedule += "Tid: " + rs.getTimestamp("tid");
                schedule += " Scen: " + rs.getString("scennamn") + "\n";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    /**
     * Fetches the names and times of the staff members with responsibility of a given stage
     * @param stage which stage to get the list from
     * @return responsibilityList a list of responsible staff members with their schedules
     */
    public String getStaffResponsibility(String stage){
        String responsibilityList = "Responsibility for: " + stage +"\n\n";
        String query = "select * from ansvar join personal on ansvar.personal=personal.personnr where scennamn = '" + stage + "' order by tid asc;";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                responsibilityList += "Datum: " + rs.getTimestamp("tid");
                responsibilityList += " Personal: " + rs.getString("personal.namn") +".";
                responsibilityList += " Personnummer: " + rs.getString("personal.personnr") + "\n";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responsibilityList;
    }

    /**
     * Finds the contact for a given band
     * @param band the band to find the contact person for
     * @return bandContact
     */
    public String getBandContact(String band){
        String bandContact = "Kontaktperson för " + band + ": ";
        String query = "select personal.namn from personal inner join band on personal.personnr = band.kontaktpersonnr where band.namn = '" + band + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                bandContact += rs.getString("personal.namn") + ".";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandContact;
    }

    /**
     * Gets a list of bands a certain staff member is responsible for
     * @param id the personal number of the staff member
     * @return bandList a list of bands a staff member is responsible for
     */
    public String getStaffBandResponsibility(String id){
        String bandList = "";
        String query = "select namn from personal where personnr = '" + id +"'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                bandList += rs.getString("namn") + " är kontaktperson för: ";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "select namn from band where kontaktpersonnr ='" + id + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                bandList += rs.getString("namn") + ", ";
            }
            st.close();
            rs.close();
          bandList = bandList.substring(0, bandList.length()-2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandList;
    }

    /**
     * Add a new performance of a band
     * @param bandnamn - The performing band
     * @param scennamn - The stage they are performing on
     * @param tid - Walk on time for the performance
     */
    public void addPerformance(String bandnamn, String scennamn, String tid) {
        String query = "insert into spelning values('" + tid + "'" + ", '" + scennamn + "'," + "'" + bandnamn + "');";
        try {
            st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new staff member to the database
     * @param id the personal number of the staff member
     * @param name
     * @param telephoneNbr
     */
    public void addStaffMember(String id, String name, String telephoneNbr){
        String query = "insert into personal values ('" + id + "'" + "," + "'" + name + "'" + "," + "'" + telephoneNbr + "');";
        try{
            st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of all the current staff members names
     * @return
     */
    public String getAllStaffMembers(){
        String query = "select namn from personal;";
        String staffMembers = "Current staff: ";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                staffMembers += rs.getString("namn") + ", ";
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        staffMembers = staffMembers.substring(0, staffMembers.length()-2);
        return staffMembers;
    }
}
