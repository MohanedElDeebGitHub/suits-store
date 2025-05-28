package view.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import model.suit.Suit;
import model.suit.BasicSuit;
import model.suit.FancySuit;
import model.suit.TayloredSuit;

public class Shopping extends JFrame {

    private enum SuitStage {
        BASIC,
        FANCY,
        TAYLORED
    }

    // The UI "card" for a suit item
    private class SuitItemPanel extends JPanel {
        private SuitStage currentStage;
        private JLabel descLabel;
        private JLabel priceLabel;
        private final String baseName;

        public SuitItemPanel(String baseName, SuitStage startingStage) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createTitledBorder(baseName));
            setBackground(Color.WHITE);

            this.baseName = baseName;
            currentStage = startingStage;
            descLabel = new JLabel();
            descLabel.setFont(new Font("Arial", Font.BOLD, 14));
            descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabel = new JLabel();
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton addToCartBtn = new JButton("Add to Cart");
            addToCartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            updateDisplay();

            descLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            descLabel.setToolTipText("Click to upgrade/downgrade this suit!");
            descLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cycleStage();
                    updateDisplay();
                }
            });

            addToCartBtn.addActionListener(e -> {
                Suit suit = getSuitForStage();
                cart.add(suit);
                JOptionPane.showMessageDialog(Shopping.this,
                        getDisplayDescription() + " added to cart! (cart not implemented yet)");
            });

            add(Box.createVerticalGlue());
            add(descLabel);
            add(Box.createVerticalStrut(8));
            add(priceLabel);
            add(Box.createVerticalStrut(8));
            add(addToCartBtn);
            add(Box.createVerticalGlue());
        }

        private void cycleStage() {
            switch (currentStage) {
                case BASIC:
                    currentStage = SuitStage.FANCY;
                    break;
                case FANCY:
                    currentStage = SuitStage.TAYLORED;
                    break;
                case TAYLORED:
                    currentStage = SuitStage.BASIC;
                    break;
            }
        }

        private Suit getSuitForStage() {
            switch (currentStage) {
                case BASIC:
                    return new BasicSuit();
                case FANCY:
                    return new FancySuit(new BasicSuit());
                case TAYLORED:
                    return new TayloredSuit(new FancySuit(new BasicSuit()));
                default:
                    return new BasicSuit();
            }
        }

        private String getDisplayDescription() {
            switch (currentStage) {
                case BASIC:
                    return "Basic suit from our store";
                case FANCY:
                    return "Fancy suit from our store";
                case TAYLORED:
                    return "Taylored suit from our store";
                default:
                    return "Suit from our store";
            }
        }

        private void updateDisplay() {
            Suit suit = getSuitForStage();
            descLabel.setText("<html><div style='text-align:center;'>" + getDisplayDescription() + "</div></html>");
            priceLabel.setText("Price: $" + String.format("%.2f", suit.getCost()));
        }
    }

    // The cart as a list of suits (not implemented in detail yet)
    private final List<Suit> cart = new ArrayList<>();

    // UI fields for switching views
    private JPanel suitsPanel; // container for suit panels
    private JPanel mainPanel;
    private final List<SuitItemPanel> suitItemPanels = new ArrayList<>();
    private boolean gridView = true; // default to grid

    public Shopping() {
        setTitle("Suit Store");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 370);
        setLocationRelativeTo(null);

        // Top panel for view switcher
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel viewLabel = new JLabel("View:");
        JToggleButton gridButton = new JToggleButton("Grid", true);
        JToggleButton listButton = new JToggleButton("List", false);

        ButtonGroup viewGroup = new ButtonGroup();
        viewGroup.add(gridButton);
        viewGroup.add(listButton);

        topPanel.add(viewLabel);
        topPanel.add(gridButton);
        topPanel.add(listButton);

        gridButton.addActionListener(e -> {
            if (!gridView) {
                gridView = true;
                updateSuitsPanel();
            }
        });
        listButton.addActionListener(e -> {
            if (gridView) {
                gridView = false;
                updateSuitsPanel();
            }
        });

        // Main content panel
        mainPanel = new JPanel(new BorderLayout());

        // Panel with all suit items
        suitsPanel = new JPanel();
        // We'll set layout in updateSuitsPanel

        // 4 suits, all start at BASIC
        suitItemPanels.add(new SuitItemPanel("Starter Suit", SuitStage.BASIC));
        suitItemPanels.add(new SuitItemPanel("Work Suit", SuitStage.BASIC));
        suitItemPanels.add(new SuitItemPanel("Evening Suit", SuitStage.BASIC));
        suitItemPanels.add(new SuitItemPanel("Travel Suit", SuitStage.BASIC));

        updateSuitsPanel();

        mainPanel.add(suitsPanel, BorderLayout.CENTER);

        // Bottom panel for Cart/Checkout
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cartBtn = new JButton("Cart");
        JButton checkoutBtn = new JButton("Checkout");
        bottomPanel.add(cartBtn);
        bottomPanel.add(checkoutBtn);

        cartBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Cart page not implemented yet."));
        checkoutBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Checkout page not implemented yet."));

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add top panel and mainPanel to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    // Updates the suitsPanel to show suits in grid or list mode
    private void updateSuitsPanel() {
        suitsPanel.removeAll();
        if (gridView) {
            suitsPanel.setLayout(new GridLayout(1, suitItemPanels.size(), 14, 0));
        } else {
            suitsPanel.setLayout(new GridLayout(suitItemPanels.size(), 1, 0, 10));
        }
        for (SuitItemPanel panel : suitItemPanels) {
            suitsPanel.add(panel);
        }
        suitsPanel.revalidate();
        suitsPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new Shopping().setVisible(true);
        });
    }
}