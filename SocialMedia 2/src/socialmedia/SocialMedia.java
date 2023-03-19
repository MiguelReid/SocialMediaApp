package socialmedia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SocialMedia implements SocialMediaPlatform {

    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        Account account = new Account(handle);
        return account.getId();
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        Account account = new Account(handle, description);
        System.out.println("Account created");

        return account.getId();
    }

    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        Account.removeAccount(id);
        System.out.println("Account removed");
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        Account.removeAccount(handle);
        System.out.println("Account removed");
    }

    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

        Account account = Account.searchByHandle(oldHandle);
        account.setHandle(newHandle);
        System.out.println("Account handle changed");
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

        Account account = Account.searchByHandle(handle);
        account.setDescription(description);
        System.out.println("Account description changed");
    }

    public String showAccount(String handle) throws HandleNotRecognisedException {
        Account account = Account.searchByHandle(handle);
        return account.toString();
    }

    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        Post post = new Post(handle, message);
        System.out.println("Post created");
        return post.getId();
    }

    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

        Endorsement endorsement = new Endorsement(handle, id);
        System.out.println("Endorsement created");
        return endorsement.getId();
    }

    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

        Comment comment = new Comment(handle, id, message);
        System.out.println("Comment created");
        return comment.getId();
    }

    public void deletePost(int id) throws PostIDNotRecognisedException {
        Post.removePost(id);
        System.out.println("Post removed");
    }

    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        Post post = Post.searchById(id);
        return post.toString();
    }

    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        // Add parent details to StringBuilder
        StringBuilder builder = new StringBuilder("<pre>\n");
        Post post = Post.searchById(id);

        builder.append(getCommentDetails(post, 0));
        builder.append("</pre>");
        return builder;
    }

    public StringBuilder getCommentDetails(Post post, int indentCounter) {
        List<Comment> comments = post.getResponseComments();

        StringBuilder builder = new StringBuilder();

        builder.append(auxPostDetails(post, indentCounter));

        for (Comment comment : comments) {
            System.out.println(comment);
            indentCounter++;
            builder.append(getCommentDetails(comment, indentCounter));
        }
        return builder;
    }

    public String auxPostDetails(Post post, int indentCounter) {
        int numEndorsements = post.getResponseEndorsements().size();
        int numComments = post.getResponseComments().size();

        StringBuilder indent = new StringBuilder();

        indent.append("\t".repeat(Math.max(0, indentCounter)));

        String suffix = indent + "|\n" + indent + "| > ";

        if (numComments == 0) suffix = "";

        return "ID: " + post.getId() + "\n" + indent + "Account: " + post.getHandle() + "\n" + indent +
                "No. endorsements: " + numEndorsements + " | No. comments: " + numComments + "\n" + indent +
                post.getMessage() + "\n" + suffix;
    }


    @Override
    public int getNumberOfAccounts() {
        return Account.getNumberOfAccounts();
    }

    @Override
    public int getTotalOriginalPosts() {
        return Post.getNumberOnlyPosts();
    }

    @Override
    public int getTotalEndorsmentPosts() {
        return Endorsement.getNumberOnlyEndorsement();
    }

    @Override
    public int getTotalCommentPosts() {
        return Comment.getNumberOnlyComments();
    }

    public int getMostEndorsedPost() {
        return Post.getMostEndorsedPost();
    }

    public int getMostEndorsedAccount() {
        return Account.getMostEndorsedAccount();
    }

    public void erasePlatform() {
        Account.reset();
        Post.reset();
    }

    public void savePlatform(String filename) throws IOException {
        // TODO Auto-generated method stub

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream((filename + ".ser")))) {
            out.writeObject(Account.getAccounts());
            out.writeObject(Account.getIdCounter());
            out.writeObject(Post.getAllPosts());
        }
    }
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        List<Account> accounts = null;
        int idCounter = 0;
        List<Post> allPosts = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".ser"))) {

            Object obj = in.readObject();
            if (obj instanceof List<Account>)
                accounts = (List<Account>) in.readObject();

            obj = in.readObject();
            if (obj instanceof Integer)
                idCounter = (Integer) in.readObject();

            obj = in.readObject();
            if (obj instanceof List<Post>)
                allPosts = (List<Post>) in.readObject();
        }


        // TODO need to cal Post.numPosts from allPosts.length
    }
}
