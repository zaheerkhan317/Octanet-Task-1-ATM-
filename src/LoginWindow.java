import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginWindow extends JFrame {
    private List<Account> accounts;

    private JTextField usernameField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginWindow(List<Account> accounts) {
        this.accounts = accounts;
        setTitle("ATM Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);

        add(usernameLabel);
        add(usernameField);
        add(pinLabel);
        add(pinField);
        add(loginButton);
        add(messageLabel);
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            int pin = Integer.parseInt(new String(pinField.getPassword()));

            Account account = ATMMachine.login(accounts, username, pin);
            if (account != null) {
                setVisible(false);
                new ATMWindow(account).setVisible(true);
            } else {
                messageLabel.setText("Invalid username or PIN.");
            }
        }
    }
}
