package model.cart;

import java.util.ArrayList;
import java.util.List;

import model.Prototype;
import model.product.Product;

// Singleton
public class ShoppingCart implements Prototype{
    private List<Product> productList = new ArrayList<>();
    // eager instantiation
    private static ShoppingCart cart = new ShoppingCart();

    private ShoppingCart(){}

    public static synchronized ShoppingCart getInstance(){
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

    public List<Product> getProductList(){
        return productList;
    }
}

