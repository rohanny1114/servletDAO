/*
Student Name: Rohan Kim
Student Number: 041070929
Course & Section #: 23S_CST8288_011
Declaration: This is my own original work and is free from Plagiarism.
*/
package viewlayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslayer.PeerTutorBusinessLogic;
import transferobject.PeerTutor;

/**
 * This class processes HTTP requests, composes HTML response on Servlet,
 * runs logic using if statement that accesses the database 
 * by calling its appropriate methods on each decision.
 * 
 * @author Rohan Kim
 */
public class PeerTutorServlet extends HttpServlet {

    private PeerTutor pt = new PeerTutor();
    private String errMsg;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {                    
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PeerTutorServler</title>");
            out.println("</head>");
            out.println("<body BGCOLOR=\"#FDF5E6\">");
            out.println("<h1>Servlet PeerTutorServlet at/PeerTutorServletDAO</h1>");
            
            out.println("</body>");
            out.println("</html>");
        
            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic();
            
            pt.setLastName(request.getParameter("lastName"));
            pt.setFirstName(request.getParameter("firstName"));
            String lastName = pt.getLastName();
            String firstName = pt.getFirstName();
            String courseCode = request.getParameter("courseCode");
            
            if (!logic.isPeerTutorRegistered(pt)) {
                System.out.println("[ TEST / PeerTutorServlet ] person is not registered as a peer tutor");
                errMsg = "The person is not registered as a peer tutor.";
                out.println("<ul>");
                out.println("<li>Last Name: " + lastName + "</li>");
                out.println("<li>Fisrt Name: " + firstName + "</li>");
                out.println("</ul>");
                out.println("<div>Error: " + errMsg + "</div>");
                
            } else if (!logic.isCourseValid(courseCode)) {
                System.out.println("[ TEST / PeerTutorServlet ] course is not valid");
                errMsg = "The course is not valid";
                out.println("<ul>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<div>Error: " + errMsg + "</div>");
                
            } else if (!logic.hasPeerTutorTakenCourse(pt, courseCode)) {
                System.out.println("[ TEST / PeerTutorServlet ] peer tutor has not taken the course");
                errMsg = "The peer tutor has not taken the course.";
                out.println("<ul>");
                out.println("<li>Last Name: " + lastName + "</li>");
                out.println("<li>Fisrt Name: " + firstName + "</li>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<div>Error: " + errMsg + "</div>");
                
            } else {
                if (!logic.validateGrade(pt, courseCode)) {
                    System.out.println("[ TEST / PeerTutorServlet ] grade obtained by the peer tutor for the course is not sufficient");
                    errMsg = "The letter grade obtained by the peer tutor for the course is not sufficient.";
                    out.println("<ul>");
                    out.println("<li>Last Name: " + lastName + "</li>");
                    out.println("<li>Fisrt Name: " + firstName + "</li>");
                    out.println("<li>Course Code: " + courseCode + "</li>");
                    out.println("</ul>");
                    out.println("<div>Error: " + errMsg + "</div>");
                    
                } else if (logic.isCourseAlreadyAssignedToPeerTutor(pt, courseCode)) {
                    System.out.println("[ TEST / PeerTutorServlet ] tutor is already assigned to the course");
                    errMsg = "The tutor is already assigned to the course.";
                    out.println("<ul>");
                    out.println("<li>Last Name: " + lastName + "</li>");
                    out.println("<li>Fisrt Name: " + firstName + "</li>");
                    out.println("<li>Course Code: " + courseCode + "</li>");
                    out.println("</ul>");
                    out.println("<div>Error: " + errMsg + "</div>");
                    
                } else {
                    logic.assignCourseToPeerTutor(pt, courseCode);
                    System.out.println("[ TEST / PeerTutorServlet ] assign the course to the peer tutor");
                    out.println("<table border=\"1\">");
                    out.println("<caption>Table of Peer Tutor for " + courseCode + "</caption>");
                    List<PeerTutor> tutors = logic.getAllPeerTutorsForCourse(courseCode);
                    out.println("<tr>");
                    out.println("<th>Tutor ID</th>");
                    out.println("<th>Last Name</th>");
                    out.println(" <th>First Name</th>");
                    out.println("</tr>");
                    out.println("<tr>");                    
                    for (PeerTutor tutor : tutors) {
                        out.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>",
                                tutor.getPeerTutorID(), tutor.getLastName(), tutor.getFirstName());
                    }
                    out.println("</tr>");
                }
            }
            out.println("</body>");
            out.println("</html>");
            
        }
    }


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
