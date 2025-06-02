package model.paymentStrategy.payStates;

import model.paymentStrategy.PaymentContext;

public class DonePayState implements PayState{
    @Override
    public void payState() {
        System.out.println("payment is done");
    }


}
