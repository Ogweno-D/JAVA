
public class Main{
    public static void main(String[] args) throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount(200.00);


        try {
            System.out.println("\n ++++++++++++++++++++++++++++++++++++++++++ \n");
            bankAccount.deposit(10000);
            System.out.println("\n ++++++++++++++++++++++++++++++++++++++++++ \n");
            bankAccount.withdraw(600);
        
        // } catch (IllegalArgumentException e){
        //     System.out.println("Application error"+ e.getMessage());

        } catch (InsufficientFundsException e){
            System.out.println("Well, to put is simply, YOU ARE BROKE!!");
        } finally{
            System.out.println("\n ======================================= \n");
            System.out.println("Transaction Complete");
        }

       
    }
}