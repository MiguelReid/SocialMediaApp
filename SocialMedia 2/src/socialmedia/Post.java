package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    protected String handle, message;
    protected final int id;
    protected static int numPosts = 0;
    protected List<String> responses = new ArrayList<>();
    protected boolean isEndorsement = false;


    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsEndorsement() {
        return isEndorsement;
    }

    public void setIsEndorsement(boolean endorsement) {
        isEndorsement = endorsement;
    }

    //TODO append responses and getter

    public String getHandle() {
        return handle;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public Post(String handle, String message) {
        this.handle = handle;
        this.message = message;
        this.id = numPosts++;
    }

    public Post(String handle) {
        this.handle = handle;
        this.id = numPosts++;
    }

    //TODO ADD TOSTRING

    public static void main(String[] args) {

        System.out.println(numPosts);
    }
}
