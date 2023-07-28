/*
Student Name: Rohan Kim
Student Number: 041070929
Course & Section #: 23S_CST8288_011
Declaration: This is my own original work and is free from Plagiarism.
*/
package businesslayer;

import dataaccesslayer.PeerTutorDAO;
import dataaccesslayer.PeerTutorDAOImpl;
import java.util.List;
import transferobject.PeerTutor;

/**
 * This business layer of the DAO pattern references 
 * the DAO object and request the data from the Database.
 * 
 * @author Rohan Kim
 */
public class PeerTutorBusinessLogic {
    private PeerTutorDAO peerTutorDAO = null;
    /**
     * qualifiedGrades are the list of sufficient grades to be a tutor.
     */
    private String[] qualifiedGrades = { "A+", "A", "A-" };
    
    /**
     * This method instantiate PeerTutorDAOImpl class
     */
    public PeerTutorBusinessLogic( ) {
        peerTutorDAO = new PeerTutorDAOImpl();
    }
    
    /**
     * This method calls isPeerTutorRegistered method 
     * that checks the input name is registered as a tutor.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @return boolean that indicates whether the tutor is registered
     */
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }
    
    /**
     * This method calls isCourseValid method 
     * that checks the input course code is valid.
     * 
     * @param courseCode the input course code
     * @return boolean that indicates whether the course is valid
     */
    public boolean isCourseValid(String courseCode) {
        return peerTutorDAO.isCourseValid(courseCode);
    }
    
    /**
     * This method calls hasPeerTutorTakenCourse method
     * that checks the tutor taken course before
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicated whether tutor taken course.
     */
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.hasPeerTutorTakenCourse(peerTutor, courseCode);
    }
    
    /**
     * This method calls getPeerTutorLetterGrdeForCourse method
     * that gets the tutor's grade from the course.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return String that indicates the grade
     */
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
    }
    

    /**
     * This method checks if the grade is sufficient to be a tutor.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicates whether the grade is sufficient to be a tutor.
     */
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
    
    /**
     * This method calls isCourseAlreadyAssignedToPeerTutot method
     * that checks whether the tutor already assigned to the course.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicates whether course is assigned to the tutor
     */
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }
    
    /**
     * This method calls assignCourseToPeerTutor method
     * that assigns registered tutor to the course.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     */
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
    }
    

    /**
     * This method calls getAllPeerTutorForCourse method
     * that gets list of tutor who assigned any course.
     * 
     * @param courseCode the input course code
     * @return List<PeerTutor> the list of peer tutors
     */
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
    
}
