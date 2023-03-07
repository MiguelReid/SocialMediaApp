package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String handle, description;
    private final int id;
    private static int idCounter = 0;
    private static List<Account> accounts = new ArrayList<>();

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

    public static Account searchByHandle(String searchHandle){
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

    @Override
    public String toString() {
        return "Account{" +
                "handle='" + handle + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
