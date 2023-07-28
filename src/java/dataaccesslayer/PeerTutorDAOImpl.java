/*
Student Name: Rohan Kim
Student Number: 041070929
Course & Section #: 23S_CST8288_011
Declaration: This is my own original work and is free from Plagiarism.
*/
package dataaccesslayer;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import transferobject.PeerTutor;

/**
 * This class a concrete class which implements PeerTutor DAO.
 * 
 * @author Rohan Kim
 */
public class PeerTutorDAOImpl implements PeerTutorDAO {

    /**
     * This method creates connection to the database
     * , checks if the input name is registered as a tutor
     * , and closes connection when it's done.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @return boolean that indicates whether the tutor is registered
     */
    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        try {
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT * FROM PeerTutor p "
                                + "WHERE p.lastName  = ?"
                                + "AND p.firstName = ?");                              
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
		rs = pstmt.executeQuery();
                if(rs.next()){
                    result = true;
                }                 
        } catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
		try{ if(con != null){ con.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
	}
        System.out.println("[ TEST / RESULT ] isPeerTutorRegistered: " + result);
	return result;
    }
    
    /**
     * This method creates connection to the database
     * , checks the input course code is valid
     * , and closes connection when it's done.
     * 
     * @param courseCode the input course code
     * @return boolean that indicates whether the course is valid
     */
    @Override
    public boolean isCourseValid(String courseCode) {
        Connection con = null;
	PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement("SELECT * FROM Course WHERE CourseCode = ?");
		pstmt.setString(1, courseCode);
                rs = pstmt.executeQuery();
                if(rs.next()){
                    result =true;
                } else {
                result = false;}
                System.out.println("[TEST] isCourseValid: "+result);
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
		try{ if(con != null){ con.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
	}
	return result;
    }

    /**
     * This method creates connection to the database
     * , checks the tutor taken course before
     * , and closes connection when it's done.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicated whether tutor taken course.
     */
    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        Connection con = null;
	PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT * FROM student s " +
                        "JOIN studentcourse sc " +
                        "on s.StudentID = sc.Student_StudentID " +
                        "WHERE s.LastName = ? " +
                        "AND s.FirstName = ? " +
                        "AND sc.Course_CourseCode = ?");
                            
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
                pstmt.setString(3, courseCode);
		rs = pstmt.executeQuery();
                if(rs.next()){
                    result = true;
                } 
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
		try{ if(con != null){ con.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
	}
        System.out.println("[ TEST / RESULT ] hasPeerTutorTakenCourse: " + result);
	return result;
    }

    /**
     * This method creates connection to the database
     * , gets the tutor's grade from the course
     * , and closes connection when it's done.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return String that indicates the grade
     */
    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String grade = "";  
        
        try {
                DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT g.GradeCode FROM Student s "
                                + "JOIN Grade g "
                                + "ON s.StudentID = g.Student_StudentID "
                                + "WHERE s.lastName = ?"
                                + "AND s.firstName = ? "
                                + "AND g.Course_CourseCode= ?");
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
                pstmt.setString(3, courseCode);
                rs = pstmt.executeQuery();
                rs.next();
                grade = rs.getString("GradeCode");
        }
	catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
		try{ if(con != null){ con.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
	}
        System.out.println("[TEST] getPeerTutorLetterGradeForCours: " +grade);
        return grade;
    }
    
    /**
     * This method creates connection to the database
     * , checks whether the tutor already assigned to the course
     * , and closes connection when it's done.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicates whether course is assigned to the tutor
     */
    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        Connection con = null;
	PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
               
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT * FROM PeerTutor pt"
                                + " JOIN PeerTutorCourse ptc"
                                + " ON pt.PeerTutorID = ptc.PeerTutor_PeerTutorID"
                                + " WHERE pt.LastName = ? "
                                + " AND pt.FirstName = ? "
                                + " AND ptc.Course_CourseCode= ?");
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
                pstmt.setString(3, courseCode);
		rs = pstmt.executeQuery();
                if(rs.next()){
                    result =true;
                } else {
                    result = false;
                }
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
		try{ if(con != null){ con.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
	}
	return result;
    }

    /**
     * This method creates connection to the database
     * , assign registered tutor to the course
     * , and closes connection when it's done.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     */
    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        Connection con = null;
	PreparedStatement pstmt = null;
        
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "INSERT INTO PeerTutorCourse "
                            + "(PeerTutor_PeerTutorID, Course_CourseCode)VALUES"
                            + "((SELECT PeerTutorID FROM PeerTutor pt" 
                            + " WHERE pt.lastName = ?" 
                            + " AND pt.firstName = ?), ?)");
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
                pstmt.setString(3, courseCode);  
                pstmt.execute();                
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
		try{ if(con != null){ con.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
	}
    }
    
    /**
     * This method calls getAllPeerTutorForCourse method
     * that gets list of tutor who assigned any course.
     * 
     * @param courseCode the input course code
     * @return List<PeerTutor> the list of peer tutors
     */
    @Override
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PeerTutor> tutors = new ArrayList<>();       
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement( 
                    "SELECT * FROM PeerTutor pt "
                + "JOIN PeerTutorCourse ptc ON pt.PeerTutorID = ptc.PeerTutor_PeerTutorID "
                + "WHERE ptc.Course_CourseCode = ?");
            pstmt.setString(1, courseCode);
            rs = pstmt.executeQuery();
                while(rs.next()){
                PeerTutor tutor = new PeerTutor();
                tutor.setPeerTutorID(rs.getInt("PeerTutorID"));
                tutor.setLastName(rs.getString("LastName"));
                tutor.setFirstName(rs.getString("FirstName"));
                tutors.add(tutor);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
		}
        return tutors;
    }
    
}
