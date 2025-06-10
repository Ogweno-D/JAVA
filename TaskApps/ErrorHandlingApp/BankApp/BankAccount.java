public  class  BankAccount{
    private double balance;
    // Add more things on revision e.g A/c No, Name e.tc 
    // Just make it fuller and richer not like your bank account ><


    // constructor
    public BankAccount(double balance){
        this.balance = balance;
    }


    // Methods
    public void deposit(double amount){
        // amount > 0 to catch invalid deposits( negative or zero amounts)
        if(amount > 0){
            balance += amount;
            System.out.println(" The amount deposited is : " + "$" + amount);
            System.out.println(" Your new account balance is : " + "$" + balance);
        } else{
            System.out.println("Enter a valid amount!");
        }

    }

    /**
     * Function to withdraw money from the checking account!
     * @param amount
     * It throws `InsufficientFundsException`
     */
    public void withdraw(double amount) throws  InsufficientFundsException{

         // amount > 0
        if(amount >= 0){
            try {
                // balance -= amount;
                if(amount >= balance){
                    
                    double deficit = amount - balance;

                    throw new InsufficientFundsException(deficit);
                }
                else{
                    balance -=amount;
                    System.out.println(" The amount withdrawn is : " + "$" + amount);
                    System.out.println(" Your new account balance is : " + "$" + balance);
                }
                
            } catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds ndugu, Work HARD!" );
                // I mean it works! Wanna check out? comment out the code below
                // And you need not to have enough money for you to see it. Get it?
                // e.printStackTrace();

            }
       
        } else{
            System.out.println("You cannot WITHDRAW this amount! \n Enter a valid amount!");
        }
    }

    // Another way to do it!
    // public void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException {
    //     if (amount <= 0) {
    //         throw new IllegalArgumentException("Withdrawal amount must be positive and greater than zero");
    //     }
    //     if (amount > balance) {
    //         throw new InsufficientFundsException("Insufficient funds. Available balance: $" + balance);
    //     }
    //     balance -= amount;
    //     System.out.println("Successfully withdrew $" + amount + ". New balance: $" + balance);
    // }

}