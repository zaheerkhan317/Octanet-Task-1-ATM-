public class Main {
    public static void main(String[] args) {
        Account account = new Account(1000.0, 1234); // Create account with initial balance and PIN
        ATMMachine atmMachine = new ATMMachine(account);
        atmMachine.run();
    }
}
