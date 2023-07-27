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

public class PeerTutorServlet extends HttpServlet {

    private PeerTutor pt = new PeerTutor();
    private String courseCode;
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
            String lastName = request.getParameter("lastName");
            String fisrtName = request.getParameter("firstName");
            String courseCode = request.getParameter("courseCode");

            
            System.out.println("[ TEST / PeerTutorServlet ] last name: "+lastName);
            System.out.println("[ TEST / PeerTutorServlet ] first name: "+fisrtName);
            System.out.println("[ TEST / PeerTutorServlet ] course code: "+courseCode);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {                    
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PeerTutorServler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PeerTutorServlet at/PeerTutorServletDAO</h1>");
            
            out.println("</body>");
            out.println("</html>");

            out.println("<h2>TESTING</h2>");
        
            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic();
            
            if (!logic.isPeerTutorRegistered(pt)) {
                System.out.println("[ TEST / PeerTutorServlet ] person is not registered as a peer tutor");
                errMsg = "The person is not registered as a peer tutor.";
                out.println("<ul>");
                out.println("<li>Last Name: " + lastName + "</li>");
                out.println("<li>Fisrt Name: " + fisrtName + "</li>");
                out.println("</ul>");
                out.println("<div>Error: " + errMsg + "</div>");
            } else if (!logic.isCourseValid(courseCode)) {
                System.out.println("[ TEST / PeerTutorServlet ] course is not valid");
                errMsg = "The course is not valid";
                out.println("<ul>");
                out.println("<li>Last Name: " + lastName + "</li>");
                out.println("<li>Fisrt Name: " + fisrtName + "</li>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<div>Error: " + errMsg + "</div>");
            } else if (!logic.hasPeerTutorTakenCourse(pt, courseCode)) {
                System.out.println("[ TEST / PeerTutorServlet ] peer tutor has not taken the course");
                errMsg = "The peer tutor has not taken the course.";
                out.println("<ul>");
                out.println("<li>Last Name: " + lastName + "</li>");
                out.println("<li>Fisrt Name: " + fisrtName + "</li>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<div>Error: " + errMsg + "</div>");
            } else {
                if (!logic.validateGrade(pt, courseCode)) {
                    System.out.println("[ TEST / PeerTutorServlet ] grade obtained by the peer tutor for the course is not sufficient");
                    errMsg = "The letter grade obtained by the peer tutor for the course is not sufficient.";
                    out.println("<ul>");
                    out.println("<li>Last Name: " + lastName + "</li>");
                    out.println("<li>Fisrt Name: " + fisrtName + "</li>");
                    out.println("<li>Course Code: " + courseCode + "</li>");
                    out.println("</ul>");
                    out.println("<div>Error: " + errMsg + "</div>");
                } else if (logic.isPeerTutorRegistered(pt)) {
                    System.out.println("[ TEST / PeerTutorServlet ] tutor is already assigned to the course");
                    errMsg = "The tutor is already assigned to the course.";
                    out.println("<ul>");
                    out.println("<li>Last Name: " + lastName + "</li>");
                    out.println("<li>Fisrt Name: " + fisrtName + "</li>");
                    out.println("<li>Course Code: " + courseCode + "</li>");
                    out.println("</ul>");
                    out.println("<div>Error: " + errMsg + "</div>");
                } else {
                    logic.assignCourseToPeerTutor(pt, courseCode);
                    System.out.println("[ TEST / PeerTutorServlet ] assign the course to the peer tutor");

                    out.println("<table border=\"1\">");
                    out.println("<caption>Table of Peer Tutor for " + courseCode + "</caption>");
                    pt.setLastName(request.getParameter("lastName"));
                    pt.setFirstName(request.getParameter("firstName"));
                    pt.setPeerTutorID(Integer.parseInt(request.getParameter("peerTutorID")));

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
    // TODO:  Add your code here.  Make sure to use try-catch or
    //        try-with-resources statement here.  Need to instantiate a
    //        PrintWriter object which is a resource.  You can use the
    //        PrintWriter object to compose the HTML response of this
    //        servlet.  Also, need to instantiate a PeerTutorBusinessLogic
    //        object here and use it to access the database by calling its
    //        appropriate methods.  As the servlet composes the HTML response,
    //        it should use the business logic object.  You should also
    //        retrieve the request parameters here and instantiate a PeerTutor
    //        object and set its fields as needed.  Use bgcolor="#FDF5E6" for
    //        the background color of the HTML response of this servlet.
    //        Please refer to the sample projects code in Week 9.

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
