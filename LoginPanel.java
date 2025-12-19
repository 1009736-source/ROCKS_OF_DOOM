import javax.swing.*;
import java.awt.*;
public class LoginPanel extends JPanel {
    public LoginPanel(GameFrame frame) {
        setLayout(new GridLayout(6, 1));
        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        add(new JLabel("ROCKS_OF_DOOM", SwingConstants.CENTER));
        add(new JLabel("Username:"));
        add(user);
        add(new JLabel("Password:"));
        add(pass);
        add(login);
        add(register);
        login.addActionListener(e -> {
            if (UserManager.login(user.getText(), new String(pass.getPassword()))) {
                frame.startGame(user.getText());
            }
        });
        register.addActionListener(e -> {
            UserManager.register(user.getText(), new String(pass.getPassword()));
        });
    }
}