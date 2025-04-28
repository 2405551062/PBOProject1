import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

class KelolaSBNWindow extends JFrame {
    private DefaultTableModel sbnModel;
    private JTable sbnTable;
    private JTextField namaField, bungaField, jangkaWaktuField, jatuhTempoField, kuotaField;

    public KelolaSBNWindow() {
        setTitle("Kelola SBN");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabel
        String[] columns = {"Nama", "Bunga (%)", "Jangka Waktu (bln)", "Tanggal Jatuh Tempo", "Kuota Nasional"};
        sbnModel = new DefaultTableModel(columns, 0);
        sbnTable = new JTable(sbnModel);

        loadSbnData();

        add(new JScrollPane(sbnTable), BorderLayout.CENTER);

        // Input
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Bunga (% per tahun):"));
        bungaField = new JTextField();
        inputPanel.add(bungaField);

        inputPanel.add(new JLabel("Jangka Waktu (bulan):"));
        jangkaWaktuField = new JTextField();
        inputPanel.add(jangkaWaktuField);

        inputPanel.add(new JLabel("Tanggal Jatuh Tempo (yyyy-mm-dd):"));
        jatuhTempoField = new JTextField();
        inputPanel.add(jatuhTempoField);

        inputPanel.add(new JLabel("Kuota Nasional:"));
        kuotaField = new JTextField();
        inputPanel.add(kuotaField);

        JButton addButton = new JButton("Tambah SBN");
        JButton deleteButton = new JButton("Hapus SBN");

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(addAction());
        deleteButton.addActionListener(deleteAction());
    }

    private void loadSbnData() {
        sbnModel.setRowCount(0); // Clear table
        for (SuratBerhargaNegara sbn : DataCenter.availableSBNList) {
            sbnModel.addRow(new Object[]{
                    sbn.nama,
                    String.format("%.2f", sbn.bunga),
                    sbn.jangkaWaktu,
                    sbn.jatuhTempo,
                    sbn.kuotaNasional
            });
        }
    }

    private ActionListener addAction() {
        return e -> {
            String nama = namaField.getText().trim();
            String bungaText = bungaField.getText().trim();
            String jangkaText = jangkaWaktuField.getText().trim();
            String jatuhTempo = jatuhTempoField.getText().trim();
            String kuotaText = kuotaField.getText().trim();

            if (nama.isEmpty() || bungaText.isEmpty() || jangkaText.isEmpty() || jatuhTempo.isEmpty() || kuotaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            try {
                double bunga = Double.parseDouble(bungaText);
                int jangkaWaktu = Integer.parseInt(jangkaText);
                int kuotaNasional = Integer.parseInt(kuotaText);

                SuratBerhargaNegara newSbn = new SuratBerhargaNegara(nama, bunga, jangkaWaktu, kuotaNasional, jatuhTempo);
                DataCenter.availableSBNList.add(newSbn);

                loadSbnData();
                JOptionPane.showMessageDialog(this, "SBN berhasil ditambahkan!");

                // Clear input
                namaField.setText("");
                bungaField.setText("");
                jangkaWaktuField.setText("");
                jatuhTempoField.setText("");
                kuotaField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bunga, Jangka Waktu, dan Kuota harus berupa angka!");
            }
        };
    }

    private ActionListener deleteAction() {
        return e -> {
            int selectedRow = sbnTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih SBN yang akan dihapus!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus SBN ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DataCenter.availableSBNList.remove(selectedRow);
                loadSbnData();
                JOptionPane.showMessageDialog(this, "SBN berhasil dihapus!");
            }
        };
    }
}
