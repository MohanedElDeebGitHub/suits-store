package model.paymentStrategy;

public class PayPalPayment implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("paying " + amount + " with paypal");
    }
}
