package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {

    protected String handle, message;
    protected final int id;
    protected static int numPosts = 0;
    protected List<Comment> responseComments = new ArrayList<>();
    protected List<Endorsement> responseEndorsements = new ArrayList<>();
    protected static List<Post> allPosts = new ArrayList<>();
    protected boolean isEndorsement = false;

    /**
     * @param numPosts
     */
    public static void setNumPosts(int numPosts) {
        Post.numPosts = numPosts;
    }

    /**
     * @return A list containing every post
     */
    public static List<Post> getAllPosts() {
        return allPosts;
    }

    /**
     * @param handle
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return A boolean value whether the post is an endorsement or not
     */
    public boolean getIsEndorsement() {
        return isEndorsement;
    }

    /**
     * @param endorsement
     */
    public void setIsEndorsement(boolean endorsement) {
        isEndorsement = endorsement;
    }

    /**
     * @return A list of comments related to this post
     */
    public List<Comment> getResponseComments() {
        return responseComments;
    }

    /**
     * @param newComment
     */
    public void setResponseComments(Comment newComment) {
        responseComments.add(newComment);
    }

    /**
     * @return A list of all endorsements related to this post
     */
    public List<Endorsement> getResponseEndorsements() {
        return responseEndorsements;
    }

    /**
     * @param newEndorsement
     */
    public void setResponseEndorsements(Endorsement newEndorsement) {
        responseEndorsements.add(newEndorsement);
    }

    /**
     * @return The handle of the account which created this post
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @return The message of this post
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return The ID of this post
     */
    public int getId() {
        return id;
    }

    /**
     * @param handle
     * @param message
     */
    public Post(String handle, String message) {
        this.handle = handle;
        this.message = message;
        this.id = numPosts++;
        allPosts.add(this);
        Account account = Account.searchByHandle(handle);
        account.setAccountPosts(this);
    }

    /**
     * @param handle
     */
    public Post(String handle) {
        this.handle = handle;
        this.id = numPosts++;
        allPosts.add(this);
        Account account = Account.searchByHandle(handle);
        account.setAccountPosts(this);
    }

    /**
     * Method to search a post with an ID
     *
     * @param searchId
     * @return The post instance corresponding to the ID given
     */
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

    /**
     * Method to remove a post with an ID
     *
     * @param id
     */
    public static void removePost(int id) {
        Post post = searchById(id);
        allPosts.remove(post);
        post.responseEndorsements = null;
    }

    /**
     * Method to get total number of Posts
     *
     * @return The number of posts which aren't comments or endorsements
     */
    public static int getNumberOnlyPosts() {
        int counter = 0;
        for (Object post : allPosts) {
            if (!(post instanceof Comment) && !(post instanceof Endorsement)) {
                // If it is not a comment nor an endorsement it will be a post
                counter++;
            }
        }
        return counter;
    }

    /**
     * Method to get the post with most endorsements
     *
     * @return The ID of the most endorsed post
     */
    public static int getMostEndorsedPost() {
        int id = -1;
        int mostEndorsements = 0;
        for (Post post : allPosts) {
            int endorsements = post.getResponseEndorsements().size();
            // We get how many endorsements does the post have
            if (endorsements >= mostEndorsements) {
                // Compare it to the one that has currently most endorsements
                id = post.getId();
                mostEndorsements = endorsements;
            }
        }
        return id;
    }

    /**
     * Method to empty the number of posts
     */
    public static void reset() {
        allPosts.clear();
        numPosts = 0;
    }

    /**
     * @return A String with information about the Post
     */
    @Override
    public String toString() {
        return "Post{" +
                "handle='" + handle + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
