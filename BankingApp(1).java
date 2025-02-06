import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

public class BankingApp extends JFrame {
    private HashMap<String, Account> accounts = new HashMap<>();
    private JTextField accountNumberField, accountHolderField, amountField, accountTypeField;
    private JTextArea transactionHistoryArea;

    public BankingApp() {
        setTitle("Banking Application");
        setLayout(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(20, 20, 120, 25);
        add(accountNumberLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(150, 20, 150, 25);
        add(accountNumberField);

        JLabel accountHolderLabel = new JLabel("Account Holder:");
        accountHolderLabel.setBounds(20, 50, 120, 25);
        add(accountHolderLabel);

        accountHolderField = new JTextField();
        accountHolderField.setBounds(150, 50, 150, 25);
        add(accountHolderField);

        JLabel accountTypeLabel = new JLabel("Account Type (Savings/Current):");
        accountTypeLabel.setBounds(20, 80, 200, 25);
        add(accountTypeLabel);

        accountTypeField = new JTextField();
        accountTypeField.setBounds(150, 80, 150, 25);
        add(accountTypeField);

        JButton createButton = new JButton("Create Account");
        createButton.setBounds(150, 110, 150, 25);
        createButton.addActionListener(new CreateAccountAction());
        add(createButton);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 140, 120, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 140, 150, 25);
        add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(20, 170, 100, 25);
        depositButton.addActionListener(new DepositAction());
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(150, 170, 100, 25);
        withdrawButton.addActionListener(new WithdrawAction());
        add(withdrawButton);

        transactionHistoryArea = new JTextArea();
        transactionHistoryArea.setBounds(20, 200, 320, 100);
        add(transactionHistoryArea);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BankingApp();
    }

    // Action for creating an account
    private class CreateAccountAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            String accountHolder = accountHolderField.getText();
            String accountType = accountTypeField.getText().toLowerCase();

            if (accounts.containsKey(accountNumber)) {
                JOptionPane.showMessageDialog(null, "Account already exists!");
                return;
            }

            if (accountType.equals("savings")) {
                accounts.put(accountNumber, new SavingsAccount(accountNumber, accountHolder, 0));
                JOptionPane.showMessageDialog(null, "Savings account created successfully!");
            } else if (accountType.equals("current")) {
                accounts.put(accountNumber, new CurrentAccount(accountNumber, accountHolder, 0));
                JOptionPane.showMessageDialog(null, "Current account created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid account type!");
            }
        }
    }

    // Action for deposit
    private class DepositAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());

            if (!accounts.containsKey(accountNumber)) {
                JOptionPane.showMessageDialog(null, "Account not found!");
                return;
            }

            Account account = accounts.get(accountNumber);
            if (account instanceof Transaction) {
                ((Transaction) account).deposit(amount);
                transactionHistoryArea.append("Deposited: " + amount + "\n");
            }
        }
    }

    // Action for withdrawal
    private class WithdrawAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());

            if (!accounts.containsKey(accountNumber)) {
                JOptionPane.showMessageDialog(null, "Account not found!");
                return;
            }

            Account account = accounts.get(accountNumber);
            if (account instanceof Transaction) {
                ((Transaction) account).withdraw(amount);
                transactionHistoryArea.append("Withdrew: " + amount + "\n");
            }
        }
    }

}
