import java.awt.*;
import javax.swing.*;

public class CustomerPage extends JFrame {
    public CustomerPage(String username) {
        setTitle("Halaman Nasabah");
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

        // Layout setup
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        buttonPanel.add(viewPortfolioButton);
        buttonPanel.add(buySahamButton);
        buttonPanel.add(buySBNButton);
        buttonPanel.add(simulasiSBNButton);
        buttonPanel.add(logoutButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
