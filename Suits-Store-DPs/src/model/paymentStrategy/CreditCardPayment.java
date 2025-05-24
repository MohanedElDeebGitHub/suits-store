package model.paymentStrategy;

public class CreditCardPayment implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("paying " + amount + " with creditcard");
    }
}
