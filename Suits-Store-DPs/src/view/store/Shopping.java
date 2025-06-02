package view.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import model.paymentStrategy.*;
import model.paymentStrategy.payStates.DonePayState;
import model.suit.*;
import view.Cart;
import view.Checkout;

public class Shopping extends JFrame {
    // Constants
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 450;
    private static final int MAX_ORDER_HISTORY = 5;

    // Enums
    private enum SuitStage { BASIC, FANCY, TAYLORED }

    // UI Components
    private JPanel mainPanel;
    private JPanel suitsPanel;
    private JPanel viewTogglePanel;
    private JButton historyBtn;
    private final List<SuitItemPanel> suitItemPanels = new ArrayList<>();

    // Data
    private final List<Suit> cart = new ArrayList<>();
    private final List<String> orderHistory = new ArrayList<>();
    private boolean gridView = true;

    public Shopping() {
        initializeFrame();
        setupMainPanel();
        initializeViewTogglePanel();
        initializeSuitsPanel();
        setupBottomPanel();
    }

    // Initialization Methods
    private void initializeFrame() {
        setTitle("Suit Store");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
    }

    private void initializeViewTogglePanel() {
        viewTogglePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JLabel viewLabel = new JLabel("View:");
        JToggleButton gridButton = new JToggleButton("Grid", true);
        JToggleButton listButton = new JToggleButton("List", false);
        historyBtn = new JButton("History"); // this is for the last item the user  make it
        historyBtn.setToolTipText("View your order history");

        setupViewToggleButtons(gridButton, listButton);
        addComponentsToViewPanel(viewLabel, gridButton, listButton, historyBtn);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(viewTogglePanel, BorderLayout.NORTH);
        mainPanel.add(topContainer, BorderLayout.NORTH);
    }

    private void setupViewToggleButtons(JToggleButton gridButton, JToggleButton listButton) {
        ButtonGroup viewGroup = new ButtonGroup();
        viewGroup.add(gridButton);
        viewGroup.add(listButton);

        gridButton.addActionListener(e -> toggleGridView(true));
        listButton.addActionListener(e -> toggleGridView(false));
        historyBtn.addActionListener(e -> showLastOrder());
    }

    private void addComponentsToViewPanel(JLabel viewLabel, JToggleButton gridButton,
                                          JToggleButton listButton, JButton historyBtn) {
        viewTogglePanel.add(viewLabel);
        viewTogglePanel.add(gridButton);
        viewTogglePanel.add(listButton);
        viewTogglePanel.add(historyBtn);
    }

    private void initializeSuitsPanel() {
        suitsPanel = new JPanel();
        initializeSuitItems();
        mainPanel.add(new JScrollPane(suitsPanel), BorderLayout.CENTER);
    }

    private void setupBottomPanel() { // this method for cart and checkout button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cartBtn = new JButton("Cart");
        JButton checkoutBtn = new JButton("Checkout");

        cartBtn.addActionListener(e -> showCart());
        checkoutBtn.addActionListener(e -> checkout());

        bottomPanel.add(cartBtn);
        bottomPanel.add(checkoutBtn);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Suit Items Management
    private void initializeSuitItems() {
        suitItemPanels.clear();
        addSuitItem("Starter Suit", SuitStage.BASIC);
        addSuitItem("Work Suit", SuitStage.BASIC);
        addSuitItem("Evening Suit", SuitStage.BASIC);
        addSuitItem("Travel Suit", SuitStage.BASIC);
        updateSuitsPanel();
    }

    private void addSuitItem(String name, SuitStage stage) {
        suitItemPanels.add(new SuitItemPanel(name, stage));
    }

    private void updateSuitsPanel() {
        suitsPanel.removeAll();
        suitsPanel.setLayout(gridView ?
                new GridLayout(1, suitItemPanels.size(), 14, 0) :
                new GridLayout(suitItemPanels.size(), 1, 0, 10));

        suitItemPanels.forEach(suitsPanel::add);
        suitsPanel.revalidate();
        suitsPanel.repaint();
    }

    // View Toggle
    private void toggleGridView(boolean isGrid) {
        if (gridView != isGrid) {
            gridView = isGrid;
            updateSuitsPanel();
        }
    }

    // this method show the last order the user make it
    private void showLastOrder() {
        if (orderHistory.isEmpty()) {
            showMessage("No order history available", "Order History");
            return;
        }

        JTextArea historyArea = createHistoryTextArea();
        JScrollPane scrollPane = new JScrollPane(historyArea);
        showMessage(scrollPane, "Your Last Order", JOptionPane.PLAIN_MESSAGE);
    }

    private JTextArea createHistoryTextArea() {
        JTextArea historyArea = new JTextArea(orderHistory.get(0), 10, 30);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        return historyArea;
    }

    private void saveCurrentOrder() { // this method saved the current order to show in history button
        if (cart.isEmpty()) return;

        String orderSummary = createOrderSummary();
        orderHistory.add(0, orderSummary);

        if (orderHistory.size() > MAX_ORDER_HISTORY) {
            orderHistory.subList(MAX_ORDER_HISTORY, orderHistory.size()).clear();
        }
    }

    private String createOrderSummary() { // this method make summary about order to put him in the history
        StringBuilder summary = new StringBuilder();
        summary.append("Order Date: ").append(new java.util.Date()).append("\n");
        summary.append("Items:\n");

        cart.forEach(suit ->
                summary.append("- ")
                        .append(suit.getDescription())
                        .append(": $")
                        .append(String.format("%.2f", suit.getCost()))
                        .append("\n"));

        double total = calculateTotal();
        summary.append("Total: $").append(String.format("%.2f", total));
        return summary.toString();
    }

    // this method check if the cart is empty or not
    private void showCart() {
        if (cart.isEmpty()) {
            showMessage("Your cart is empty!");
        } else {
            new Cart(cart).setVisible(true);
        }
    }

    private void checkout() { // this method check if the checkout is empty or not if not htey will save the current order and open ckeckout window
        if (cart.isEmpty()) {
            showMessage("Your cart is empty!");
            return;
        }

        saveCurrentOrder();
        openCheckoutWindow();
    }

    private void openCheckoutWindow() { // this method show the checkout window and claculate the total oder
        double total = calculateTotal();
        Checkout checkout = new Checkout(total);
        checkout.setVisible(true);
        setEnabled(false);

        checkout.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Shopping.this.setEnabled(true);
            }
        });
    }

    // Helper Methods
    private double calculateTotal() {// this method for clac the total items
        return cart.stream().mapToDouble(Suit::getCost).sum();
    }

    private void showMessage(Object message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void showMessage(Object message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessage(Object message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // Suit Item Panel Inner Class
    private class SuitItemPanel extends JPanel {
        private SuitStage currentStage;
        private final JLabel descLabel;
        private final JLabel priceLabel;
        private final String baseName;

        public SuitItemPanel(String baseName, SuitStage startingStage) {
            this.baseName = baseName;
            this.currentStage = startingStage;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createTitledBorder(baseName));
            setBackground(Color.WHITE);

            descLabel = createDescriptionLabel();
            priceLabel = createPriceLabel();
            JButton addToCartBtn = createAddToCartButton();

            addComponentsToPanel(descLabel, priceLabel, addToCartBtn);
            updateDisplay();
        }

        private JLabel createDescriptionLabel() {
            JLabel label = new JLabel();
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setToolTipText("Click to upgrade/downgrade this suit!");
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cycleStage();
                    updateDisplay();
                }
            });
            return label;
        }

        private JLabel createPriceLabel() {
            JLabel label = new JLabel();
            label.setFont(new Font("Arial", Font.PLAIN, 13));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            return label;
        }

        private JButton createAddToCartButton() {
            JButton button = new JButton("Add to Cart");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> addSuitToCart());
            return button;
        }

        private void addComponentsToPanel(JLabel desc, JLabel price, JButton button) {
            add(Box.createVerticalGlue());
            add(desc);
            add(Box.createVerticalStrut(8));
            add(price);
            add(Box.createVerticalStrut(8));
            add(button);
            add(Box.createVerticalGlue());
        }

        private void addSuitToCart() {// this method for add the items in the cart
            Suit suit = getSuitForStage();
            cart.add(suit);
            showMessage(getDisplayDescription() + " added to cart!");
        }

        private void cycleStage() {
            currentStage = switch (currentStage) {
                case BASIC -> SuitStage.FANCY;
                case FANCY -> SuitStage.TAYLORED;
                case TAYLORED -> SuitStage.BASIC;
            };
            updateDisplay();
        }

        private Suit getSuitForStage() {
            return switch (currentStage) {
                case BASIC -> new BasicSuit();
                case FANCY -> new FancySuit(new BasicSuit());
                case TAYLORED -> new TayloredSuit(new FancySuit(new BasicSuit()));
            };
        }

        private String getDisplayDescription() {
            return switch (currentStage) {
                case BASIC -> "Basic suit from our store";
                case FANCY -> "Fancy suit from our store";
                case TAYLORED -> "Taylored suit from our store";
            };
        }

        private void updateDisplay() {
            Suit suit = getSuitForStage();
            descLabel.setText("<html><div style='text-align:center;'>" + getDisplayDescription() + "</div></html>");
            priceLabel.setText("Price: $" + String.format("%.2f", suit.getCost()));
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new Shopping().setVisible(true);
        });
    }
}