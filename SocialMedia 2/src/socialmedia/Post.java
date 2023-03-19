package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    protected String handle, message;
    protected final int id;
    protected static int numPosts = 0;
    protected List<Comment> responseComments = new ArrayList<>();
    protected List<Endorsement> responseEndorsements = new ArrayList<>();
    protected static List<Post> allPosts = new ArrayList<>();
    protected boolean isEndorsement = false;

    public static void setNumPosts(int numPosts) {
        Post.numPosts = numPosts;
    }
    public static List<Post> getAllPosts() {
        return allPosts;
    }

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

    //TODO check future the getters for the list

    public List<Comment> getResponseComments() {
        return responseComments;
    }

    public void setResponseComments(Comment newComment) {
        responseComments.add(newComment);
    }

    public List<Endorsement> getResponseEndorsements() {
        return responseEndorsements;
    }

    public void setResponseEndorsements(Endorsement newEndorsement) {
        responseEndorsements.add(newEndorsement);
    }

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
        allPosts.add(this);
        Account account = Account.searchByHandle(handle);
        account.setAccountPosts(this);
    }

    public Post(String handle) {
        this.handle = handle;
        this.id = numPosts++;
        allPosts.add(this);
        Account account = Account.searchByHandle(handle);
        account.setAccountPosts(this);
    }

    public static Post searchById(int searchId) {
        Post foundPost = null;
        for (Post post : allPosts) {
            int id = post.getId();
            if (id == searchId) {
                foundPost = post;
            }
        }
        return foundPost;
    }

    public static void removePost(int id) {
        Post post = searchById(id);
        allPosts.remove(post);
    }

    public static int getNumberOnlyPosts() {
        int counter = 0;

        for (Object post : allPosts) {
            if (!(post instanceof Comment) && !(post instanceof Endorsement)) {
                counter++;
            }
        }
        return counter;
    }

    public static int getMostEndorsedPost() {
        int id = -1;
        int mostEndorsements = 0;
        for (Post post : allPosts) {
            int endorsements = post.getResponseEndorsements().size();
            if (endorsements >= mostEndorsements) {
                id = post.getId();
                mostEndorsements = endorsements;
            }
        }
        return id;
    }

    public static void reset() {
        allPosts.clear();
        numPosts = 0;
    }

    @Override
    public String toString() {
        return "Post{" +
                "handle='" + handle + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
