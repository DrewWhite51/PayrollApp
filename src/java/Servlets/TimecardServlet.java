/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import business_layer.Employee;
import business_layer.Timecard;
import data_access_layer.EmployeeDatabase;
import data_access_layer.TimeCardDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TimecardServlet extends HttpServlet {
    
       protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {
                       
            String successUrl = "/timecard.jsp";

   
//            GEtting the current session
            HttpSession httpSession = request.getSession(false); 

//            Getting the user in the current session
            Employee user = null;
            if(httpSession != null){
                user = (Employee)httpSession.getAttribute("currentUser");                
            }
            if(user!=null){
                // Do stuff here
            }
            
//          Getting timecards assocaited with the user of this session
            ArrayList<Timecard> newTime = (ArrayList<Timecard>) request.getSession(false).getAttribute("empTimeCards");
            for (int i = 0; i<newTime.size(); i++) {
    //                Strings to read the data from the html JSTL table
                    String str = "deleteRow";
                    String counterStr = Integer.toString(i);
                    if (request.getParameter(str+counterStr) != null){
        //             Removig the index of the timecard selected
                        newTime.remove(i);
        //                Showing results in console 
                        System.out.println(newTime);

                        System.out.println(newTime.size());
        //              Updating the page
                        getServletContext().getRequestDispatcher(successUrl).forward(request, response);

                        break;
                } else if (request.getParameter("addTimecard") != null){
                    
                    String date = request.getParameter("date");
                    int hoursWorked = Integer.parseInt(request.getParameter("empHoursWorked"));
                    int overTimeHours = Integer.parseInt(request.getParameter("empOverTimeHours"));
                 
                    Timecard newCard = new Timecard(date,user.employeeId,hoursWorked,overTimeHours);
                    

                    newTime.add(newCard);
                    
                    System.out.println(newTime);
                    
                    System.out.println(newTime.size());
                    
                    getServletContext().getRequestDispatcher(successUrl).forward(request, response);
                    
                    break;
                } else if (request.getParameter("editTimecard") != null) {
                    
                    String timeCardToEdit = request.getParameter("editTimecardNumber");
                    String newDate =  request.getParameter("editDate");
                    String newHoursStr = request.getParameter("editEmpHoursWorked");
                    String newOverTimeStr = request.getParameter("editEmpOverTimeHours");
                    
                    int newHours = Integer.parseInt(newHoursStr);
                    int newOverTimeHours = Integer.parseInt(newOverTimeStr);
                    
                    
                        
                    System.out.println("Editing timecard with a table index of: " + timeCardToEdit);
                    
                    int editIndex = Integer.parseInt(timeCardToEdit);
                    
                    
                    int arrayIndex = editIndex-1;
                    
                        
                    System.out.println(arrayIndex);
                    
                        
                    System.out.println(newTime.get(arrayIndex));
                    
//                    Setting the timecard object to the new values
                    
                    newTime.get(arrayIndex).setDate(newDate);
                    newTime.get(arrayIndex).setHoursWorked(newHours);
                    newTime.get(arrayIndex).setOvertimeHours(newOverTimeHours);
                    
                        
                    System.out.println(newTime.get(arrayIndex));
                        
                        
                        
                    
                    getServletContext().getRequestDispatcher(successUrl).forward(request, response);
                        
                       
                    break;
                    
                }
            }


       
       }
       
       
       
       

}