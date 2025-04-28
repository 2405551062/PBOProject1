import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

class KelolaSahamWindow extends JFrame {
    private DefaultTableModel sahamModel;
    private JTable sahamTable;
    private JTextField kodeField, namaField, hargaField;

    public KelolaSahamWindow() {
        setTitle("Kelola Saham");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabel
        String[] columns = {"Kode", "Nama Perusahaan", "Harga (Rp)"};
        sahamModel = new DefaultTableModel(columns, 0);
        sahamTable = new JTable(sahamModel);

        loadSahamData();

        add(new JScrollPane(sahamTable), BorderLayout.CENTER);

        // Input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Kode:"));
        kodeField = new JTextField();
        inputPanel.add(kodeField);

        inputPanel.add(new JLabel("Nama Perusahaan:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Harga:"));
        hargaField = new JTextField();
        inputPanel.add(hargaField);

        JButton addButton = new JButton("Tambah Saham");
        JButton deleteButton = new JButton("Hapus Saham");

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(addAction());
        deleteButton.addActionListener(deleteAction());
    }

    private void loadSahamData() {
        sahamModel.setRowCount(0); // Refresh
        for (Saham saham : DataCenter.availableSahamList) {
            sahamModel.addRow(new Object[]{
                    saham.kode,
                    saham.namaPerusahaan,
                    String.format("%,.2f", saham.harga)
            });
        }
    }

    private ActionListener addAction() {
        return e -> {
            String kode = kodeField.getText().trim();
            String nama = namaField.getText().trim();
            String hargaText = hargaField.getText().trim();

            if (kode.isEmpty() || nama.isEmpty() || hargaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            try {
                double harga = Double.parseDouble(hargaText);

                // Buat object baru dan tambah ke list
                Saham newSaham = new Saham(kode, nama, harga);
                DataCenter.availableSahamList.add(newSaham);

                loadSahamData();
                JOptionPane.showMessageDialog(this, "Saham berhasil ditambahkan!");

                // Clear input
                kodeField.setText("");
                namaField.setText("");
                hargaField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga harus berupa angka!");
            }
        };
    }

    private ActionListener deleteAction() {
        return e -> {
            int selectedRow = sahamTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih saham yang akan dihapus!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus saham ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DataCenter.availableSahamList.remove(selectedRow);
                loadSahamData();
                JOptionPane.showMessageDialog(this, "Saham berhasil dihapus!");
            }
        };
    }
}
