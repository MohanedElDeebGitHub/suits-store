package model.suit;

import service.orders.SuitOrder;

public class BasicSuit extends SuitOrder{

    public BasicSuit() {
        description="Basic Suit";
    }

    @Override
    public double calculateCost() {
        return pricing.calculatePrice(2.99);
    }
}
