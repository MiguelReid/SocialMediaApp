package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String handle, message;
    private final int id;
    private static int numPosts = 0;
    private List<String> responses = new ArrayList<>();
    private boolean isEndorsement = false;

    public Post(String handle, String message) {
        this.handle = handle;
        this.message = message;
        this.id = numPosts++;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEndorsement() {
        return isEndorsement;
    }

    public void setEndorsement(boolean endorsement) {
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

    public Post(String handle) {
        this.handle = handle;
        this.id = numPosts++;
    }

    public static void main(String[] args) {

        System.out.println(numPosts);
    }
}
