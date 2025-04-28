class InvestasiSaham {
    String username;
    Saham saham;
    int jumlahLembar;
    double totalPembelian;

    public InvestasiSaham(String username, Saham saham, int jumlahLembar, double totalPembelian) {
        this.username = username;
        this.saham = saham;
        this.jumlahLembar = jumlahLembar;
        this.totalPembelian = totalPembelian;
    }

    public String getUsername() {
        return username;
    }
}