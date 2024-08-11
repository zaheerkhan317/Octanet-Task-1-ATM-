import java.util.List;

public class ATMMachine {
    private Account account;

    public ATMMachine(Account account) {
        this.account = account;
    }

    public double getBalance() {
        return account.getBalance();
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void changePin(int newPin) {
        account.changePin(newPin);
    }

    public List<String> getTransactionHistory() {
        return account.getTransactionHistory();
    }

    public static Account login(List<Account> accounts, String username, int enteredPin) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.checkPin(enteredPin)) {
                return account;
            }
        }
        return null;
    }
}
