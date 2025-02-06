// CurrentAccount class inheriting from Account and implementing Transaction
public class CurrentAccount extends Account implements Transaction {
    private double overdraftLimit = 1000.00;  // Overdraft limit for Current account

    public CurrentAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ". New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Withdrawal failed. Overdraft limit exceeded.");
        }
    }
}
