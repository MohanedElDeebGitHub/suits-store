public class SuitDecorator extends SuitOrder{

    protected SuitOrder decoratedSuit;

    public SuitDecorator(SuitOrder decoratedSuit) {
        this.decoratedSuit = decoratedSuit;
        this.pricing=decoratedSuit.pricing;
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
