/*
Student Name: Rohan Kim
Student Number: 041070929
Course & Section #: 23S_CST8288_011
Declaration: This is my own original work and is free from Plagiarism.
*/
package dataaccesslayer;

import java.util.List;
import transferobject.PeerTutor;
/**
 * This is DAO interface which declares methods 
 * that checks tutor and course status.
 * 
 * @author Rohan Kim
 */
public interface PeerTutorDAO {
    /**
     * This abstract method checks if the input name is registered as a tutor.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @return boolean that indicates whether the tutor is registered
     */
    boolean isPeerTutorRegistered(PeerTutor peerTutor);
    /**
     * This abstract method checks the input course code is valid.
     * 
     * @param courseCode the input course code
     * @return boolean that indicates whether the course is valid
     */
    boolean isCourseValid(String courseCode);
    /**
     * This abstract method checks the tutor taken course before.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicated whether tutor taken course.
     */
    boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode);
    /**
     * This abstract method gets the tutor's grade from the course.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return String that indicates the grade
     */
    String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode);
    /**
     * This abstract method checks whether the tutor already assigned to the course.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     * @return boolean that indicates whether course is assigned to the tutor
     */
    boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode);
    /**
     * This abstract method assigns registered tutor to the course.
     * 
     * @param peerTutor the DTO that contains tutor's last and first name.
     * @param courseCode the input course code
     */
    void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCOde);
    
    /**
     * This abstract method gets list of tutor who assigned any course.
     * 
     * @param courseCode the input course code
     * @return List<PeerTutor> the list of peer tutors
     */
    List<PeerTutor> getAllPeerTutorsForCourse(String courseCode);
}

