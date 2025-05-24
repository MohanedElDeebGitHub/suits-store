package model.suit;

public abstract class SuitDecorator {
    private Suit suit;

    public SuitDecorator(Suit suit){
        this.suit = suit;
    }

    public String getDescription(){
        return suit.getDescription();
    }    

    public double getCost(){
        return suit.getCost();
    }
}
