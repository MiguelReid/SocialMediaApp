package socialmedia;

public class Comment extends Post {

    private int parentPostID;

    /**
     * @return The ID of the post this comment is associated with
     */
    public int getParentPostID() {
        return parentPostID;
    }

    /**
     * @param parentPostID
     */
    public void setParentPostID(int parentPostID) {
        this.parentPostID = parentPostID;
    }

    /**
     * @param handle
     * @param parentPostID
     * @param message
     */
    public Comment(String handle, int parentPostID, String message) {
        super(handle, message);
        setHandle(handle);
        setMessage(message);
        this.parentPostID = parentPostID;
        Post parentPost = Post.searchById(parentPostID);
        parentPost.setResponseComments(this);
    }

    /**
     * @param handle
     * @param parentPostID
     */
    public Comment(String handle, int parentPostID) {
        super(handle);
        setHandle(handle);
        this.parentPostID = parentPostID;
        Post parentPost = Post.searchById(parentPostID);
        parentPost.setResponseComments(this);
    }

    /**
     * Method to find the number of comments
     * @return The number of comments
     */
    public static int getNumberOnlyComments() {
        int counter = 0;

        for (Object post : allPosts) {
            if (post instanceof Comment) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return A string containign information about the comment
     */
    @Override
    public String toString() {
        return "Comment{" +
                "handle='" + handle + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
