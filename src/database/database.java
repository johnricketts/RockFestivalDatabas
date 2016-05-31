package database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by John Ricketts on 2016-05-31.
 */
public class Database {

    private Connection con;
    private PreparedStatement ps;
    Statement st;
    private ResultSet rs;
    private static final String host =  "jdbc:mysql://195.178.232.16:3306/m11p1108";
    private static String uname;
    private static String password;
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
        query = "";
        query = "select namn, biografi from bandmedlem where band = '" + band + "';";
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


    public static void main(String [] args){
        Database b = new Database();
        b.connect();
        System.out.println(b.getAllStages());
        System.out.println(b.getBandBio("Cannibal Corpse"));
    }

}
