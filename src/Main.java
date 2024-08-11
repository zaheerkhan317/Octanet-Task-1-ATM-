public class Main {
    public static void main(String[] args) {
        Account account = new Account("user123", 1000.0, 1234); // Create account with username, initial balance, and PIN
        ATMMachine atmMachine = new ATMMachine(account);
        atmMachine.run();
    }
}
