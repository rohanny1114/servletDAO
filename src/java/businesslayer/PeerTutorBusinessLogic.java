package businesslayer;

import dataaccesslayer.PeerTutorDAO;
import dataaccesslayer.PeerTutorDAOImpl;
import java.util.List;
import transferobject.PeerTutor;

public class PeerTutorBusinessLogic {
    private PeerTutorDAO peerTutorDAO = null;
    private String[] qualifiedGrades = { "A+", "A", "A-" };
    
    public PeerTutorBusinessLogic( ) {
        // TODO:  Add your code here.  Need to instantiate a DAO object here.
    peerTutorDAO = new PeerTutorDAOImpl();
    }
    
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
    return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }
    
    public boolean isCourseValid(String courseCode) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
        return peerTutorDAO.isCourseValid(courseCode);
    }
    
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
        return peerTutorDAO.hasPeerTutorTakenCourse(peerTutor, courseCode);
    }
    
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
        return peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
    }
    
    // R: (Created) Validate grade
    public boolean validateGrade(PeerTutor peerTutor, String courseCode){
        String grade = getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
        boolean isQualified = false;
        for (String g : qualifiedGrades) {
	if (grade.equals(g)) {
		System.out.println("[SYSTEM] Grade Qualifies.");
                isQualified = true;
            }
        }
        return isQualified;
    }
    
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }
    
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
        peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
    }
    

    
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        // TODO:  Add your code here.  Need to call the appropriate DAO method.
        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
    
}
