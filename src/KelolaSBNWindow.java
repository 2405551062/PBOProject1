import javax.swing.*;

class KelolaSBNWindow extends JFrame {
    public KelolaSBNWindow() {
        setTitle("Kelola SBN");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Halaman Kelola SBN", JLabel.CENTER));
    }
}
