package service.commonCarts;

public class CommonCartsDirector {
    private SuitCartBuilder suitCartBuilder;

    public CommonCartsDirector(SuitCartBuilder suitCartBuilder){
        this.suitCartBuilder = suitCartBuilder;
    }

    public void buildCart(){
        suitCartBuilder.buildSetOfFancySuits();
        suitCartBuilder.buildSetOfNormalSuits();
    }
}
