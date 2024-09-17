
package utils;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author canhd
 */
public class Menu extends ArrayList<String> implements IMenu{
    public Menu(){
        super();
    }

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public int getChoice() {
        showMenu();
        int choice = (new Validation()).getInteger("Enter your choice (1-" + size() + "): ", "Invalid, please try again",1,size());
        return choice;
    }

    @Override
    public void showMenu() {
        System.out.println("============================================");
        System.out.println("                 MENU");
        System.out.println("============================================");

        for (int i = 0; i < size(); i++) {
            System.out.printf("%-3d %s%n", (i + 1), get(i));
        }

        System.out.println("============================================");
    }
    
    public void enterConfirm() {
        System.out.println("Press Enter to return to the main menu...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
