package view;

import utils.Menu;
import controller.Controller;
import java.io.IOException;
import utils.Account;
import utils.AccountDaoImpl;
import utils.Validation;

public class Application {

    private Controller controller;
    private Validation validation;
    private AccountDaoImpl accountManager;
    private Menu menu;

    public Application() {
        this.controller = new Controller();
        this.validation = new Validation();
        this.accountManager = new AccountDaoImpl();
        this.menu = new Menu();
    }

    public void run() throws IOException {
        Account admin = new Account("admin", "admin", Account.Role.ADMIN);
        accountManager.addAccount(admin);
        Account user = new Account("user", "user", Account.Role.USER);
        accountManager.addAccount(user);
        boolean isAuthenticated = false;

        while (!isAuthenticated) {
            System.out.println("Please provide account information");
            String username = validation.getString("Enter username: ", "Should not be blank, please try again", false);
            String password = validation.getString("Enter password: ", "Should not be blank, please try again", false);

            Account foundAccount = accountManager.get(username);

            if (foundAccount != null && foundAccount.authenticateAccount(password)) {
                // Authentication successful
                isAuthenticated = true;

                // Perform actions based on user role
                if (foundAccount.getRole() == Account.Role.ADMIN) {
                    performAdminMenuActions(foundAccount);
                } else {
                    performUserMenuActions(foundAccount);
                }
            } else {
                // Authentication failed
                System.out.println("Authentication failed. Please check your credentials.");
            }
        }
    }

    private void performAdminMenuActions(Account account) throws IOException {
        System.out.println("Welcome, " + account.getUsername() + "! (Admin access)");

        // Initialize menus and controllers for admin

        boolean exitRequested = false;
        boolean exitRequestedForSubmenus = false;

        while (!exitRequested) {
            menu.clear();

            menu.add("Flight schedule management"); // This function is for admin
            menu.add("Passenger reservation and booking");
            menu.add("Passenger check-in and seat allocation"); // This function is for admin
            menu.add("Crew management"); // This function is for admin
            menu.add("Save data of current session to file"); // This function is for admin
            menu.add("Print Flight list from file"); // This function is for admin
            menu.add("Exit");

            switch (menu.getChoice()) {
                case 1:
                    while (!exitRequestedForSubmenus) {
                        menu.clear();

                        menu.add("Add flight");
                        menu.add("Get flight");
                        menu.add("Update flight");
                        menu.add("Remove flight");
                        menu.add("Show all flights");
                        menu.add("Back to main menu");

                        switch (menu.getChoice()) {
                            case 1:
                                controller.addFlight();
                                menu.enterConfirm();
                                break;
                            case 2:
                                controller.getFlight();
                                menu.enterConfirm();
                                break;
                            case 3:
                                controller.updateFlight();
                                menu.enterConfirm();
                                break;
                            case 4:
                                controller.removeFlight();
                                menu.enterConfirm();
                                break;
                            case 5:
                                controller.showAllFlights();
                                menu.enterConfirm();
                                break;
                            case 6:
                                exitRequestedForSubmenus = true;
                                break;
                        }
                    }
                    exitRequestedForSubmenus = false;
                    break;

                case 2:
                    while (!exitRequestedForSubmenus) {
                        menu.clear();

                        menu.add("Add reservation to desired flight");
                        menu.add("Show all reservations");
                        menu.add("Back to main menu");

                        switch (menu.getChoice()) {
                            case 1:
                                controller.addReservation();
                                menu.enterConfirm();
                                break;
                            case 2:
                                controller.showAllReservations();
                                menu.enterConfirm();
                            case 3:
                                exitRequestedForSubmenus = true;
                                break;
                        }
                    }
                    exitRequestedForSubmenus = false;
                    break;

                case 3:
                    while (!exitRequestedForSubmenus) {
                        menu.clear();

                        menu.add("Select seat for reservation");
                        menu.add("Generate boarding pass");
                        menu.add("Back to main menu");

                        switch (menu.getChoice()) {
                            case 1:
                                controller.selectSeat();
                                menu.enterConfirm();
                                break;
                            case 2:
                                controller.flightCheckIn();
                                menu.enterConfirm();
                                break;
                            case 3:
                                exitRequestedForSubmenus = true;
                                break;
                        }
                    }
                    exitRequestedForSubmenus = false;
                    break;

                case 4:
                    controller.assignCrewToFlight();
                    menu.enterConfirm();
                    break;

                case 5:
                    while (!exitRequestedForSubmenus) {
                        menu.clear();
                        menu.add("Save flights data to file");
                        menu.add("Save reservations data to file");
                        menu.add("Back to main menu");

                        switch (menu.getChoice()) {
                            case 1:
                                controller.saveFlightsDataToFile();
                                System.out.println("Saving successfully!");
                                menu.enterConfirm();
                                break;
                            case 2:
                                controller.saveReservationsDataToFile();
                                System.out.println("Saving successfully!");
                                menu.enterConfirm();
                                break;
                            case 3:
                                exitRequestedForSubmenus = true;
                                break;
                        }
                    }
                    exitRequestedForSubmenus = false;
                    break;

                case 6:
                    controller.showFlightsWithDepartureDateDescending();
                    menu.enterConfirm();
                    break;

                case 7: // Exit option
                    exitRequested = true;
                    break;
            }
        }
    }

    private void performUserMenuActions(Account account) {
        System.out.println("Welcome, " + account.getUsername() + "! (User access)");

        boolean exitRequested = false;
        boolean exitRequestedForSubmenus = false;

        while (!exitRequested) {
            menu.clear();
            menu.add("Passenger reservation and booking");
            menu.add("Report");
            menu.add("Save to file");
            menu.add("Exit");

            switch (menu.getChoice()) {

                case 1:
                    while (!exitRequestedForSubmenus) {
                        menu.clear();

                        menu.add("Add reservation to desired flight");
                        menu.add("Back to main menu");

                        switch (menu.getChoice()) {
                            case 1:
                                controller.addReservation();
                                menu.enterConfirm();
                                break;
                            case 2:
                                exitRequestedForSubmenus = true;
                                break;
                        }
                    }
                    exitRequestedForSubmenus = false;
                    break;
                    
                case 2:
                    while (!exitRequestedForSubmenus) {
                        menu.clear();

                        menu.add("Retrieve flights scheduled for a particular departure date");
                        menu.add("Show available flights in current date");
                        menu.add("Back to main menu");

                        switch (menu.getChoice()) {
                            case 1:
                                controller.showFlightsInSpecificDepartureDate();
                                menu.enterConfirm();
                                break;
                            case 2:
                                controller.showFlightsInCurrentDate();
                                menu.enterConfirm();
                                break;
                            case 3:
                                exitRequestedForSubmenus = true;
                                break;
                        }
                    }
                    exitRequestedForSubmenus = false;
                    break;

                case 3:
                    // Save to file
                    menu.enterConfirm();
                    break;

                case 4: // Exit option
                    exitRequested = true;
                    break;
            }
        }
    }
}

