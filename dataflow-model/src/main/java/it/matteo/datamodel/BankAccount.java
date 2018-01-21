package it.matteo.datamodel;

/**
 * @author matteo.minardi
 */
public class BankAccount {
    
    private float balance;

    public BankAccount(float balance) {
        this.balance = balance;
    }

    public BankAccount() {
        this(0);
    }

    public void add(float value) {
        if (value <= 0) {
            throw new IllegalArgumentException("You can't add a negative value");
        }
        
        this.balance += value;
    }
    
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
    
    public float printBalance(){
        return this.balance;
    }
    
    public String printBalanceAsString(){
        return String.valueOf(this.balance);
    }
}
