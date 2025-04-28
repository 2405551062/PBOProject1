import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Window untuk Portofolio
class PortfolioWindow extends JFrame {
    private List<InvestasiSaham> sahamList;
    private List<InvestasiSBN> sbnList;

    // Constructor 1: hanya username (dummy list)
    public PortfolioWindow(String username) {
        this(username, List.of(), List.of()); // Panggil constructor lengkap, tapi kasih list kosong
    }

    // Constructor 2: username + data portofolio
    public PortfolioWindow(String username, List<InvestasiSaham> sahamList, List<InvestasiSBN> sbnList) {
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
        DefaultTableModel sahamModel = new DefaultTableModel(sahamColumns, 0);

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

        JTable sahamTable = new JTable(sahamModel);
        tabbedPane.addTab("Saham", new JScrollPane(sahamTable));

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
}
