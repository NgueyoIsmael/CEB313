// SavingsAccount class inheriting from Account and implementing Transaction
public class SavingsAccount extends Account implements Transaction {
    private double minBalance = 500.00;  // Minimum balance for Savings account

    public SavingsAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ". New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Withdrawal failed. Minimum balance of " + minBalance + " required.");
        }
    }
}
