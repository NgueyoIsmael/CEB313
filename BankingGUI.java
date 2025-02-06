import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BankingGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField accountNumberField;
    private JTextField depositField;
    private JTextField withdrawField;
    private JTextArea accountDetailsArea;
    private JComboBox<String> accountSelectionComboBox;

    private Map<String, BankAccount> accounts;  // Map to store multiple accounts

    public BankingGUI() {
        accounts = new HashMap<>();

        frame = new JFrame("Bank Account Management");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Name Field
        frame.add(new JLabel("Account Holder Name:"));
        nameField = new JTextField(20);
        frame.add(nameField);

        // Account Number Field
        frame.add(new JLabel("Account Number:"));
        accountNumberField = new JTextField(20);
        frame.add(accountNumberField);

        // Deposit Field
        frame.add(new JLabel("Deposit Amount:"));
        depositField = new JTextField(10);
        frame.add(depositField);

        // Withdraw Field
        frame.add(new JLabel("Withdraw Amount:"));
        withdrawField = new JTextField(10);
        frame.add(withdrawField);

        // Create Account Button
        JButton createAccountButton = new JButton("Create Account");
        frame.add(createAccountButton);

        // Account Selection ComboBox
        frame.add(new JLabel("Select Account:"));
        accountSelectionComboBox = new JComboBox<>();
        frame.add(accountSelectionComboBox);

        // Show Account Button
        JButton showAccountButton = new JButton("Show Account");
        frame.add(showAccountButton);

        // Account Details Text Area
        accountDetailsArea = new JTextArea(10, 30);
        accountDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(accountDetailsArea);
        frame.add(scrollPane);

        // Action listener for creating an account
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountHolderName = nameField.getText();
                String accountNumber = accountNumberField.getText();

                if (accountHolderName.isEmpty() || accountNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in both name and account number.");
                } else if (accounts.containsKey(accountNumber)) {
                    JOptionPane.showMessageDialog(frame, "Account with this number already exists.");
                } else {
                    // Create the new account and add it to the map
                    BankAccount newAccount = new BankAccount(accountHolderName, accountNumber);
                    accounts.put(accountNumber, newAccount);

                    // Add the new account to the JComboBox for selection
                    accountSelectionComboBox.addItem(accountNumber);

                    // Clear the input fields for creating another account
                    nameField.setText("");
                    accountNumberField.setText("");

                    JOptionPane.showMessageDialog(frame, "Account created successfully!");
                }
            }
        });

        // Action listener for showing account details
        showAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAccountNumber = (String) accountSelectionComboBox.getSelectedItem();
                if (selectedAccountNumber == null || !accounts.containsKey(selectedAccountNumber)) {
                    JOptionPane.showMessageDialog(frame, "Please select an account.");
                    return;
                }

                BankAccount selectedAccount = accounts.get(selectedAccountNumber);

                // Handle deposit if entered
                if (!depositField.getText().isEmpty()) {
                    try {
                        double depositAmount = Double.parseDouble(depositField.getText());
                        selectedAccount.deposit(depositAmount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid deposit amount.");
                    }
                }

                // Handle withdrawal if entered
                if (!withdrawField.getText().isEmpty()) {
                    try {
                        double withdrawAmount = Double.parseDouble(withdrawField.getText());
                        selectedAccount.withdraw(withdrawAmount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid withdrawal amount.");
                    }
                }

                // Display account information and transaction history
                StringBuilder accountInfo = new StringBuilder();
                accountInfo.append("Account Holder: ").append(selectedAccount.getAccountHolderName()).append("\n")
                        .append("Account Number: ").append(selectedAccount.getAccountNumber()).append("\n")
                        .append("Balance: $").append(selectedAccount.getBalance()).append("\n")
                        .append("\nTransactions:\n");

                for (String transaction : selectedAccount.getTransactionHistory()) {
                    accountInfo.append(transaction).append("\n");
                }

                accountDetailsArea.setText(accountInfo.toString());
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BankingGUI();
    }
}
