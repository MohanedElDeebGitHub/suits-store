package view;

import javax.swing.*;
import java.awt.*;
import model.paymentStrategy.*;
import model.paymentStrategy.payStates.DonePayState;

public class Checkout extends JFrame {
    private JPanel mainPanel1;
    private JPanel buttonPanel;
    private JButton creditCardBtn;
    private JButton paypalBtn;
    private JButton cancelBtn;
    private JLabel totalLabel;
    private double totalAmount;
    private JPanel Checkout;
    public Checkout(double total) {
        this.totalAmount = total;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Checkout");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Main panel with BorderLayout
        mainPanel1 = new JPanel(new BorderLayout(10, 10));
        mainPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Total label (CENTER)
        totalLabel = new JLabel(
                "<html><div style='text-align: center;'>" +
                        "Proceed to checkout?<br>" +
                        "Total: $" + String.format("%.2f", totalAmount) +
                        "</div></html>",
                SwingConstants.CENTER
        );
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel1.add(totalLabel, BorderLayout.CENTER);

        // Button panel (SOUTH)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        creditCardBtn = new JButton("Credit Card");
        paypalBtn = new JButton("PayPal");
        cancelBtn = new JButton("Cancel");

        // Style buttons
        Dimension buttonSize = new Dimension(120, 30);
        creditCardBtn.setPreferredSize(buttonSize);
        paypalBtn.setPreferredSize(buttonSize);
        cancelBtn.setPreferredSize(buttonSize);

        buttonPanel.add(creditCardBtn);
        buttonPanel.add(paypalBtn);
        buttonPanel.add(cancelBtn);

        mainPanel1.add(buttonPanel, BorderLayout.SOUTH);

        // Add actions
        creditCardBtn.addActionListener(e -> processPayment(new CreditCardPayment()));
        paypalBtn.addActionListener(e -> processPayment(new PayPalPayment()));
        cancelBtn.addActionListener(e -> dispose());

        add(mainPanel1);
    }
    private void setupButtonActions() {
        // Credit Card button action
        creditCardBtn.addActionListener(e -> {
            processPayment(new CreditCardPayment());
        });

        // PayPal button action
        paypalBtn.addActionListener(e -> {
            processPayment(new PayPalPayment());
        });

        // Cancel button action (if needed)
        cancelBtn.addActionListener(e -> dispose());
    }

    private void processPayment(PaymentStrategy strategy) {
        // Create PaymentContext with the concrete strategy
        PaymentContext context = new PaymentContext(strategy);

        try {
            context.pay(totalAmount);
            context.setPayState(new DonePayState());
            JOptionPane.showMessageDialog(this, "Payment successful!");
            dispose();
        } catch (Exception e) {
            context.setPayState(new FailedPayState());
            JOptionPane.showMessageDialog(this,
                    "Payment failed: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }



}
