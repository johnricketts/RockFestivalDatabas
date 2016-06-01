package test;

import database.Database;

/**
 * Created by John on 2016-06-01.
 */
public class DbTest {
    public static void main(String [] args){
        Database b = new Database();
        b.connect();
        System.out.println(b.getAllStages());
        System.out.println(b.getBandBio("Cannibal Corpse"));
        System.out.println(b.getScheduleForStage("Scenscenen"));
        System.out.println(b.getStaffResponsibility("Scenscenen"));
        System.out.println(b.getScheduleForBand("Cannibal Corpse"));
        System.out.println(b.getBandContact("Cannibal Corpse"));
        System.out.println(b.getStaffBandResponsibility("8911171234"));
        System.out.println(b.getAllStaffMembers());
    }
}
