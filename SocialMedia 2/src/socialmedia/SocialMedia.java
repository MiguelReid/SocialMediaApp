package socialmedia;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SocialMedia implements SocialMediaPlatform {

    /**
     * @param handle account's handle.
     * @return The ID of the account
     * @throws IllegalHandleException
     * @throws InvalidHandleException
     */
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        Account account = new Account(handle);
        return account.getId();
    }

    /**
     * @param handle      account's handle.
     * @param description account's description.
     * @return The ID of the account
     * @throws IllegalHandleException
     * @throws InvalidHandleException
     */
    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        Account account = new Account(handle, description);
        System.out.println("Account created");

        return account.getId();
    }

    /**
     * @param id ID of the account.
     * @throws AccountIDNotRecognisedException
     */
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        Account.removeAccount(id);
        System.out.println("Account removed");
    }

    /**
     * @param handle account's handle.
     * @throws HandleNotRecognisedException
     */
    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        Account.removeAccount(handle);
        System.out.println("Account removed");
    }

    /**
     * @param oldHandle account's old handle.
     * @param newHandle account's new handle.
     * @throws HandleNotRecognisedException
     * @throws IllegalHandleException
     * @throws InvalidHandleException
     */
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        Account account = Account.searchByHandle(oldHandle);
        account.setHandle(newHandle);
        System.out.println("Account handle changed, new handle -> " + account.getHandle());
    }

    /**
     * @param handle      handle to identify the account.
     * @param description new text for description.
     * @throws HandleNotRecognisedException
     */
    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        Account account = Account.searchByHandle(handle);
        account.setDescription(description);
        System.out.println("Account description changed, new desc -> " + account.getDescription());
    }

    /**
     * @param handle handle to identify the account.
     * @return A description of the account
     * @throws HandleNotRecognisedException
     */
    public String showAccount(String handle) throws HandleNotRecognisedException {
        Account account = Account.searchByHandle(handle);
        return account.toString();
    }

    /**
     * @param handle  handle to identify the account.
     * @param message post message.
     * @return The ID of the post
     * @throws HandleNotRecognisedException
     * @throws InvalidPostException
     */
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        Post post = new Post(handle, message);
        System.out.println("Post created");
        return post.getId();
    }

    /**
     * @param handle of the account endorsing a post.
     * @param id     of the post being endorsed.
     * @return The ID of the endorsement
     * @throws HandleNotRecognisedException
     * @throws PostIDNotRecognisedException
     * @throws NotActionablePostException
     */
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        Endorsement endorsement = new Endorsement(handle, id);
        System.out.println("Endorsement created");
        return endorsement.getId();
    }

    /**
     * @param handle  of the account commenting a post.
     * @param id      of the post being commented.
     * @param message the comment post message.
     * @return The ID of the comment
     * @throws HandleNotRecognisedException
     * @throws PostIDNotRecognisedException
     * @throws NotActionablePostException
     * @throws InvalidPostException
     */
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        Comment comment = new Comment(handle, id, message);
        System.out.println("Comment created");
        return comment.getId();
    }

    /**
     * @param id ID of post to be removed.
     * @throws PostIDNotRecognisedException
     */
    public void deletePost(int id) throws PostIDNotRecognisedException {
        Post.removePost(id);
        System.out.println("Post removed");
    }

    /**
     * @param id of the post to be shown.
     * @return A description of the post
     * @throws PostIDNotRecognisedException
     */
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        Post post = Post.searchById(id);
        return post.toString();
    }

    /**
     * @param id of the post to be shown.
     * @return A description of the post and all related comments and endorsements
     * @throws PostIDNotRecognisedException
     * @throws NotActionablePostException
     */
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        StringBuilder builder = new StringBuilder("<pre>\n");
        Post post = Post.searchById(id);
        builder.append(getCommentDetails(post, 0));
        builder.append("</pre>");
        return builder;
    }

    /**
     * @param post
     * @param indentCounter
     * @return A description of all comments related to a post
     */
    private StringBuilder getCommentDetails(Post post, int indentCounter) {
        List<Comment> comments = post.getResponseComments();

        StringBuilder builder = new StringBuilder();

        builder.append(auxPostDetails(post, indentCounter));

        for (Comment comment : comments) {
            indentCounter++;
            builder.append(getCommentDetails(comment, indentCounter));
            // Per comment we print it's details
        }
        return builder;
    }

    /**
     * @param post
     * @param indentCounter
     * @return A formatted description of a post or subclass
     */
    private String auxPostDetails(Post post, int indentCounter) {
        int numEndorsements = post.getResponseEndorsements().size();
        int numComments = post.getResponseComments().size();

        StringBuilder indent = new StringBuilder();

        indent.append("\t".repeat(Math.max(0, indentCounter)));
        // We indent it depending on what level of children it is

        String suffix = indent + "|\n" + indent + "| > ";

        if (numComments == 0) suffix = "";

        return "ID: " + post.getId() + "\n" + indent + "Account: " + post.getHandle() + "\n" + indent +
                "No. endorsements: " + numEndorsements + " | No. comments: " + numComments + "\n" + indent +
                post.getMessage() + "\n" + suffix;
    }

    /**
     * @return The number of accounts
     */
    @Override
    public int getNumberOfAccounts() {
        return Account.getNumberOfAccounts();
    }

    /**
     * @return The number of posts
     */
    @Override
    public int getTotalOriginalPosts() {
        return Post.getNumberOnlyPosts();
    }

    /**
     * @return The number of endorsements
     */
    @Override
    public int getTotalEndorsmentPosts() {
        return Endorsement.getNumberOnlyEndorsement();
    }

    /**
     * @return The number of comments
     */
    @Override
    public int getTotalCommentPosts() {
        return Comment.getNumberOnlyComments();
    }

    /**
     * @return The ID of the most endorsed post
     */
    public int getMostEndorsedPost() {
        return Post.getMostEndorsedPost();
    }

    /**
     * @return The id of most endorsed account
     */
    public int getMostEndorsedAccount() {
        return Account.getMostEndorsedAccount();
    }

    /**
     *
     */
    public void erasePlatform() {
        Account.reset();
        Post.reset();
    }

    /**
     * @param filename location of the file to be saved
     * @throws IOException
     */
    public void savePlatform(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((filename + ".ser")))) {
            oos.writeObject(Account.getAccounts());
            oos.writeObject(Account.getIdCounter());
            oos.writeObject(Post.getAllPosts());
            // We write the list and counters to a file
        }
    }

    /**
     * @param filename location of the file to be loaded
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        ArrayList<Account> accounts;
        int idCounter;
        ArrayList<Post> allPosts;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".ser"))) {
            accounts = (ArrayList<Account>) in.readObject();
            idCounter = (Integer) in.readObject();
            allPosts = (ArrayList<Post>) in.readObject();
            // We read the objectsin the file
        }

        Post.allPosts = allPosts;
        // Load the list
        Account.setIdCounter(idCounter);
        Account.setAccounts(accounts);
        // Setting the counters
    }
}
