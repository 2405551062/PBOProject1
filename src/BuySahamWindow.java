import javax.swing.*;

class BuySahamWindow extends JFrame {
    public BuySahamWindow(String username) {
        setTitle("Beli Saham - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Form Pembelian Saham untuk " + username, JLabel.CENTER));
    }
}
