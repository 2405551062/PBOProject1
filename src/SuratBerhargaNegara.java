import java.time.LocalDate;

public class SuratBerhargaNegara {
    private String nama;
    private double bunga;
    private int jangkaWaktu; // dalam tahun
    private LocalDate tanggalJatuhTempo;
    private double kuotaNasional;

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, LocalDate tanggalJatuhTempo, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.kuotaNasional = kuotaNasional;
    }

    public String getNama() {
        return nama;
    }

    public double getBunga() {
        return bunga;
    }

    public int getJangkaWaktu() {
        return jangkaWaktu;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public double getKuotaNasional() {
        return kuotaNasional;
    }

    public void setKuotaNasional(double kuotaNasional) {
        this.kuotaNasional = kuotaNasional;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Bunga: " + bunga + "%, Jangka Waktu: " + jangkaWaktu + " tahun, Tanggal Jatuh Tempo: " + tanggalJatuhTempo + ", Kuota Nasional: Rp" + kuotaNasional;
    }
}
