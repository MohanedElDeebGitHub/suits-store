public abstract class SuitOrder {

    public boolean getDescription;
    public Object claculatecost;

    protected String description ="Unknown Suit";
    protected PricingStrategy pricing;

    public SuitOrder() {
        this.pricing = new RegularPricing();
    }
    public void setPricingStrategy(PricingStrategy pricing) {
        this.pricing = pricing;
    }
    public String getDescription() {
        return description;
    }
    public abstract double calculateCost();
}
