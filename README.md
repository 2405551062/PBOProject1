Project 1
-

1.Main.java
-

class Main ()

Main adalah kelas utama (entry point) dari program. Di dalam metode main, program:
Membuat objek UsersandPasswords, yang mungkin berisi data username dan password.
Membuat objek LoginPage dengan melewatkan informasi login (loginInfo) yang diperoleh dari UsersandPasswords. Ini berarti aplikasi akan menampilkan halaman login yang menggunakan data login yang sudah disiapkan.
- Class UsersandPasswords
adalah kelas yang bertanggung jawab untuk menyimpan dan menyediakan daftar username dan password. Biasanya berisi method seperti getLoginInfo() yang mengembalikan data tersebut, kemungkinan besar dalam bentuk struktur data seperti HashMap<String, String>.
- Class LoginPage
adalah kelas yang membuat tampilan antarmuka untuk login. Objek ini memanfaatkan informasi login dari UsersandPasswords untuk memverifikasi apakah username dan password yang dimasukkan oleh pengguna sudah benar.

2.LoginPage.java
-
![image](https://github.com/user-attachments/assets/b7c96b0b-0c30-4252-9756-c285c587df42)

LoginPage adalah kelas yang bertanggung jawab untuk membuat dan mengelola tampilan antarmuka pengguna untuk proses login. 

Detail penjelasan:
- HashMap<String,String> logininfo: Menyimpan daftar username dan password yang valid, diwariskan dari UsersandPasswords.
- JFrame window: Jendela utama aplikasi yang akan ditampilkan.
- JTextField userIDField: Input field untuk username.
- JPasswordField userPasswordField: Input field untuk password.
- JLabel messageLabel: Label untuk menampilkan pesan sukses/gagal login.
- JButton loginButton: Tombol untuk mengirimkan form login.

Di dalam konstruktor LoginPage():
- Membuat layout tampilan login, termasuk input username, password, tombol login, dan label pesan.
- Menghubungkan loginButton dengan aksi (ActionListener).

Di dalam method actionPerformed():
- Saat tombol "Login" ditekan:
  - Mengambil input username dan password.
- Memeriksa apakah username ada dalam logininfo.

  - Jika ada dan password cocok ➔ tampilkan pesan "Login Berhasil", tutup jendela login, lalu buka AdminDashboard (jika user adalah admin) atau CustomerPage (untuk user biasa).

  - Jika password salah ➔ tampilkan pesan "Password salah".

  - Jika username tidak ditemukan ➔ tampilkan pesan "Username tidak ditemukan".

3.UsersandPasswords.java
-
UsersandPasswords adalah sebuah kelas yang bertanggung jawab untuk menyimpan informasi username dan password. Penjelasan tiap bagian:
- HashMap<String, String> logininfo: Struktur data yang menyimpan pasangan username dan password (key-value).

- Konstruktor UsersandPasswords(): Saat objek dibuat, langsung dimasukkan dua akun:
|Username admin dengan password 12345|
|Username customer dengan password abcde|

- Method getLoginInfo(): Mengembalikan objek HashMap berisi semua username dan password yang telah disimpan. Method ini dipanggil oleh kelas lain (seperti Main atau ) untuk mendapatkan data login.

4.AdminDashboard.java
-
![image](https://github.com/user-attachments/assets/9658cad4-3aa7-4829-929f-b388eac1cca3)


AdminDashboard adalah kelas yang membuat tampilan khusus untuk user dengan role Admin setelah berhasil login. 

Penjelasan detail:

- AdminDashboard memperluas JFrame, artinya class ini langsung membangun jendela (window) baru.

- Di dalam konstruktor:
  - Judul jendela diatur menjadi "Admin Dashboard".
  - Membuat label sambutan "Selamat Datang Admin" dengan font besar dan tebal.
  - Tiga tombol disediakan:
      - Kelola Saham ➔ membuka window KelolaSahamWindow.

      - Kelola SBN ➔ membuka window KelolaSBNWindow.

      - Logout ➔ menutup dashboard admin dan membuka kembali halaman login (LoginPage), dengan membuat ulang data login baru (hardcode admin dan customer).

- Menggunakan BorderLayout untuk mengatur layout, dengan tombol-tombol diatur menggunakan GridLayout vertikal.

5.Saham.java
-
Saham adalah class model yang merepresentasikan data satu jenis saham dalam sistem.

Penjelasan atribut:
- kode: kode unik untuk saham, misalnya "BBCA", "TLKM".
- namaPerusahaan: nama perusahaan yang menerbitkan saham tersebut.
- harga: harga per lembar saham.

Constructor:
- Digunakan untuk membuat objek Saham baru dengan mengisi ketiga atribut tersebut.

Saham dipakai oleh class lain seperti:
- DataCenter → dalam availableSahamList.
- InvestasiSaham → menyimpan jenis saham yang dimiliki user.
- KelolaSahamWindow → untuk menambah dan menghapus daftar saham yang tersedia.
- BuySahamWindow → untuk membeli saham.
- PortfolioWindow → untuk menampilkan portofolio saham user.

6.KelolaSahamWindow.java
-
![image](https://github.com/user-attachments/assets/9171f47c-2c75-4fd8-aacf-4367429ab9e5)

KelolaSahamWindow adalah kelas yang membangun tampilan admin untuk mengelola daftar saham yang tersedia di aplikasi.

Penjelasan setiap bagian:

Atribut:
- sahamModel: model tabel untuk menampung data saham.
- sahamTable: tabel yang menampilkan daftar saham.
- kodeField, namaField, hargaField: input field untuk menambahkan saham baru.

Constructor KelolaSahamWindow():
- Membuat window berjudul "Kelola Saham" dengan layout BorderLayout.
- Bagian tengah (CENTER) menampilkan tabel daftar saham.
- Bagian bawah (SOUTH) menampilkan form input untuk kode saham, nama perusahaan, dan harga.
- Dua tombol:
  - Tambah Saham ➔ menambahkan data saham baru ke dalam DataCenter.availableSahamList.
  - Hapus Saham ➔ menghapus saham yang dipilih dari tabel.
 
Method loadSahamData():
- Memuat ulang data saham dari DataCenter.availableSahamList ke dalam tabel (sahamModel).

Method addAction():
- Membaca input kode, nama perusahaan, dan harga.
- Validasi input tidak kosong dan harga berupa angka.
- Membuat objek Saham baru dan menambahkannya ke DataCenter.
- Refresh tabel saham.

Method deleteAction():
- Menghapus saham yang dipilih dari tabel dan dari DataCenter.
- Memastikan ada konfirmasi pengguna sebelum menghapus.

7.SuratBerhargaNegara.java
-
SuratBerhargaNegara (disingkat SBN) adalah class model yang mewakili data produk investasi berupa surat utang yang diterbitkan negara.

Penjelasan atribut:
- nama: nama produk SBN, contoh: ORI022, SR017, dll.
- bunga: persentase bunga tahunan.
- jangkaWaktu: lama waktu jatuh tempo dalam bulan.
- kuotaNasional: total nominal dana yang tersedia untuk dibeli masyarakat.
- jatuhTempo: tanggal jatuh tempo SBN dalam format yyyy-mm-dd.

Constructor:
- Digunakan untuk membuat objek baru SuratBerhargaNegara dengan semua atribut yang telah ditentukan.
- Ada sedikit kelebihan (String.valueOf(jatuhTempo)) karena jatuhTempo sudah bertipe String, tetapi tidak mempengaruhi fungsionalitas.


8.KelolaSBNWindow.java
-
![image](https://github.com/user-attachments/assets/1b182fb7-773f-42dc-9c96-45be849d13d7)


KelolaSBNWindow adalah kelas GUI untuk admin yang memungkinkan pengelolaan daftar Surat Berharga Negara (SBN) dalam aplikasi.

Penjelasan per bagian:

Atribut:
- sbnModel: model tabel untuk menyimpan dan menampilkan data SBN.
- sbnTable: tabel GUI yang menampilkan data dari DataCenter.availableSBNList.
- namaField, bungaField, jangkaWaktuField, jatuhTempoField, kuotaField: field input untuk menambahkan SBN baru.

Constructor KelolaSBNWindow():
- Membuat layout BorderLayout.
- Bagian atas (CENTER) menampilkan tabel data SBN.
- Bagian bawah (SOUTH) berisi form input data SBN dan dua tombol:
  - Tambah SBN: menambahkan data SBN baru.
  - Hapus SBN: menghapus SBN yang dipilih dari tabel dan daftar.

Method loadSbnData():
- Memuat ulang semua data SBN dari DataCenter.availableSBNList ke dalam tabel.

Method addAction():
- Membaca inputan dari field.
- Validasi agar semua field terisi dan numerik valid.
- Membuat objek SuratBerhargaNegara dan menambahkannya ke DataCenter.
- Mereset form input setelah berhasil.

Method deleteAction():
- Menghapus baris SBN yang dipilih dari tabel dan daftar data.
- Menampilkan konfirmasi sebelum menghapus.

9.CustomerPage.java
-
![image](https://github.com/user-attachments/assets/b477b828-a5e5-49ba-9ee9-de0c79fa7a8c)


CustomerPage adalah kelas yang membangun halaman utama untuk pengguna dengan role sebagai nasabah (customer) setelah berhasil login.

Detail penjelasan:
- CustomerPage memperluas JFrame, artinya membuat jendela GUI yang berdiri sendiri.
- Parameter username digunakan untuk menampilkan nama pengguna yang sedang login di bagian atas.
- Komponen:
  - Label sambutan: Menampilkan “Selamat Datang, [username]”.
  - Tombol - tombol:
      - Portofolio ➜ membuka PortfolioWindow untuk melihat investasi nasabah.
      - Beli Saham ➜ membuka BuySahamWindow.
      - Beli SBN ➜ membuka BuySBNWindow.
      - Simulasi SBN ➜ membuka SimulasiSBNWindow untuk mensimulasikan imbal hasil SBN.
      - Logout ➜ keluar dari halaman nasabah dan membuka kembali halaman login dengan data akun yang telah ditentukan (admin dan customer).
    - Layout menggunakan BorderLayout dengan:
      - Label sambutan di bagian atas (BorderLayout.NORTH).
      - Panel tombol dengan GridLayout vertikal (5 tombol disusun ke bawah).

10.InvestasiSaham.java
-  
InvestasiSaham adalah class model yang merepresentasikan satu transaksi atau kepemilikan saham oleh user.

Penjelasan tiap bagian:

Atribut:
- username: nama pengguna yang memiliki saham.
- saham: objek Saham yang menunjukkan jenis saham yang dibeli.
- jumlahLembar: jumlah lembar saham yang dimiliki user.
- totalPembelian: total nilai pembelian saham berdasarkan harga saat transaksi.

Constructor:
- Menginisialisasi semua atribut berdasarkan data transaksi pembelian saham.

Method:
- getUsername(): mengembalikan nama pengguna yang memiliki investasi ini.

11.BuySahamWindow.java
-
![image](https://github.com/user-attachments/assets/5fc00662-403a-4a3f-a002-936b7b7e3029)


BuySahamWindow adalah kelas yang membuat tampilan antarmuka pengguna untuk membeli saham.

Penjelasan per bagian:

- Kelas ini memperluas JFrame, artinya membangun jendela baru untuk proses pembelian saham.

- Variabel:
    -  username: Username pengguna yang melakukan pembelian.
    -  sahamComboBox: Dropdown menu untuk memilih saham yang tersedia.
    -  jumlahField: Inputan untuk memasukkan jumlah lembar saham yang ingin dibeli.

-  Konstruktor BuySahamWindow(String username):
    - Menyetel jendela dengan layout dan komponen GUI seperti label, combo box, input field, dan tombol.
    - ComboBox diisi dengan daftar saham yang diambil dari DataCenter.availableSahamList.
    - Tombol "Beli" dikaitkan dengan beliAction().

- Method beliAction() . Saat tombol "Beli" ditekan:
    - Memastikan user memilih saham.

    - Membaca input jumlah lembar saham.

    - Validasi agar jumlah lebih dari 0.

    - Menghitung total pembelian berdasarkan harga saham × jumlah lembar.

    - Menambahkan data investasi ke DataCenter.allUserSahamList.

    - Menampilkan pesan "Berhasil membeli saham" dan menutup jendela pembelian.

12.InvestasiSBN.java
-
InvestasiSBN adalah class model yang mewakili investasi pengguna terhadap Surat Berharga Negara (SBN).

Penjelasan atribut dan method:

Atribut:
- username: nama user yang melakukan pembelian SBN.
- sbn: objek SuratBerhargaNegara yang berisi detail produk SBN yang dibeli.
- nominalPembelian: jumlah uang yang diinvestasikan user pada SBN tersebut.

Constructor:
- Menerima parameter username, sbn, dan nominalPembelian untuk menginisialisasi objek.

Method:
- getUsername(): mengembalikan nama pengguna.
- hitungBungaPerBulan(): menghitung jumlah bunga bulanan berdasarkan suku bunga tahunan dari objek sbn. Rumus: %bunga / 12 bulan * 90% * nominal investasi. 

13.BuySBNWindow.java
-
![image](https://github.com/user-attachments/assets/086752cb-4f07-4835-aed1-fa90426ece11)


BuySBNWindow adalah kelas yang menyediakan tampilan antarmuka untuk membeli Surat Berharga Negara (SBN).

Penjelasan per bagian:
- Kelas ini memperluas JFrame, yang berarti membangun jendela (window) baru.
- Variabel:
  - username: menyimpan nama pengguna yang sedang login.
  - sbnComboBox: dropdown berisi daftar SBN yang tersedia.
  - nominalField: field tempat pengguna memasukkan jumlah nominal pembelian.\
- Konstruktor BuySBNWindow(String username):
  - Menyiapkan window dengan layout dan komponen GUI.
  - sbnComboBox diisi dari DataCenter.availableSBNList, yaitu daftar SBN yang tersedia untuk dibeli.
  - Tombol "Beli" dikaitkan dengan method beliAction().
- Method beliAction(). Saat tombol diklik
  - Memastikan user memilih SBN.
  - Membaca dan memvalidasi nominal pembelian.
  - Memastikan nominal > 0 dan tidak melebihi kuota nasional SBN yang dipilih.
  - Jika valid:
    - Mengurangi kuotaNasional dari objek SuratBerhargaNegara yang dibeli.
    - Menambahkan pembelian ke portofolio pengguna melalui DataCenter.allUserSbnList.
    - Menampilkan pesan sukses dan menutup jendela.

14.SimulasiSBNWindow.java
-
![image](https://github.com/user-attachments/assets/67cf3b8b-fd01-4d03-b8c1-6f04de4555db)


SimulasiSBNWindow adalah jendela yang digunakan oleh nasabah (customer) untuk mensimulasikan hasil investasi SBN berdasarkan jumlah uang, bunga tahunan, dan durasi investasi.

Penjelasan:

Field input:
- amountField: jumlah uang yang ingin diinvestasikan.
- rateField: bunga tahunan (dalam persen).
- durationField: durasi investasi dalam tahun.

Tombol “Hitung Simulasi”:
- Memicu method calculateSimulation() saat ditekan.

Area hasil:
- resultArea: menampilkan hasil simulasi dengan format yang rapi.

Method calculateSimulation()
melakukan perhitungan kupon bulanan SBN berdasarkan rumus:

kuponBulanan = (rate / 100 / 12) * 0.9 * amount

- 0.9 adalah asumsi setelah pajak 10%.
- Total kupon = kupon per bulan × jumlah bulan.

15.PortfolioWindow.java
-
![image](https://github.com/user-attachments/assets/d4a30e46-e6f5-4dbd-bb5d-c91c1804ab3e)


PortfolioWindow adalah kelas GUI untuk menampilkan portofolio investasi saham dan SBN milik user yang sedang login.

Fungsi utama:
- Menampilkan dua tab: Saham dan SBN.
- Menampilkan detail investasi saham:
  - kode saham, perusahaan, lembar dimiliki, total pembelian, nilai pasar.
- Menampilkan detail investasi SBN:
  - nama SBN, nominal pembelian, dan bunga bulanan.
- Menyediakan fitur jual saham melalui tombol.

Penjelasan:

Konstruktor PortfolioWindow(String username)
- Mengambil data saham dan SBN milik user dari DataCenter.
- Mengatur GUI menggunakan JTabbedPane:
  - Tab Saham: menampilkan tabel sahamTable dan tombol “Jual Saham”.
  - Tab SBN: menampilkan tabel dengan bunga per bulan yang dihitung dari InvestasiSBN.hitungBungaPerBulan().
  
loadSahamData()
- Memuat data saham pengguna ke dalam sahamModel.
- Menghitung nilai pasar = harga x jumlahLembar.

sellSahamAction()
- Listener untuk tombol "Jual Saham":
  - Mengambil kode saham yang dipilih.
  - Meminta input jumlah lembar yang ingin dijual.
  - Memanggil DataCenter.sellSaham() untuk memproses penjualan.
  - Jika berhasil, data diperbarui.

16.DataCenter.java
-
DataCenter adalah pusat data bersama (global storage) untuk seluruh data investasi dan daftar produk yang tersedia dalam aplikasi.

Penjelasan tiap bagian:
- availableSahamList: daftar semua saham yang tersedia untuk dibeli.
- availableSBNList: daftar semua Surat Berharga Negara (SBN) yang tersedia.
- allUserSahamList: daftar seluruh transaksi saham milik semua user.
- allUserSbnList: daftar seluruh transaksi SBN milik semua user.

Method fungsional:
- userSahamList(String username):
Mengembalikan daftar investasi saham milik user tertentu, menggunakan filter berdasarkan username.
- userSbnList(String username) :
Mengembalikan daftar investasi SBN milik user tertentu.
- sellSaham(String username, String kodeSaham, int jumlahLembarJual) Logika untuk menjual saham :
  - Mencari investasi saham berdasarkan username dan kodeSaham.
  - Jika ditemukan dan jumlah lembar mencukupi, kurangi jumlah lembar dan total pembelian.
  - Jika sisa lembar = 0, hapus investasi dari list.
  - Return true jika berhasil, false jika gagal (misal tidak cukup lembar atau tidak ditemukan).
