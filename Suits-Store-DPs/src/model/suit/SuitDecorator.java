package model.suit;
import service.orders.SuitOrder;
import service.pricing.strategy.PricingStrategy;

public class SuitDecorator extends SuitOrder{

    protected SuitOrder decoratedSuit;

    public SuitDecorator(SuitOrder decoratedSuit) {
        this.decoratedSuit = decoratedSuit;
        this.pricing=decoratedSuit.getPricing();
    }

    @Override
    public double calculateCost() {
        return 0 ;
    }
    public void setPricingStrategy(PricingStrategy Strategy) {
        this.pricing=Strategy;
        decoratedSuit.setPricingStrategy(pricing);
    }
}
