package model.suit;

import model.product.Product;

public class BasicSuit implements Suit, Product{
    private String description = "suit from our store";
    private double cost = 120;


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return cost;
    }
    
}
