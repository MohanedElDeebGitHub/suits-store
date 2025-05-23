package service.pricing.strategy;
public class HappyHourPricing implements    PricingStrategy{


    @Override
    public double calculatePrice(double basePrice) {
        return basePrice*0.8;
    }
}
