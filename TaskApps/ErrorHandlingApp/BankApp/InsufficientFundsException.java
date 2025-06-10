
public class InsufficientFundsException extends  Exception{
    private  double balance;

    public InsufficientFundsException( double balance){
        this.balance = balance;
    }

    // Setters and getters
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}