package viewlayer;

import businesslayer.PeerTutorBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.PeerTutor;

public class PeerTutorServlet extends HttpServlet {
    private PeerTutor pt = new PeerTutor();

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
        try (PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PeerTutorServler</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            out.println("<h1>Servlet PeerTutorServlet at/PeerTutorServletDAO</h1>");
            

            out.println("<ul>");
            out.println("<li>Last Name: XXXX</li>");
            out.println("<li>Fisrt Name: XXXX</li>");
            out.println("<li>Course Code: XXXX</li>");
            out.println("</ul>");
            out.println("<div>Error: XX XXXX XXX XXXXXXXX</div>");
            
            
            out.println("<table border=\"1\">");
            out.println("<caption>Table of Peer Tutor for XXXX</caption>");
            pt.setLastName(request.getParameter("lastName"));
            pt.setFirstName(request.getParameter("firstName"));
            pt.setPeerTutorID(request.getParameter("peerTutorID"));
            
            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic(tutors);
            List<PeerTutor> tutors = logic.getAllPeerTutorsForCourse();
            out.println("<tr>");
            out.println("<th>Tutor ID</th>");
            out.println("<th>Last Name</th>");
            out.println(" <th>First Name</th>");
            out.println("</tr>");
            
            out.println("<tr>");
            for(PeerTutor tutor : tutors){
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>",
                    tutor.getPeerTutorID(), tutor.getLastName(), tutor.getFirstName());
            }          
            out.println("</tr>");
            
            
            out.println("</body>");
            out.println("</html>");       
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