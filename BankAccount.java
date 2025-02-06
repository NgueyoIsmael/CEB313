import java.util.ArrayList;

public class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public BankAccount(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Methods to deposit and withdraw money
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount + ", New Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount + ", New Balance: $" + balance);
        } else {
            transactionHistory.add("Withdrawal failed: Insufficient funds");
        }
    }

    // Getter methods
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}

