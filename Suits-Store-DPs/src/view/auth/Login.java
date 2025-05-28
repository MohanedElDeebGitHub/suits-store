package view.auth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    public JPanel loginFrame;
    private JTextField usernameInput;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    // login -> create account navigation
    public Login(){
        setContentPane(loginFrame);
        setSize(500, 500);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registerFrame = new Register();
                registerFrame.setVisible(true);
                dispose();
            }
        });
    }
}
