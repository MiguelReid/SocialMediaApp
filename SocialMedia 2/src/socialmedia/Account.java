package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

    private String handle, description;
    private final int id;
    private static int idCounter = 0;
    private static List<Account> accounts = new ArrayList<>();
    private List<Post> accountPosts = new ArrayList<>();

    public void clearAccountPosts() {
        this.accountPosts = null;
    }

    /**
     * @param idCounter
     */
    public static void setIdCounter(int idCounter) {
        Account.idCounter = idCounter;
    }

    /**
     * @param accounts
     */
    public static void setAccounts(List<Account> accounts) {
        Account.accounts = accounts;
    }

    /**
     * @param handle
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The ID counter used for creating accounts
     */
    public static int getIdCounter() {
        return idCounter;
    }

    /**
     * @return A list of all accounts
     */
    public static List<Account> getAccounts() {
        return accounts;
    }

    /**
     * @return The handle of the account
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @return The description of the account
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The ID of the account
     */
    public int getId() {
        return id;
    }

    /**
     * @return A list of all posts from one account
     */
    public List<Post> getAccountPosts() {
        return accountPosts;
    }

    /**
     * @param post
     */
    public void setAccountPosts(Post post) {
        accountPosts.add(post);
    }

    /**
     * @param handle
     * @param description
     */
    public Account(String handle, String description) {
        this.handle = handle;
        this.description = description;
        this.id = idCounter++;
        accounts.add(this);
    }

    /**
     * @param handle
     */
    public Account(String handle) {
        this.handle = handle;
        this.id = idCounter++;
        accounts.add(this);
    }

    /**
     * @return The number of accounts
     */
    public static int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Method to search an Account with the handle
     *
     * @param searchHandle
     * @return The account instance corresponding to the given handle
     */
    public static Account searchByHandle(String searchHandle) {
        Account foundAccount = null;
        for (Account account : accounts) {
            String handle = account.getHandle();
            if (handle.equals(searchHandle)) {
                // Checking if the account.handle == the one we're searching
                foundAccount = account;
            }
        }
        return foundAccount;
    }

    /**
     * Method to search an Account with the id
     *
     * @param searchId
     * @return The account instance corresponding to the given ID
     */
    public static Account searchById(int searchId) {
        Account foundAccount = null;
        for (Account account : accounts) {
            int id = account.getId();
            if (id == searchId) {
                // Checking if the account.id == the one we're searching
                foundAccount = account;
            }
        }
        return foundAccount;
    }

    /**
     * Method to remove an account by its handle
     *
     * @param handle
     */
    public static void removeAccount(String handle) {
        Account account = searchByHandle(handle);
        accounts.remove(account);
        account.clearAccountPosts();
    }

    /**
     * Method to remove an account by its id
     *
     * @param id
     */
    public static void removeAccount(int id) {
        Account account = searchById(id);
        accounts.remove(account);
        account.clearAccountPosts();
    }

    /**
     * Method to get the account with most endorsements
     *
     * @return ID of the most endorsed account
     */
    public static int getMostEndorsedAccount() {
        int id = -1;
        int mostEndorsements = 0;
        for (Account account : accounts) {
            List<Post> posts = account.getAccountPosts();
            // We obtain all of the posts
            for (Post post : posts) {
                int endorsements = post.getResponseEndorsements().size();
                // check how many endorsements each post has
                if (endorsements >= mostEndorsements) {
                    // Compare them
                    id = account.getId();
                    mostEndorsements = endorsements;
                }
            }
        }
        return id;
    }

    /**
     * Method to reset the list of acounts
     */
    public static void reset() {
        accounts.clear();
        idCounter = 0;
    }

    /**
     * @return String detailing account information
     */
    @Override
    public String toString() {
        return "Account{" +
                "handle='" + handle + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
