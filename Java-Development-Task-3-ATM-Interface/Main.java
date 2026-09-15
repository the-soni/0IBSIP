import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("=================================");
        System.out.println("          WELCOME TO ATM");
        System.out.println("=================================");

        Account loggedInAccount = null;
        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.next();

            System.out.print("Enter PIN: ");
            String pin = scanner.next();

            loggedInAccount = bank.authenticate(userId, pin);

            if (loggedInAccount != null) {
                System.out.println("\nLogin Successful!");
                break;
            }

            attempts++;

            if (attempts < 3) {
                System.out.println("Invalid User ID or PIN.");
                System.out.println("Attempts remaining: " + (3 - attempts));
            }
        }

        if (loggedInAccount == null) {
            System.out.println("\nAccess Denied!");
            System.out.println("Too many incorrect attempts.");
            scanner.close();
            return;
        }

        System.out.println("\nWelcome, " + loggedInAccount.getUserId() + "!");
        System.out.println("Account ID: " + loggedInAccount.getAccountId());
        System.out.println("Current Balance: ₹" + loggedInAccount.getBalance());

        ATM atm = new ATM(loggedInAccount, bank);
        atm.start();

        scanner.close();
    }
}