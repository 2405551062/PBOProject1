import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BuySBNWindow extends JFrame {
    private JComboBox<String> sbnComboBox;
    private JTextField nominalField;
    private String username;

    public BuySBNWindow(String username) {
        this.username = username;

        setTitle("Beli SBN - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        add(new JLabel("Pilih SBN:", JLabel.CENTER));
        sbnComboBox = new JComboBox<>();
        for (SuratBerhargaNegara sbn : DataCenter.availableSBNList) {
            sbnComboBox.addItem(sbn.nama);
        }
        add(sbnComboBox);

        add(new JLabel("Masukkan Nominal Pembelian (Rp):", JLabel.CENTER));
        nominalField = new JTextField();
        add(nominalField);

        JButton beliButton = new JButton("Beli");
        add(beliButton);

        beliButton.addActionListener(beliAction());
    }

    private ActionListener beliAction() {
        return e -> {
            int selectedIndex = sbnComboBox.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Pilih SBN terlebih dahulu!");
                return;
            }

            SuratBerhargaNegara selectedSBN = DataCenter.availableSBNList.get(selectedIndex);

            try {
                double nominal = Double.parseDouble(nominalField.getText());

                if (nominal <= 0) {
                    JOptionPane.showMessageDialog(this, "Nominal harus lebih dari 0!");
                    return;
                }

                if (selectedSBN.kuotaNasional < nominal) {
                    JOptionPane.showMessageDialog(this, "Kuota nasional tidak mencukupi!");
                    return;
                }

                // Kurangi kuota nasional
                selectedSBN.kuotaNasional -= nominal;

                // Tambahkan ke portofolio user
                DataCenter.allUserSbnList.add(new InvestasiSBN(username, selectedSBN, nominal)); // ✅ No error now

                JOptionPane.showMessageDialog(this, "Berhasil membeli SBN!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan nominal yang valid!");
            }
        };
    }
}
