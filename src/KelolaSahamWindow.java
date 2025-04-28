import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KelolaSahamWindow extends JFrame {
    private JTextField kodeField;
    private JTextField namaPerusahaanField;
    private JTextField hargaField;
    private JButton tambahButton;
    private JTextArea daftarSahamArea;

    private ArrayList<Saham> daftarSaham; // Data disimpan di memori

    public KelolaSahamWindow() {
        setTitle("Kelola Saham");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        daftarSaham = new ArrayList<>();

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 20, 20));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Kode Saham:"));
        kodeField = new JTextField();
        inputPanel.add(kodeField);

        inputPanel.add(new JLabel("Nama Perusahaan:"));
        namaPerusahaanField = new JTextField();
        inputPanel.add(namaPerusahaanField);

        inputPanel.add(new JLabel("Harga Saham:"));
        hargaField = new JTextField();
        inputPanel.add(hargaField);

        tambahButton = new JButton("Tambah Saham");
        inputPanel.add(tambahButton);

        // Area daftar saham
        daftarSahamArea = new JTextArea();
        daftarSahamArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(daftarSahamArea);

        // Tambahkan listener ke tombol
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahSaham();
            }
        });

        // Layout utama
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void tambahSaham() {
        try {
            String kode = kodeField.getText();
            String namaPerusahaan = namaPerusahaanField.getText();
            double harga = Double.parseDouble(hargaField.getText());

            if (kode.isEmpty() || namaPerusahaan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Saham sahamBaru = new Saham(kode, namaPerusahaan, harga);
            daftarSaham.add(sahamBaru);

            updateDaftarSaham();

            // Bersihkan input
            kodeField.setText("");
            namaPerusahaanField.setText("");
            hargaField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDaftarSaham() {
        daftarSahamArea.setText("");
        for (Saham saham : daftarSaham) {
            daftarSahamArea.append(saham.toString() + "\n");
        }
    }
}
