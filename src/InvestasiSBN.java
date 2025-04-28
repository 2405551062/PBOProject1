class InvestasiSBN {
    String username;
    SuratBerhargaNegara sbn;
    double nominalPembelian;

    public InvestasiSBN(String username, SuratBerhargaNegara sbn, double nominalPembelian) {
        this.username = username;
        this.sbn = sbn;
        this.nominalPembelian = nominalPembelian;
    }

    public String getUsername() {
        return username;
    }

    public double hitungBungaPerBulan() {
        return (sbn.bunga / 12.0) * nominalPembelian;
    }
}