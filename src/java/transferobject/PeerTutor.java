package transferobject;

public class PeerTutor {

    // Here are the fields for a peer tutor.
    private int peerTutorID;
    private String lastName;
    private String firstName;
    
// TODO:  Add the necessary getters and setters.
    public int getPeerTutorID() {
        return peerTutorID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setPeerTutorID(int peerTutorID) {
        this.peerTutorID = peerTutorID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
}
