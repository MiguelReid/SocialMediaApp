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

    /**
     *
     * @param idCounter
     */
    public static void setIdCounter(int idCounter) {
        Account.idCounter = idCounter;
    }

    /**
     *
     * @param accounts
     */
    public static void setAccounts(List<Account> accounts) {
        Account.accounts = accounts;
    }

    /**
     *
     * @param handle
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public static int getIdCounter() {
        return idCounter;
    }

    /**
     *
     * @return
     */
    public static List<Account> getAccounts() {
        return accounts;
    }

    /**
     *
     * @return
     */
    public String getHandle() {
        return handle;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public List<Post> getAccountPosts() {
        return accountPosts;
    }

    /**
     *
     * @param post
     */
    public void setAccountPosts(Post post) {
        accountPosts.add(post);
    }

    /**
     *
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
     *
     * @param handle
     */
    public Account(String handle) {
        this.handle = handle;
        this.id = idCounter++;
        accounts.add(this);
    }

    /**
     *
     * @return
     */
    public static int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     *
     * @param searchHandle
     * @return
     */
    public static Account searchByHandle(String searchHandle) {
        Account foundAccount = null;
        for (Account account : accounts) {
            String handle = account.getHandle();
            if (handle.equals(searchHandle)) {
                foundAccount = account;
            }
        }
        return foundAccount;
    }

    /**
     *
     * @param searchId
     * @return
     */
    public static Account searchById(int searchId) {
        Account foundAccount = null;
        for (Account account : accounts) {
            int id = account.getId();
            if (id == searchId) {
                foundAccount = account;
            }
        }
        return foundAccount;
    }

    /**
     *
     * @param handle
     */
    public static void removeAccount(String handle) {
        Account account = searchByHandle(handle);
        accounts.remove(account);
    }

    /**
     *
     * @param id
     */
    public static void removeAccount(int id) {
        Account account = searchById(id);
        accounts.remove(account);
    }

    /**
     *
     * @return
     */
    public static int getMostEndorsedAccount() {
        int id = -1;
        int mostEndorsements = 0;
        for (Account account : accounts) {
            List<Post> posts = account.getAccountPosts();
            for (Post post : posts) {
                int endorsements = post.getResponseEndorsements().size();
                if (endorsements >= mostEndorsements) {
                    id = account.getId();
                    mostEndorsements = endorsements;
                }
            }
        }
        return id;
    }

    /**
     *
     */
    public static void reset() {
        accounts.clear();
        idCounter = 0;
    }

    /**
     *
     * @return
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
