package socialmedia;

public class Comment extends Post{

    private Post parentPost;


    public Comment(String handle, String message) {
        super(handle, message);
    }

    public Comment(String handle) {
        super(handle);
    }
}
