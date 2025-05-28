import view.auth.Login;
import view.store.Shopping;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        Login login = new Login();
//        login.setContentPane(login.loginFrame);
//        login.setSize(500, 500);
//        login.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            new Shopping().setVisible(true);
        });
    }
}


/*

    Signleton, Decorator, Strategy, prototype, builder
    proxy, state, adapter, command, facade




 */