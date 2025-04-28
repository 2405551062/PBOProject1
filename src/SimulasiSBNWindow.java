import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimulasiSBNWindow extends JFrame {
    private final JTextField amountField;
    private final JTextField rateField;
    private final JTextField durationField;
    private final JTextArea resultArea;

    public SimulasiSBNWindow(String username) {
        setTitle("Simulasi SBN - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Judul
        JLabel headerLabel = new JLabel("Simulasi Perhitungan SBN untuk " + username, JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);

        // Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Jumlah Investasi (Rp):"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Suku Bunga Tahunan (%):"));
        rateField = new JTextField();
        inputPanel.add(rateField);

        inputPanel.add(new JLabel("Durasi (tahun):"));
        durationField = new JTextField();
        inputPanel.add(durationField);

        JButton simulateButton = new JButton("Hitung Simulasi");
        inputPanel.add(simulateButton);

        add(inputPanel, BorderLayout.CENTER);

        // Untuk menampilkan hasil
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Hasil Simulasi"));
        add(scrollPane, BorderLayout.SOUTH);

        // Tombol aksi
        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSimulation();
            }
        });
    }

    private void calculateSimulation() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double rate = Double.parseDouble(rateField.getText());
            int duration = Integer.parseInt(durationField.getText());

            // Rumus kupon per bulan
            double kuponBulanan = (rate / 100.0 / 12.0) * 0.9 * amount;
            int totalBulan = duration * 12;
            double totalKupon = kuponBulanan * totalBulan;

            resultArea.setText(String.format(
                    "Jumlah Investasi: Rp %.2f\nSuku Bunga Tahunan: %.2f%%\nDurasi: %d tahun (%d bulan)\n\n"
                            + "Estimasi Kupon per Bulan (Setelah Pajak): Rp %.2f\n"
                            + "Total Kupon Selama %d Tahun: Rp %.2f",
                    amount, rate, duration, totalBulan, kuponBulanan, duration, totalKupon
            ));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid! Pastikan semua bidang diisi dengan angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
