public class Product{
    String productName;
    double price;

    // Constructor
    public Product(String productName, double price){
        this.productName = productName;
        this.price = price;
    }

    // Setters and Getters

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }


    public double getPrice(){
        return price;
    }
    public void setPrice( double price){
        this.price = price;
    }


    // Methods to call and a lot of fun!
    // 1. Update Primitive
    // Tries to  update the price but does not affect the 
    // original variable
    public void updatePrice(double price){
        System.out.println("Before modification(Inside updatePrice): " + price );
        price = 100.00;
        System.out.println("After modification(Inside updatePrice): "+ price);
    }

    // 2. Modify Object Reference
    // Updates the name and price of the passed object
    public void updateProduct( Product product){
        System.out.println("Inside updateProduct before Modification:\n "
                                    + product.getProductName() + "\n" +
                                    "$" + product.getPrice()+ "\n");

        product.setProductName(" Updated " + product.getProductName());
        product.setPrice(product.getPrice()*1.5); // Increase by 50% >< // Also checkout if I can trim it

        System.out.println("Inside updateProduct after Modification: \n"
                                    + product.getProductName() + "\n" +
                                     "$" + product.getPrice() + "\n");

    }


    // 3. Reassign Object Reference
    // Assigns a new object to the parameter and show that the reassignment does
    // not affect the original object in the caller, as only the local copy of the reference
    // is modified

    public void reassignProduct(Product product){
        System.out.println(" Inside reassignProduct before the DEED! >< \n" 
                                + product.getProductName() + "\n"
                                + "$" + product.getPrice() + "\n"
                                );
        product = new Product("Notebook", 200.00);

        System.out.println("Inside reassignProduct after the DEED (Buyers remorse ++) \n"
                                + product.getProductName()+ "\n"
                                + "$" + product.getPrice() + "\n"
                                );

    }


    // Trial and Error slide as everybody does?

   public  void modify(int value){
        value = 20;
        System.out.println(value);
    }

    public void modifyProductName(String  productName){
        this.productName = productName;
        
    }

    @Override
    public String toString(){
        return 
                "Product Name: " + productName + "\n" +
                "Product price: " + price + "" +
                " ";
    }
}