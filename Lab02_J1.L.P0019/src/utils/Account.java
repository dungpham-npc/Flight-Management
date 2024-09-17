
package utils;

/**
 *
 * @author canhd
 */
public class Account {
    
    public enum Role {
        ADMIN, // Represents administrators
        USER   // Represents regular users or airline staff
    }

    private final String username;
    private final String password;
    private final Role role;

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
    
    public boolean authenticateAccount(String password) {
        return this.getPassword().equals(password);
    }

}
