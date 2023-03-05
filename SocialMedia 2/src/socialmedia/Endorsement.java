package socialmedia;

public class Endorsement extends Post {

    private int parentPostID;

    public int getParentPostID() {
        return parentPostID;
    }

    public void setParentPostID(int parentPostID) {
        this.parentPostID = parentPostID;
    }

    //TODO

    public Endorsement(String handle, int parentPostID) {
        super(handle);
        setHandle(handle);
        this.parentPostID = parentPostID;
        Post parentObject = searchById(parentPostID);
        message = parentObject.getMessage();
    }

    /*
    public Endorsement(String handle, int parentPostID ,String message) {
        super(handle, message);
        setHandle(handle);
        setMessage(message);
        this.parentPostID = parentPostID;
    }
    */
}
