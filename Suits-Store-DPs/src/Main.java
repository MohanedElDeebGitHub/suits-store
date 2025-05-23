import java.util.HashMap;
import java.util.Map;

public class Main {
    private Map<String, Suit> menu = new HashMap<>();

    public Suit orderSuit(String type){
        Suit suit = menu.get(type);
        if (suit != null){
            return suit.clone();
        }
        throw new IllegalArgumentException("Sorry");
    }

    public static void main(String[] args) {
        SuitOrder suit= new BasicSuit();
        SuitOrder suitwithShoes= new ShoesDecorator(suit);
        SuitOrder suitwithShoeswith= new SuitDecorator(suitwithShoes);
        SuitOrder spectialSuit = new SuitDecorator(new ShoesDecorator(new BasicSuit()));
        spectialSuit.setPricingStrategy(new HappyHourPricing());

        OrderManager ordermanager=OrderManager.getInstance();
        ordermanager.addOrder(suitwithShoeswith);
        ordermanager.addOrder(spectialSuit);
        System.out.println("suit shop orders:");
        ordermanager.displayOrders();
        OrderManager anotherManager =OrderManager.getInstance();
        System.out.println("is same instance? "+(ordermanager==anotherManager));

        Main shope = new Main();
        Suit shop1 = shope.orderSuit("classic Suit");
        shop1.serve();
        ClassicSuit customeClassic = (ClassicSuit) shope.orderSuit("classic Suit");
        customeClassic.setSize("classic");
        customeClassic.serve();

    }
}