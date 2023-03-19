package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String handle, description;
    private final int id;
    private static int idCounter = 0;
    private static List<Account> accounts = new ArrayList<>();
    private List<Post> accountPosts = new ArrayList<>();

    public static int getIdCounter() {
        return idCounter;
    }

    public static List<Account> getAccounts() {
        return accounts;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHandle() {
        return handle;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public List<Post> getAccountPosts() {
        return accountPosts;
    }

    public void setAccountPosts(Post post) {
        accountPosts.add(post);
    }

    public Account(String handle, String description) {
        this.handle = handle;
        this.description = description;
        this.id = idCounter++;
        accounts.add(this);
    }

    public Account(String handle) {
        this.handle = handle;
        this.id = idCounter++;
        accounts.add(this);
    }

    public static int getNumberOfAccounts() {
        return accounts.size();
    }

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

    public static void removeAccount(String handle) {
        Account account = searchByHandle(handle);
        accounts.remove(account);
    }

    public static void removeAccount(int id) {
        Account account = searchById(id);
        accounts.remove(account);
    }

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

    public static void reset() {
        accounts.clear();
        idCounter = 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "handle='" + handle + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
