package service.OrderQOLServices;

import model.cart.ShoppingCart;
import model.product.Product;

public class SameOrderAddition implements Command{
    private Product product;

    public SameOrderAddition(Product product){
        this.product = product;
    }

    public void execute(){
        ShoppingCart.getInstance().getProductList().add((Product) product.clone());
    }
}
