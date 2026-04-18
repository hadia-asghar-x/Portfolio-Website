import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame implements ActionListener {
    JLabel userLabel, passLabel;
    JTextField userText;
    JPasswordField passText;
    JButton loginButton;

    public LoginForm() {
        setTitle("Library Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        userText = new JTextField();
        passText = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        setLayout(new GridLayout(3, 2, 10, 10));
        add(userLabel); add(userText);
        add(passLabel); add(passText);
        add(new JLabel()); add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = new String(passText.getPassword());

        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "✅ Login Successful!");
            // Proceed to next window or menu
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;

        try {
            String sql = "SELECT * FROM [User] WHERE Username = ? AND Password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            return rs.next(); 

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}