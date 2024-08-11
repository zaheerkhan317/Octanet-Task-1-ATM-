import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = loadAccountsFromCSV("accounts.csv");
        Scanner scanner = new Scanner(System.in);

        int maxAttempts = 3;
        boolean authenticated = false;
        Account loggedInAccount = null;

        for (int attempts = 0; attempts < maxAttempts; attempts++) {
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();

            loggedInAccount = ATMMachine.login(accounts, username, enteredPin);
            if (loggedInAccount != null) {
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

        ATMMachine atmMachine = new ATMMachine(loggedInAccount);
        atmMachine.run();
    }

    public static List<Account> loadAccountsFromCSV(String fileName) {
        List<Account> accounts = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\IntelliJ\\ATM\\src\\accounts.csv"))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String username = values[0];
                double initialBalance = Double.parseDouble(values[1]);
                int pin = Integer.parseInt(values[2]);
                accounts.add(new Account(username, initialBalance, pin));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
