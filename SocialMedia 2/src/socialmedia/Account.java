package socialmedia;

public class Account {

    private String handle, description;
    private final int id;
    private static int numAccounts = 0;

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
        this.id = numAccounts++;
    }

    public Account(String handle) {
        this.handle = handle;
        this.id = numAccounts++;
    }

    public static void main(String[] args){
        Account george = new Account("George");
        Account mikey = new Account("Mikey");
        System.out.println(numAccounts);
    }
}
