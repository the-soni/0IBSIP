import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Account account;
    private Bank bank;
    private ArrayList<Transaction> transactions;
    private Scanner scanner;

    public ATM(Account account, Bank bank) {
        this.account = account;
        this.bank = bank;
        this.transactions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void withdraw() {

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (account.withdraw(amount)) {
            transactions.add(
                    new Transaction("WITHDRAW", amount, "Cash withdrawn")
            );

            System.out.println("Withdrawal successful.");
            System.out.println("Remaining Balance: ₹" + account.getBalance());

        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void deposit() {

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        account.deposit(amount);

        transactions.add(
                new Transaction("DEPOSIT", amount, "Cash deposited")
        );

        System.out.println("Deposit successful.");
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    private void transfer() {

        System.out.print("Enter recipient Account ID: ");
        String recipientId = scanner.next();

        Account recipient = bank.getAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient.getAccountId().equals(account.getAccountId())) {
            System.out.println("You cannot transfer money to your own account.");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (account.withdraw(amount)) {

            recipient.deposit(amount);

            transactions.add(
                    new Transaction(
                            "TRANSFER",
                            amount,
                            "Transferred to Account " + recipientId
                    )
            );

            System.out.println("Transfer successful.");
            System.out.println("Remaining Balance: ₹" + account.getBalance());

        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void showTransactionHistory() {

        System.out.println("\n====== TRANSACTION HISTORY ======");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }

        System.out.println("=================================");
    }
}