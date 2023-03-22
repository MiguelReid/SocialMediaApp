package socialmedia;

public class Comment extends Post {

    private int parentPostID;

    /**
     *
     * @return
     */
    public int getParentPostID() {
        return parentPostID;
    }

    //TODO check if we have to see flag about isEndorsement

    /**
     *
     * @param parentPostID
     */
    public void setParentPostID(int parentPostID) {
        this.parentPostID = parentPostID;
    }

    /**
     *
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
     *
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
     *
     * @return
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
     *
     * @return
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
