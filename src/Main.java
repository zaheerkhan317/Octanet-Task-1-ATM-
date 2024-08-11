import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Account> accounts; // Make this static to be accessed by static methods

    public static void main(String[] args) {
        accounts = loadAccountsFromCSV("D:\\IntelliJ\\ATM\\src\\accounts.csv");

        // Create and show the login window
        LoginWindow loginWindow = new LoginWindow(accounts);
        loginWindow.setVisible(true);
    }

    public static List<Account> loadAccountsFromCSV(String fileName) {
        List<Account> accounts = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { // Fixed file path
            // Skip the header row
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

    public static List<Account> getAccounts() {
        return accounts; // Access the static field
    }
}
