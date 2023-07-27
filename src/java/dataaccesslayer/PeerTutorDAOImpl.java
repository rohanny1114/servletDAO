package dataaccesslayer;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import transferobject.PeerTutor;

public class PeerTutorDAOImpl implements PeerTutorDAO {


    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean result = false;
        try {
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT * FROM PeerTutor p "
                                + "WHERE p.lastName  = ?"
                                + "AND p.firstName = ?");
                
                System.out.println("[ TEST / PeerTutorDAOImpl ] last name: " + peerTutor.getLastName());
                System.out.println("[ TEST / PeerTutorDAOImpl ] first name: " + peerTutor.getFirstName());
                
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
		result = pstmt.execute();
                System.out.println("[ TEST / PeerTutorDAOImpl ] SELECT result: " + result);
        } catch(SQLException e){
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
    
    @Override
    public boolean isCourseValid(String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        Connection con = null;
	PreparedStatement pstmt = null;
        boolean result = false;
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement("SELECT * FROM Course WHERE CourseCode = ?");
		pstmt.setString(1, courseCode);
		result = pstmt.execute();
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

    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        Connection con = null;
	PreparedStatement pstmt = null;
        boolean result = false;
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT * FROM Student s "
                                + "JOIN StudentCourse sc"
                                + "ON s.StudentID = sc.Student_StudentID"
                                + "WHERE s.LastName = ? "
                                + "AND s.FirstName = ?"
                                + " AND sc.Course_CourseCode= ?");
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
                pstmt.setString(3, courseCode);
		result = pstmt.execute();
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

    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
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
                grade = rs.getString(1);
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
        return grade;
    }
    
    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        Connection con = null;
	PreparedStatement pstmt = null;
        boolean result = false;
               
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "SELECT * FROM PeerTutor pt "
                                + "JOIN PeerTutorCourse ptc"
                                + "ON pt.PeerTutorID = ptc.PeerTutor_PeerTutorID"
                                + "WHERE pt.LastName = ? "
                                + "AND pt.FirstName = ?"
                                + " AND ptc.Course_CourseCode= ?");
		pstmt.setString(1, peerTutor.getLastName());
                pstmt.setString(2, peerTutor.getFirstName());
                pstmt.setString(3, courseCode);
		result = pstmt.execute();
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

    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        Connection con = null;
	PreparedStatement pstmt = null;
        try{
		DataSource ds = new DataSource();
		con = ds.createConnection();
		pstmt = con.prepareStatement(
                        "UPDATE PeerTutorCourse ptc"
                                + "SET ptc.PeerTutor_PeerTutorID"
                                + "= (SELECT PeerTutodID"
                                + "FROM PeerTutor pt"
                                + "WHERE pt.lastName = ?"
                                + "AND pt.firstName = ?)"
                                + ", ptc.Course_CourseCode = ?");
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
     * This method retrieves all ~~~
     * @param courseCode
     * @return 
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
