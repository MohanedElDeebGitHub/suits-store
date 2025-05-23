import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    // private data member
    private static OrderManager instance;
    private List<SuitOrder> orders;

    // private constructor
    private OrderManager() {
        orders = new ArrayList<>();
    }

    // public fatory method
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(SuitOrder order) {
        orders.add(order);
    }

    public void displayOrders() {
        for (SuitOrder order : orders) {
            System.out.println(order.getDescription +" -$"+order.claculatecost);
        }
    }
}
