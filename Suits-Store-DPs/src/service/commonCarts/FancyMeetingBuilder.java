package service.commonCarts;

import java.util.List;

import model.suit.BasicSuit;
import model.suit.FancySuit;

public class FancyMeetingBuilder implements SuitCartBuilder{
    private List<FancySuit> fancySuitList;
    private List<BasicSuit> normalSuitList;

    @Override
    public void buildSetOfFancySuits() {
        for(int i = 0; i < 5; i++){
            fancySuitList.add(new FancySuit(new BasicSuit()));
        }
    }   

    @Override
    public void buildSetOfNormalSuits() {
        for(int i = 0; i < 2; i++){
            normalSuitList.add(new BasicSuit());
        }
    }
}
