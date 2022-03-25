package data_access_layer;

import business_layer.Employee;
import business_layer.HourlyEmployee;
import business_layer.SalaryEmployee;

import java.util.ArrayList;

public class EmployeeDatabase {
    // Initializing Arraylist for all employees
    static ArrayList<Employee> employee_arr = new ArrayList<Employee>();
//    Initializing ArrayLists for hourly and salaries employees
    static ArrayList<HourlyEmployee> hourly_employees_arr = new ArrayList<HourlyEmployee>();
    static ArrayList<SalaryEmployee> salary_employees_arr = new ArrayList<SalaryEmployee>();
// Instantiating instances of SalaryEmployees
    static SalaryEmployee s1 = new SalaryEmployee(100000,"Drew", "White", 1, 6789, "User4", "user4");
    static SalaryEmployee s2 = new SalaryEmployee(70000,"Dylan", "White", 2, 5837, "User2", "user2");
    static SalaryEmployee s3 = new SalaryEmployee(50000,"Rachael", "White", 3, 3813, "User3", "user3");
// Instantiating instances of Hourly Employees
    static HourlyEmployee h1 = new HourlyEmployee(20,30,"Ali", "Vanzant",4,3795, "User1", "user1");
    static HourlyEmployee h2 = new HourlyEmployee(30,50,"Deonna", "Hunt",5,3859, "User5", "user5");
    static HourlyEmployee h3 = new HourlyEmployee(40,60,"Rich", "White",6,4810, "User6", "user6");
    
    static {
        salary_employees_arr.add(s1);
        salary_employees_arr.add(s2);
        salary_employees_arr.add(s3);
        hourly_employees_arr.add(h1);
        hourly_employees_arr.add(h2);
        hourly_employees_arr.add(h3);

        employee_arr.add(s1);
        employee_arr.add(s2);
        employee_arr.add(s3);
        employee_arr.add(h1);
        employee_arr.add(h2);
        employee_arr.add(h3);
    }
    

// Method that returns an ArrayList for all hourly employeees
    public static ArrayList<HourlyEmployee> get_hourly_employees() {
        return hourly_employees_arr;
    }
// Method that returns an ArrayList for all salaried employees
    public static ArrayList<SalaryEmployee> get_salaried_employees() {return salary_employees_arr;}
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