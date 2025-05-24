package model.paymentStrategy.specialPay;

import model.paymentStrategy.PaymentStrategy;

public class InstapayAdapter implements PaymentStrategy{
    private Instapay instapay;
    private String bank;

    public InstapayAdapter(Instapay instapay, String bank){
        this.instapay = instapay;
        this.bank = bank;
    }

    @Override
    public void pay(double amount) {
        instapay.pay(amount, bank);
    }
    
}
