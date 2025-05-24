package model.suit;

public class FancySuit extends SuitDecorator{

    public FancySuit(Suit suit) {
        super(suit);
    }
    
    public double getCost(){
        return super.getCost() * 100;
    }

    public String getDescription(){
        return super.getDescription() + " that is fancy";
    }
}
