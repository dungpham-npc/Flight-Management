package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validation implements IValidation{

    final private static Scanner sc = new Scanner(System.in);

    @Override
    public int getInteger(String inputMsg, String errorMsg, boolean canBeBlank) {
        int n;
        while (true) {
            System.out.print(inputMsg);
            String input = sc.nextLine().trim(); // Trim leading/trailing whitespace

            // Check if the input is blank and can be blank
            if (input.isEmpty() && canBeBlank) {
                // Return a sentinel value, e.g., -1, to indicate blank input
                return -1;
            }

            try {
                n = Integer.parseInt(input);
                return n;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    @Override
    public int getInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //nhập vào 1 số thực, chặn hết các trường hợp cà chớn 
    @Override
    public double getDouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    @Override
    public double getDouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //nhập vào một chuỗi kí tự, theo định dạng đc đưa vào
    //định dạng xài Regular Expression
    @Override
    public String getID(String inputMsg, String errorMsg, String format, boolean canBeBlank) {
        String id;
        boolean match;

        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();

            if (id.isEmpty() && canBeBlank) {
                return "";
            }

            match = id.matches(format);

            if (id.isEmpty() || !match) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }


    @Override
    public String getString(String inputMsg, String errorMsg, boolean canBeBlank) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();

            // Check if the input is blank and can be blank
            if (id.isEmpty() && canBeBlank) {
                // Return an empty string to indicate blank input
                return "";
            }

            if (id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

     
    
    @Override
    public boolean confirmYesNo(String welcome) {

        System.out.println(welcome);
        System.out.print("Enter 'Y' for Yes or 'N' for No: ");

        String userInput = sc.nextLine().trim().toUpperCase();

        switch (userInput) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
                return confirmYesNo(welcome); //
        }
    }
    
    @Override
    public Date getDate(String message, String errorMessage, String pattern, boolean allowNull) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (allowNull && input.isEmpty()) {
                return null; // Return null for optional input
            }

            try {
                Date date = dateFormat.parse(input);
                return date;
            } catch (ParseException e) {
                System.out.println(errorMessage);
            }
        }
    }
    
    public static boolean isBlank(String str) {
        // Check if the string is null or consists only of whitespace characters
        return str == null || str.trim().isEmpty();
    }
    
    @Override
    public boolean areDatesValid(Date startDate, Date endDate) {
        // Compare the milliseconds since epoch for both dates
        return !startDate.after(endDate) && !(startDate.getTime() > endDate.getTime());
    }

}
