package model.suit;

import model.Prototype;
import model.product.Product;

public class FancySuit extends SuitDecorator implements Suit, Product {

    public FancySuit(Suit suit) {
        super(suit);
    }
    
    public double getCost(){
        return super.getCost() * 100;
    }

    public String getDescription(){
        return super.getDescription() + " that is fancy";
    }

    @Override
    public Prototype clone() {
        return null;
    }
}
