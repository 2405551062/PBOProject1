import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Selamat Datang Admin", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton manageSahamButton = new JButton("Kelola Saham");
        JButton manageSBNButton = new JButton("Kelola SBN");
        JButton logoutButton = new JButton("Logout");

        manageSahamButton.addActionListener(e -> new KelolaSahamWindow().setVisible(true));

        manageSBNButton.addActionListener(e -> new KelolaSBNWindow().setVisible(true));

        logoutButton.addActionListener(e -> {
            this.dispose();
            // Recreate login page
            HashMap<String, String> loginInfo = new HashMap<>();
            loginInfo.put("admin", "12345");
            loginInfo.put("customer", "abcde");
            new LoginPage(loginInfo);
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(manageSahamButton);
        buttonPanel.add(manageSBNButton);
        buttonPanel.add(logoutButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
    }
}
