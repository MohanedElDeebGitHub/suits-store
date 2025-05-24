package model;


// Singleton
public class ShoppingCart{
    // eager instantiation
    private ShoppingCart cart = new ShoppingCart();

    private ShoppingCart(){}

    public synchronized ShoppingCart getInstance(){
        return cart;
    }
}