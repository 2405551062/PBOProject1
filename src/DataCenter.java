import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class DataCenter {
    public static List<Saham> availableSahamList = new ArrayList<>();
    public static List<SuratBerhargaNegara> availableSBNList = new ArrayList<>();

    public static List<InvestasiSaham> allUserSahamList = new ArrayList<>();
    public static List<InvestasiSBN> allUserSbnList = new ArrayList<>();

    // Ambil semua saham milik user tertentu
    public static List<InvestasiSaham> userSahamList(String username) {
        return allUserSahamList.stream()
                .filter(inv -> inv.username.equals(username))
                .collect(Collectors.toList());
    }

    // Ambil semua SBN milik user tertentu
    public static List<InvestasiSBN> userSbnList(String username) {
        return allUserSbnList.stream()
                .filter(inv -> inv.username.equals(username))
                .collect(Collectors.toList());
    }

    // Jual saham (mengurangi jumlah lembar atau menghapus investasi jika 0)
    public static boolean sellSaham(String username, String kodeSaham, int jumlahLembarJual) {
        for (InvestasiSaham investasi : allUserSahamList) {
            if (investasi.username.equals(username) && investasi.saham.kode.equals(kodeSaham)) {
                if (investasi.jumlahLembar >= jumlahLembarJual) {
                    investasi.jumlahLembar -= jumlahLembarJual;
                    investasi.totalPembelian -= investasi.saham.harga * jumlahLembarJual;

                    if (investasi.jumlahLembar == 0) {
                        allUserSahamList.remove(investasi); // Hapus kalau sudah habis
                    }
                    return true;
                } else {
                    return false; // Tidak cukup lembar
                }
            }
        }
        return false; // Tidak ditemukan
    }
}
