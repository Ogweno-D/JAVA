public class Main{

    public static void main( String[] args){
        Product product = new Product("Chippy Chips" , 9.99);
        System.out.println(product);

        // updatePrice [Primitive]
        double originalPrice = 20.00;
        System.out.println("Before updated price: " + originalPrice);

        product.updatePrice(originalPrice);
        System.out.println("After price update: "+ originalPrice);

        // As you can see, it did not affect the original value
        System.out.println(product);


        //2. Modify Object Reference
        Product p1 = new Product("Laptop", 999.99);
        System.out.println("Before product Modification: \n"+
                                 p1.getProductName() + "\n"+
                                "$" + p1.getPrice() + "\n" );

        // 
        p1.updateProduct(p1);  

        System.out.println("After Modification: \n"+ 
                                 p1.getProductName() + "\n" +
                                 "$" + p1.getPrice() + "\n" );


        // 3. Reassign Object Reference
        Product p2 = new Product("Iphone", 2000.00);
        System.out.println("Before reassignProduct:  \n"+ 
                            p2.getProductName() + "\n" +
                            "$" + p2.getPrice() + "\n"
                            );
        p2.reassignProduct(p2);
        System.out.println(" After reassignProduct:  \n" +
                            p2.getProductName() + "\n" +
                            "$" + p2.getPrice() + "\n"
                            );

    }
}