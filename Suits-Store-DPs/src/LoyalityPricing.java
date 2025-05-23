public class LoyalityPricing implements PricingStrategy{

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.9;
    }
}
