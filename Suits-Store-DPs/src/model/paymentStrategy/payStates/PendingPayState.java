package model.paymentStrategy.payStates;

public class PendingPayState implements PayState{
    @Override
    public void payState() {
        System.out.println("payment is pending");
    }
}
