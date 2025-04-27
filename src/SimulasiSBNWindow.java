import javax.swing.*;

class SimulasiSBNWindow extends JFrame {
    public SimulasiSBNWindow(String username) {
        setTitle("Simulasi SBN - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Simulasi Perhitungan SBN untuk " + username, JLabel.CENTER));
    }
}