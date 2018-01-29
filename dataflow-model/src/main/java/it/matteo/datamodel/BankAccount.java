package it.matteo.datamodel;

/**
 * Class of a bank account instance
 * @author Matteo Minardi
 */
public class BankAccount {
    
    private float balance;
    /**
     * Constructor of the bank account
     * @param balance the initial account balance
     */
    public BankAccount(float balance) {
        this.balance = balance;
    }
    /**
     * Constructor with an initial balance of 0.
     */
    public BankAccount() {
        this(0);
    }
    /**
     * Adds money to the current bank account.
     * @param value 
     */
    public void add(float value) {
        if (value <= 0) {
            throw new IllegalArgumentException("You can't add a negative value");
        }
        
        this.balance += value;
    }
    /**
     * Getter for the money.
     * @param value the value requested
     * @return the value requested.
     */
    public float get(float value) {
        if (value <= 0) {
            throw new IllegalArgumentException("You can't get a negative value");
        }
        
        if (this.balance - value < 0) {
            throw new IllegalArgumentException("You can't get this amount, your balance is " + this.balance);
        }
        
        this.balance -= value;
        return value;
    }
    
    /**
     * Getter for the account balance.
     * @return the float value of the balance
     */
    public float printBalance(){
        return this.balance;
    }
    /**
     * Getter for the account balance in {@link String}
     * @return A string containing the value
     */
    public String printBalanceAsString(){
        return String.valueOf(this.balance);
    }
}
