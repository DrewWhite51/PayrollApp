package data_access_layer;

import business_layer.Employee;
import business_layer.HourlyEmployee;
import business_layer.SalaryEmployee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashSet;

public class EmployeeDatabase {
    // Initializing Arraylist for all employees
    static ArrayList<Employee> employee_arr = new ArrayList<Employee>();

    
    public static void init() {          
        try {
               //Connect to the database
            Connection con = (Connection)  
            DriverManager.getConnection("jdbc:derby://localhost:1527/PayrollSystemDB","CIS640","cis640");
               //Create a ResultSet
            Statement cmd =  con.createStatement();
            ResultSet rs = cmd.executeQuery("SELECT * FROM Employee");
            while (rs.next()) {
                String firstNames = rs.getString("FirstName");
                String lastNames = rs.getString("LastName");
                int employeeID = rs.getInt("EmployeeId");
                double ssn = rs.getDouble("SSN");
                String userId = rs.getString("UserId");
                String password = rs.getString("Password");
                double hourlyRate = rs.getDouble("HourlyRate");
                double overtimeRate = rs.getDouble("OvertimeRate");
                double salary = rs.getDouble("Salary");

                if (hourlyRate == 0) {
                    SalaryEmployee salEmp = new SalaryEmployee(salary,firstNames,lastNames,employeeID,ssn,userId,password);
                    employee_arr.add(salEmp);
                } else {
                    HourlyEmployee hourEmp = new HourlyEmployee(hourlyRate,overtimeRate,firstNames,lastNames,employeeID,ssn,userId,password);
                    employee_arr.add(hourEmp);
                }   
            }
            } catch(SQLException error) {
                System.out.println("ERROR CAUGHT");
                System.err.println("Error:" + error.toString());
            }
                
    }
    



// Method that returns the arraylist for all employees
    public static ArrayList<Employee> get_employees() {return employee_arr;}
    
    public static boolean check_hourly_employee(Employee employee) {
        for (int i = 0; i<employee_arr.size();i++) {
            return employee.toString().contains(", hourlyRate=");
        }
        return false;
    }
// Method that gets employee by their IDs
    public static Employee get_employee_by_id(int id){
//        Loops through the employee array and tries to find a match with the argument being passed
        for (int i = 0; i<employee_arr.size();i++){
//            If there is a match return the employee from them database
            if (id == employee_arr.get(i).employeeId){
                return employee_arr.get(i);
            }
        }
//        IF method can't find a match return null
        return null;
    }



}