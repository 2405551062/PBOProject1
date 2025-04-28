class InvestasiSBN {
    SuratBerhargaNegara sbn;
    double nominalPembelian;

    public InvestasiSBN(SuratBerhargaNegara sbn, double nominalPembelian) {
        this.sbn = sbn;
        this.nominalPembelian = nominalPembelian;
    }

    public double hitungBungaPerBulan() {
        return (sbn.bunga / 100.0) / 12.0 * 0.9 * nominalPembelian;
    }
}