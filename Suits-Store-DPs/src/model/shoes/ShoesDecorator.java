package model.shoes;

import model.suit.SuitDecorator;
import service.orders.SuitOrder;

public class ShoesDecorator extends SuitDecorator {
    public ShoesDecorator(SuitOrder DecoretedSuit) {
        super(DecoretedSuit);
    }
    public String getDescription() {
        return decoratedSuit.getDescription()+"with shoes ";

    }
    public double calculateCost() {
        return pricing.calculatePrice(decoratedSuit.calculateCost()+0.25);
    }
}
