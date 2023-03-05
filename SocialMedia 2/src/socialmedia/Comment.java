package socialmedia;

public class Comment extends Post{

    private int parentPostID;

    public int getParentPostID() {
        return parentPostID;
    }

    //TODO check if we have to see flag about isEndorsement

    public void setParentPostID(int parentPostID) {
        this.parentPostID = parentPostID;
    }

    public Comment(String handle, int parentPostID, String message) {
        super(handle, message);
        setHandle(handle);
        setMessage(message);
        this.parentPostID = parentPostID;
    }

    public Comment(String handle, int parentPostID) {
        super(handle);
        setHandle(handle);
        this.parentPostID = parentPostID;
    }

    //TODO ADD TOSTRING


}
