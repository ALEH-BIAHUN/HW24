import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static final String LAST_NAME_REGEX = "[A-z]+";
    private static final String NUMBER_REGEX = "\\d{11}|\\d{9}";

    private static Map<String, String> phoneBook = new TreeMap<String,String>();

    public static void main(String[] args) {

        System.out.println("\n\t Welcome to the program: Phone Book \n");

        while (true) {
            System.out.println("\t Enter your last name or phone number or commands: print exit.\n");

            String input = new Scanner(System.in).nextLine();
            if (input.matches("exit")) {
                System.out.println("Bye - bye");
                return;
            } else if (input.matches("print")) {
                print();
            } else if (input.matches(LAST_NAME_REGEX)) {
                addName(input);
            } else if (input.replaceAll("\\D+", "").matches(NUMBER_REGEX)) {
                addPhoneNumber(input.replaceAll("\\D+", ""));
            } else {
                System.out.println("Invalid command " + input + " , please try again.");
            }
        }
    }
    public static void addName(String name) {
        if (phoneBook.containsKey(name)) {
            System.out.println("The contact " + name + "has already been added to the phone book.");
            return;
        }
        System.out.println("Enter the phone number for the contact " + name);
        String phoneNumber = new Scanner(System.in).nextLine();
        phoneNumber = phoneNumber.replaceAll("\\D+", "");
        if (!phoneNumber.matches(NUMBER_REGEX)) {
            System.out.println("Invalid phone number");
            return;
        }
        if(phoneNumber.length() == 11) {
            phoneNumber = phoneNumber.substring(2);
        }
        if(phoneBook.containsValue(phoneNumber)) {
            System.out.println("The number " + phoneNumber + " is already assigned");
            return;
        }
        phoneBook.put(name, phoneNumber);
        System.out.println("The contact " + name + " with the number " + phoneNumber + " has been added to the phone book.");
    }

    public static void addPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() >= 11) {
            phoneNumber = phoneNumber.substring(2);
        }
        if (phoneBook.containsValue(phoneNumber)) {
            System.out.println("The phone number " + phoneNumber + " is already assigned");
            return;
        }
        System.out.println("Enter the name for the contact " + phoneNumber);
        String name = new Scanner(System.in).nextLine();
        if(!name.matches(LAST_NAME_REGEX)) {
            System.out.println("You did not enter a name, please try again.");
            return;
        }
        if(phoneBook.containsKey(name)) {
            System.out.println("The contact " + name + " has already been added to the phone book.");
            return;
        }
        phoneBook.put(name, phoneNumber);
        System.out.println("The contact " + name + " with the number " + phoneNumber + " has been successfully added to the phone book.");
    }

    public static void print() {
            if (phoneBook.isEmpty()) {
                System.out.println("No phone number found");
                return;
            }
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                System.out.println("Abonament: " + entry.getKey() + ", number: " + entry.getValue());
            }
    }
}