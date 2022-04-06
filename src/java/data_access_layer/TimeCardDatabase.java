/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access_layer;

import business_layer.HourlyEmployee;
import business_layer.SalaryEmployee;
import business_layer.Timecard;
import static data_access_layer.EmployeeDatabase.employee_arr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class TimeCardDatabase {
    static ArrayList<Timecard> timecards_arr = new ArrayList<Timecard>();

    public static void init() {          
        try {
               //Connect to the database
            Connection con = (Connection)  
            DriverManager.getConnection("jdbc:derby://localhost:1527/PayrollSystemDB","CIS640","cis640");
               //Create a ResultSet
            Statement cmd =  con.createStatement();
            ResultSet rs = cmd.executeQuery("SELECT * FROM TimeCard");
            while (rs.next()) {
                String dates = rs.getString("TimecardDate");
                double employeeIds = rs.getDouble("EmployeeId");
                int hoursWorked = rs.getInt("HoursWorked");
                int overtimeHours = rs.getInt("overtimeHours");
                
                Timecard time = new Timecard(dates,employeeIds,hoursWorked,overtimeHours);
                
                timecards_arr.add(time);
                
 
            }
            } catch(SQLException error) {
                System.out.println("ERROR CAUGHT");
                System.err.println("Error:" + error.toString());
            }
                
    }
    
//    Method to return the ArraryList of timecards
    public static ArrayList<Timecard> getTimecards_arr() {return timecards_arr;}
// Method to manually set Timecards in the Timecard ArrayList
    public static void setTimecards_arr(ArrayList<Timecard> timecards_arr) {
        TimeCardDatabase.timecards_arr = timecards_arr;
    }
    
    public static ArrayList<Timecard> get_timecards_by_employee_id (int id) {
        
        ArrayList<Timecard> emp_timecards = new ArrayList<>();
        
        for (int i = 0; i<timecards_arr.size();i++){
            if (id == timecards_arr.get(i).employeeId){
                emp_timecards.add(timecards_arr.get(i));
            }
        }
        
        return emp_timecards;
    }
}