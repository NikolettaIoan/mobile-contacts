package gr.aueb.cf.contacts;

import gr.aueb.cf.contacts.controller.MobileContactController;
import gr.aueb.cf.contacts.model.MobileContact;

import java.util.Scanner;

public class Main {
    private static final MobileContactController controller = new MobileContactController();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        do {
            printMenu();
            choice = getInputChoice();
            manageChoice(choice);
        } while (choice != 5);
    }

    private static void manageChoice(int choice) {
        switch (choice) {
            case 1:
                insertHandler();
                break;
            case 2:
                getContactAndPrintHandler();
                break;
            case 3:
               updateHandler();
                break;
            case 4:
                deleteHandler();
                break;
            case 5:
                System.out.println("QUIT");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private static void printMenu() {
        System.out.println("Please select one of the following");
        System.out.println("1. Insert mobile contact");
        System.out.println("2. Get and Print mobile contact");
        System.out.println("3. Update mobile contact");
        System.out.println("4. Delete mobile contact");
        System.out.println("5. Quit");
    }

    private static int getInputChoice() {
        String strChoice;
        int choice;

        strChoice = sc.nextLine();
        if(isInt(strChoice)) {
            choice = Integer.parseInt(strChoice);
        } else {
            choice = -1;
        }

        return choice;
    }

    private static  String getInputFirstName(){
        System.out.println("Please insert firstname");
        return sc.nextLine();
    }

    private static  String getInputLastName(){
        System.out.println("Please insert lastname");
        return sc.nextLine();
    }

    private static  String getInputPhoneNumber(){
        System.out.println("Please insert phone number");
        return sc.nextLine();
    }

    private static void printContact(MobileContact contact) {
        System.out.println(contact.convertToString());
    }

    public static void insertHandler() {
        String firstname;
        String lastname;
        String  phoneNumber;

        firstname = getInputFirstName().trim();
        lastname = getInputLastName().trim();
        phoneNumber = getInputPhoneNumber().trim();

        if (controller.insertController(firstname, lastname, phoneNumber)) {
            System.out.println("Contact inserted");
        } else {
            System.out.println("Contact already exists");
        }
    }

    public static void getContactAndPrintHandler() {
        String phoneNumber;
        MobileContact contact = new MobileContact();

        phoneNumber = getInputPhoneNumber();
        contact = controller.getContactController(phoneNumber);

        if (contact == null) {
            System.out.println("Contact not found");
            return;
        }

        printContact(contact);
    }

    public static void updateHandler() {
        String firstname;
        String lastname;
        String  phoneNumber;
        String inputPhoneNumber;
        MobileContact contact;

        inputPhoneNumber = getInputPhoneNumber();
        contact = controller.getContactController(inputPhoneNumber);

        if (contact != null) {
            firstname = getInputFirstName().trim();
            lastname = getInputLastName().trim();
            phoneNumber = getInputPhoneNumber().trim();

            if (controller.updateController(inputPhoneNumber, firstname, lastname,phoneNumber)) {
                System.out.println("Contact updated");
            } else {
                System.out.println("Contact not found");
            }
        } else {
            System.out.println("Contact not found");

        }
    }

    public static void deleteHandler() {
        String inputPhoneNumber;

        inputPhoneNumber = getInputPhoneNumber().trim();

        if (controller.deleteController(inputPhoneNumber)) {
            System.out.println("Contact deleted");
        } else {
            System.out.println("Contact not found");
        }

    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
