package model.paymentStrategy;

public class CreditCardPayment implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("paying " + amount + " with creditcard");
    }
    public class PayPalPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("Processing $" + amount + " via PayPal");
            // Actual payment logic here
        }
    }
}
