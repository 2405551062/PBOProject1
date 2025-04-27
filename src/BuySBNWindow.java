import javax.swing.*;

class BuySBNWindow extends JFrame {
    public BuySBNWindow(String username) {
        setTitle("Beli SBN - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Form Pembelian SBN untuk " + username, JLabel.CENTER));
    }
}