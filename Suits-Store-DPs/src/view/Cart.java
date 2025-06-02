package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.suit.Suit;
public class Cart extends JFrame {
    private JPanel mainPanel;
    private JList<String> itemList;
    private JPanel buttonPanel;
    private JPanel panel1;
    private JButton cartBtn;
    private JButton checkoutBtn;  // Renamed from cartBtn to be more descriptive
    private JButton continueShoppingBtn;  // Added new button
    private List<Suit> cartItems;
    public Cart(List<Suit> items) {
        this.cartItems = items;
        setTitle("Your Shopping Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        loadCartItems();
        setupActions();

        add(mainPanel);
    }
    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());

        // Item list with scroll pane
        itemList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(itemList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Continue Shopping button
        continueShoppingBtn = new JButton("Continue Shopping");
        buttonPanel.add(continueShoppingBtn);

        // Remove Selected button
        JButton removeBtn = new JButton("Remove Selected");
        removeBtn.addActionListener(e -> {
            int selectedIndex = itemList.getSelectedIndex();
            if (selectedIndex != -1) {
                cartItems.remove(selectedIndex);
                loadCartItems(); // Refresh the list

                // Optional: Show confirmation
                JOptionPane.showMessageDialog(this,
                        "Item removed from cart",
                        "Removed",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select an item to remove",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        buttonPanel.add(removeBtn);

        // Checkout button
        checkoutBtn = new JButton("Proceed to Checkout");
        buttonPanel.add(checkoutBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    private void loadCartItems() {
        // Convert cart items to display strings
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Suit item : cartItems) {
            listModel.addElement(item.getDescription() + " - $" + item.getCost());
        }
        itemList.setModel(listModel);
    }
    private void setupActions() {
        // Continue Shopping button closes the cart
        continueShoppingBtn.addActionListener(e -> dispose());

        // Checkout button
        checkoutBtn.addActionListener(e -> {
            double total = cartItems.stream()
                    .mapToDouble(Suit::getCost)
                    .sum();

            JOptionPane.showMessageDialog(this,
                    "Total: $" + String.format("%.2f", total) +
                            "\nRedirecting to payment...");

            // Here you would integrate with your PaymentContext later
        });
    }
}
