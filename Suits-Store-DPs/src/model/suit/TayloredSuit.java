package model.suit;

import model.Prototype;
import model.product.Product;

public class TayloredSuit extends SuitDecorator implements Product, Suit {
    public TayloredSuit(Suit suit){
        super(suit);
    }

    public double getCost(){
        return super.getCost() + 1200;
    }

    public String getDescription(){
        return super.getDescription();
    }

    @Override
    public Prototype clone() {
        return null;
    }
}
