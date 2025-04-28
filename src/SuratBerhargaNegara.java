import java.time.LocalDate;

class SuratBerhargaNegara {
    String nama;
    double bunga;
    int jangkaWaktu; // dalam bulan
    double kuotaNasional;
    String jatuhTempo; // <-- variabel baru

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, double kuotaNasional, String jatuhTempo) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.kuotaNasional = kuotaNasional;
        this.jatuhTempo = String.valueOf(jatuhTempo);
    }
}
