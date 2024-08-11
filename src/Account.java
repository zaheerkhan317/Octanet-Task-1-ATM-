import java.util.ArrayList;

public class Account {
    private String username;
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    public Account(String username, double initialBalance, int pin) {
        this.username = username;
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: " + initialBalance);
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public boolean checkPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void changePin(int newPin) {
        this.pin = newPin;
        transactionHistory.add("PIN changed");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}
