import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ATMWindow extends JFrame {
    private Account account;

    private JTextArea transactionHistoryArea;
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton changePinButton;
    private JButton viewHistoryButton;
    private JButton logoutButton;

    public ATMWindow(Account account) {
        this.account = account;
        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        changePinButton = new JButton("Change PIN");
        viewHistoryButton = new JButton("View History");
        logoutButton = new JButton("Logout");

        transactionHistoryArea = new JTextArea();
        transactionHistoryArea.setEditable(false);

        depositButton.addActionListener(new DepositButtonListener());
        withdrawButton.addActionListener(new WithdrawButtonListener());
        changePinButton.addActionListener(new ChangePinButtonListener());
        viewHistoryButton.addActionListener(new ViewHistoryButtonListener());
        logoutButton.addActionListener(new LogoutButtonListener());

        add(amountLabel);
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(changePinButton);
        add(viewHistoryButton);
        add(new JLabel("Transaction History:"));
        add(new JScrollPane(transactionHistoryArea));
        add(logoutButton);
    }

    private class DepositButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            JOptionPane.showMessageDialog(null, "Deposited: " + amount);
        }
    }

    private class WithdrawButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());
            account.withdraw(amount);
            JOptionPane.showMessageDialog(null, "Withdrawn: " + amount);
        }
    }

    private class ChangePinButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newPinStr = JOptionPane.showInputDialog("Enter new PIN:");
            int newPin = Integer.parseInt(newPinStr);
            account.changePin(newPin);
            JOptionPane.showMessageDialog(null, "PIN changed successfully.");
        }
    }

    private class ViewHistoryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<String> history = account.getTransactionHistory();
            transactionHistoryArea.setText(String.join("\n", history));
        }
    }

    private class LogoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            List<Account> accounts = Main.getAccounts();
            new LoginWindow(accounts).setVisible(true);
        }
    }

}
