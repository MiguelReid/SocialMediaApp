package socialmedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static socialmedia.Post.getAllPosts;

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

    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

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

    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

        Endorsement endorsement = new Endorsement(handle, id);
        System.out.println("Endorsement created");
        return endorsement.getId();
    }

    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

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

    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfAccounts() {
        return Account.getNumberOfAccounts();
    }

    @Override
    public int getTotalOriginalPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalCommentPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getMostEndorsedPost() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getMostEndorsedAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void erasePlatform() {
        // TODO Auto-generated method stub

    }

    public void savePlatform(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }
}
