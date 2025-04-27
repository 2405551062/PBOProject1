import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CustomerPage extends JFrame {
    public CustomerPage(String username) {
        setTitle("Halaman Nasabah - " + username);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Customer-specific components
        JLabel welcomeLabel = new JLabel("Selamat Datang, " + username, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton viewPortfolioButton = new JButton("Portofolio");
        JButton buySahamButton = new JButton("Beli Saham");
        JButton buySBNButton = new JButton("Beli SBN");
        JButton simulasiSBNButton = new JButton("Simulasi SBN");
        JButton logoutButton = new JButton("Logout");

        // Add action listeners to buttons
        viewPortfolioButton.addActionListener(e -> {
            new PortfolioWindow(username).setVisible(true);
        });

        buySahamButton.addActionListener(e -> {
            new BuySahamWindow(username).setVisible(true);
        });

        buySBNButton.addActionListener(e -> {
            new BuySBNWindow(username).setVisible(true);
        });

        simulasiSBNButton.addActionListener(e -> {
            new SimulasiSBNWindow(username).setVisible(true);
        });

        logoutButton.addActionListener(e -> {
            this.dispose();
            // Recreate login page with original credentials
            HashMap<String, String> loginInfo = new HashMap<>();
            loginInfo.put("admin", "admin123");
            loginInfo.put("customer", "customer123");
            new LoginPage(loginInfo);
        });

        // Layout setup
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(viewPortfolioButton);
        buttonPanel.add(buySahamButton);
        buttonPanel.add(buySBNButton);
        buttonPanel.add(simulasiSBNButton);
        buttonPanel.add(logoutButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
    }
}