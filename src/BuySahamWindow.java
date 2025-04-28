import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BuySahamWindow extends JFrame {
    private JComboBox<String> sahamComboBox;
    private JTextField jumlahField;
    private String username;

    public BuySahamWindow(String username) {
        this.username = username;

        setTitle("Beli Saham - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        add(new JLabel("Pilih Saham:", JLabel.CENTER));
        sahamComboBox = new JComboBox<>();
        for (Saham saham : DataCenter.availableSahamList) {
            sahamComboBox.addItem(saham.kode + " - " + saham.namaPerusahaan);
        }
        add(sahamComboBox);

        add(new JLabel("Masukkan Jumlah Lembar:", JLabel.CENTER));
        jumlahField = new JTextField();
        add(jumlahField);

        JButton beliButton = new JButton("Beli");
        add(beliButton);

        beliButton.addActionListener(beliAction());
    }

    private ActionListener beliAction() {
        return e -> {
            int selectedIndex = sahamComboBox.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Pilih saham terlebih dahulu!");
                return;
            }

            Saham selectedSaham = DataCenter.availableSahamList.get(selectedIndex);

            try {
                int jumlah = Integer.parseInt(jumlahField.getText());

                if (jumlah <= 0) {
                    JOptionPane.showMessageDialog(this, "Jumlah lembar harus lebih dari 0!");
                    return;
                }

                double totalPembelian = selectedSaham.harga * jumlah;

                // Tambahkan ke portofolio user
                DataCenter.allUserSahamList.add(new InvestasiSaham(username, selectedSaham, jumlah, totalPembelian)); // ✅ Correct constructor

                JOptionPane.showMessageDialog(this, "Berhasil membeli saham!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan jumlah lembar yang valid!");
            }
        };
    }
}
