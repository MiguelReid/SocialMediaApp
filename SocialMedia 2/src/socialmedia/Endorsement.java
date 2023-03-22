package socialmedia;

public class Endorsement extends Post {

    private int parentPostID;

    /**
     *
     * @return
     */
    public int getParentPostID() {
        return parentPostID;
    }

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
     */
    public Endorsement(String handle, int parentPostID) {
        super(handle);
        setHandle(handle);
        this.parentPostID = parentPostID;
        Post parentPost = searchById(parentPostID);
        message = parentPost.getMessage();
        parentPost.setResponseEndorsements(this);
    }

    /**
     *
     * @return
     */
    public static int getNumberOnlyEndorsement() {
        int counter = 0;

        for (Object post : allPosts) {
            if (post instanceof Endorsement) {
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
        return "Endorsement{" +
                "handle='" + handle + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
