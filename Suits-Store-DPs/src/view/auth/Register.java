package view.auth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame{
    private JLabel fullNameText;
    public JPanel registerPanel;
    private JLabel accountNameText;
    private JLabel accountPasswordText;
    private JTextField fullNameInput;
    private JTextField accountNameInput;
    private JTextField accountPasswordInput;
    private JButton createNewAccountButton;
    private JButton loginButton;

    public Register(){
        setContentPane(registerPanel);
        setSize(500, 500);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame loginFrame = new Login();
                loginFrame.setVisible(true);
                dispose();
            }
        });
    }
}
