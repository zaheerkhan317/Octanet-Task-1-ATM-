import java.util.Scanner;

public class ATMMachine {
    private Account account;

    public ATMMachine(Account account) {
        this.account = account;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 3;
        boolean authenticated = false;

        // Login process
        for (int attempts = 0; attempts < maxAttempts; attempts++) {
            System.out.print("Enter Username: ");
            String enteredUsername = scanner.next();
            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();
            if (account.checkCredentials(enteredUsername, enteredPin)) {
                authenticated = true;
                break;
            } else {
                System.out.println("Incorrect username or PIN. Try again.");
            }
        }

        if (!authenticated) {
            System.out.println("Maximum attempts exceeded. Exiting.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Machine:");
            System.out.println("1. Account Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Account Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    account.changePin(newPin);
                    System.out.println("PIN changed successfully.");
                    break;
                case 5:
                    account.printTransactionHistory();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting. Thank you for using the ATM Machine.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
