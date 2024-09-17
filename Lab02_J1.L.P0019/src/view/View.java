
package view;

import java.io.IOException;

/**
 *
 * @author canhd
 */
public class View {
    public static void main(String[] args) throws IOException {
        System.out.println("Admin access: username admin, password admin");
        System.out.println("User access: username user, password user");
        Application app = new Application();
        app.run();
    }
}
