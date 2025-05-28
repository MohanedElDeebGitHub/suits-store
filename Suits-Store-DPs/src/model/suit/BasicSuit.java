package model.suit;

import model.product.Product;

public class BasicSuit implements Suit, Product{
    private String description;
    private double cost;

    public BasicSuit(){
        this.description = "suit from our store";
        this.cost = 120;
    }


    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public double getCost() {
        return cost;
    }
    
    public BasicSuit clone(){
        try{
            return (BasicSuit) super.clone();
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
