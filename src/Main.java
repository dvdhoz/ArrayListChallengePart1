import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner((System.in));
    private static MobilePhone mobilePhone = new MobilePhone("000000000");

    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printAction();
        while(!quit) {
            System.out.println("\nEnter actions: 6 to show available actions");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Shutting down....");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                   removeContact();
                   break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printAction();
                    break;
            }

        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contacts existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }

        System.out.println("Enter new contact name");
        String newName = scanner.nextLine();
        System.out.println("Enter new contact phone number: ");
        String newNumber = scanner.nextLine();
        Contacts newContact = Contacts.createContact(newName, newNumber);
        if(mobilePhone.updateContact(existingContactRecord, newContact)) {  //this line uses the updateContact from the MobilePhone class
            System.out.println("Successfully updated record");
        } else {
            System.out.println("Error updating record");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contacts existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }

        if(mobilePhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Error deleting record");
        }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contacts existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Name " + existingContactRecord.getName() + " phone number is " + existingContactRecord.getPhoneNumber());

        if(mobilePhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Error deleting record");
        }
    }

    private static void addNewContact() {
        System.out.println("New contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contacts newContact = Contacts.createContact(name, phone);  //this is referencing the Contact class but not creating a new instance of the class.  This is where the createContact method comes in...factory method
        if(mobilePhone.addNewContact(newContact)) {
            System.out.println("New contact added: " + name + ", phone = " + phone);
        } else {
            System.out.println("Cannot add, " + name + "already on file");
        }
    }

    private static void startPhone() {
        System.out.println("Starting phone....");
    }

    private static void printAction() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to shutdown\n" +
                            "1 - to print contacts\n" +
                            "2 - to add a new contact\n" +
                            "3 - to update existing contact\n" +
                            "4 - to remove existing contact\n" +
                            "5 - query contact\n" +
                            "6 - print a list of available actions");
        System.out.println("Choose your actions: ");
    }
}
