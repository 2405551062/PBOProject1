import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

class PortfolioWindow extends JFrame {
    private List<InvestasiSaham> sahamList;
    private List<InvestasiSBN> sbnList;
    private JTable sahamTable;
    private DefaultTableModel sahamModel;
    private String username;

    public PortfolioWindow(String username) {
        this(username, DataCenter.userSahamList(username), DataCenter.userSbnList(username));
    }

    public PortfolioWindow(String username, List<InvestasiSaham> sahamList, List<InvestasiSBN> sbnList) {
        this.username = username;
        this.sahamList = sahamList;
        this.sbnList = sbnList;

        setTitle("Portofolio - " + username);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tabel Saham
        String[] sahamColumns = {"Kode", "Perusahaan", "Lembar Dimiliki", "Total Pembelian (Rp)", "Nilai Pasar (Rp)"};
        sahamModel = new DefaultTableModel(sahamColumns, 0);

        loadSahamData(); // Load saham

        sahamTable = new JTable(sahamModel);
        JPanel sahamPanel = new JPanel(new BorderLayout());
        sahamPanel.add(new JScrollPane(sahamTable), BorderLayout.CENTER);

        JButton sellButton = new JButton("Jual Saham");
        sellButton.addActionListener(sellSahamAction());
        sahamPanel.add(sellButton, BorderLayout.SOUTH);

        tabbedPane.addTab("Saham", sahamPanel);

        // Tabel SBN
        String[] sbnColumns = {"Nama", "Nominal Investasi (Rp)", "Bunga per Bulan (Rp)"};
        DefaultTableModel sbnModel = new DefaultTableModel(sbnColumns, 0);

        for (InvestasiSBN inv : sbnList) {
            Object[] row = {
                    inv.sbn.nama,
                    String.format("%,.2f", inv.nominalPembelian),
                    String.format("%,.2f", inv.hitungBungaPerBulan())
            };
            sbnModel.addRow(row);
        }

        JTable sbnTable = new JTable(sbnModel);
        tabbedPane.addTab("SBN", new JScrollPane(sbnTable));

        add(new JLabel("Portofolio Saham dan SBN " + username, JLabel.CENTER), BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void loadSahamData() {
        sahamModel.setRowCount(0); // Refresh
        for (InvestasiSaham inv : sahamList) {
            double nilaiPasar = inv.saham.harga * inv.jumlahLembar;
            Object[] row = {
                    inv.saham.kode,
                    inv.saham.namaPerusahaan,
                    inv.jumlahLembar,
                    String.format("%,.2f", inv.totalPembelian),
                    String.format("%,.2f", nilaiPasar)
            };
            sahamModel.addRow(row);
        }
    }

    private ActionListener sellSahamAction() {
        return e -> {
            int selectedRow = sahamTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih saham yang ingin dijual!");
                return;
            }

            String kodeSaham = (String) sahamModel.getValueAt(selectedRow, 0);
            String jumlahInput = JOptionPane.showInputDialog(this, "Masukkan jumlah lembar yang ingin dijual:");

            if (jumlahInput == null) return; // User cancel

            try {
                int jumlahJual = Integer.parseInt(jumlahInput);
                if (jumlahJual <= 0) {
                    JOptionPane.showMessageDialog(this, "Jumlah lembar harus lebih dari 0!");
                    return;
                }

                boolean berhasil = DataCenter.sellSaham(username, kodeSaham, jumlahJual);

                if (berhasil) {
                    JOptionPane.showMessageDialog(this, "Saham berhasil dijual!");
                    // Refresh data
                    sahamList = DataCenter.userSahamList(username);
                    loadSahamData();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menjual saham. Cek jumlah lembar yang dimiliki!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!");
            }
        };
    }
}
