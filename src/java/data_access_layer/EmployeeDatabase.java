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

public class EmployeeDatabase {
    // Initializing Arraylist for all employees
    static ArrayList<Employee> employee_arr = new ArrayList<Employee>();
    
//    static {
//        salary_employees_arr.add(s1);
//        salary_employees_arr.add(s2);
//        salary_employees_arr.add(s3);
//        hourly_employees_arr.add(h1);
//        hourly_employees_arr.add(h2);
//        hourly_employees_arr.add(h3);
//
//        employee_arr.add(s1);
//        employee_arr.add(s2);
//        employee_arr.add(s3);
//        employee_arr.add(h1);
//        employee_arr.add(h2);
//        employee_arr.add(h3);
//    }
    
    public static void init() {          
        try {
               //Connect to the database
            Connection con = (Connection)  
            DriverManager.getConnection("jdbc:derby://localhost:1527/PayrollSystemDB","CIS640","cis640");
               //Create a ResultSet
            Statement cmd =  con.createStatement();
            ResultSet rs = cmd.executeQuery("SELECT * FROM Employee");
            while (rs.next()) {
//                String codes = rs.getString("Code");
//                String description = rs.getString("Description");
//                double price = rs.getDouble("Price");

//                Product p;
//                           
//                p = new Product();
//                p.setCode(codes);
//                p.setDescription(description);
//                p.setPrice(price);
//                products.add(p);
                
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