import javax.swing.*;

class PortfolioWindow extends JFrame {
    public PortfolioWindow(String username) {
        setTitle("Portofolio - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Portofolio Saham dan SBN " + username, JLabel.CENTER));
    }
}