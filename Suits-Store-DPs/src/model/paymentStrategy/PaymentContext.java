package model.paymentStrategy;

import model.paymentStrategy.payStates.PayState;
import model.paymentStrategy.payStates.PendingPayState;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    private PayState payState;

    public PaymentContext(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentStrategy getPaymentStrategy(){
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount){
        paymentStrategy.pay(amount);
        payState = new PendingPayState();
    }

    public void payState(){
        payState.payState();
    }
}
