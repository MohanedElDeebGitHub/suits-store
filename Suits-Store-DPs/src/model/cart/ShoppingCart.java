package model.cart;


// Singleton
public class ShoppingCart implements Prototype{
    // eager instantiation
    private ShoppingCart cart = new ShoppingCart();

    private ShoppingCart(){}

    public synchronized ShoppingCart getInstance(){
        return cart;
    }

    public ShoppingCart clone(){
        try{
            return (ShoppingCart) super.clone();
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}

