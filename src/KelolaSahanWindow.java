import javax.swing.*;

class KelolaSahamWindow extends JFrame {
    public KelolaSahamWindow() {
        setTitle("Kelola Saham");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Halaman Kelola Saham", JLabel.CENTER));
    }
}
