package model.paymentStrategy.specialPay;

public class Instapay {
    public void pay(double amount, String bankToPayfrom){
        System.out.println("Paying " + amount + " from instapay registered bank: " + bankToPayfrom);
    }
}
