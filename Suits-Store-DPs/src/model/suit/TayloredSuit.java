package model.suit;

public class TayloredSuit extends SuitDecorator{
    public TayloredSuit(Suit suit){
        super(suit);
    }

    public double getCost(){
        return super.getCost() + 1200;
    }

    public String getDescription(){
        return super.getDescription();
    }
}
