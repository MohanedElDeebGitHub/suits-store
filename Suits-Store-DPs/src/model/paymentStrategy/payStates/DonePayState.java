package model.paymentStrategy.payStates;

public class DonePayState implements PayState{
    @Override
    public void payState() {
        System.out.println("payment is done");
    }
}
