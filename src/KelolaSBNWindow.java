import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Window Admin untuk Kelola SBN
class KelolaSBNWindow extends JFrame {
    private List<SuratBerhargaNegara> daftarSBN = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> sbnList;

    public KelolaSBNWindow() {
        setTitle("Kelola SBN");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // List untuk menampilkan SBN
        sbnList = new JList<>(listModel);
        add(new JScrollPane(sbnList), BorderLayout.CENTER);

        // Panel tombol
        JPanel panelButton = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton tambahButton = new JButton("Tambah SBN");
        JButton kembaliButton = new JButton("Kembali");

        panelButton.add(tambahButton);
        panelButton.add(kembaliButton);
        add(panelButton, BorderLayout.SOUTH);

        // Aksi tombol tambah
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahSBN();
            }
        });

        // Aksi tombol kembali
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Menutup window
            }
        });
    }

    private void tambahSBN() {
        // Membuka dialog input untuk data SBN
        JTextField namaField = new JTextField();
        JTextField bungaField = new JTextField();
        JTextField jangkaField = new JTextField();
        JTextField jatuhTempoField = new JTextField();
        JTextField kuotaField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nama:"));
        panel.add(namaField);
        panel.add(new JLabel("Bunga (%):"));
        panel.add(bungaField);
        panel.add(new JLabel("Jangka Waktu (tahun):"));
        panel.add(jangkaField);
        panel.add(new JLabel("Tanggal Jatuh Tempo (YYYY-MM-DD):"));
        panel.add(jatuhTempoField);
        panel.add(new JLabel("Kuota Nasional:"));
        panel.add(kuotaField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah SBN Baru", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nama = namaField.getText();
                double bunga = Double.parseDouble(bungaField.getText());
                int jangkaWaktu = Integer.parseInt(jangkaField.getText());
                String jatuhTempo = jatuhTempoField.getText();
                int kuotaNasional = Integer.parseInt(kuotaField.getText());

                SuratBerhargaNegara sbn = new SuratBerhargaNegara(nama, bunga, jangkaWaktu, kuotaNasional, jatuhTempo);
                daftarSBN.add(sbn);
                listModel.addElement(sbn.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid. Pastikan format angka benar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
