/*
Student Name: Rohan Kim
Student Number: 041070929
Course & Section #: 23S_CST8288_011
Declaration: This is my own original work and is free from Plagiarism.
*/
package transferobject;

public class PeerTutor {

    /**
     * peerTutorID ID of registered peer tutor
     * lastName last name of peer tutor
     * firstName first name of peer tutor
     */
    private int peerTutorID;
    private String lastName;
    private String firstName;
    
// TODO:  Add the necessary getters and setters.
    /**
     * This gets peer tutor's id
     * @return peerTutorID 
     */
    public int getPeerTutorID() {
        return peerTutorID;
    }
    /**
     * This gets peer tutor's last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * This gets peer tutor's first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * This sets peer tutor's id
     * @param peerTutorID 
     */
    public void setPeerTutorID(int peerTutorID) {
        this.peerTutorID = peerTutorID;
    }
    /**
     * This sets peer tutor's last name
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * This sets peer tutor's first name
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
}
