Project 1
--

Main.java
--
![image](https://github.com/user-attachments/assets/b7c96b0b-0c30-4252-9756-c285c587df42)

class Main ()

Main adalah kelas utama (entry point) dari program. Di dalam metode main, program:
Membuat objek UsersandPasswords, yang mungkin berisi data username dan password.
Membuat objek LoginPage dengan melewatkan informasi login (loginInfo) yang diperoleh dari UsersandPasswords. Ini berarti aplikasi akan menampilkan halaman login yang menggunakan data login yang sudah disiapkan.
- Class UsersandPasswords
adalah kelas yang bertanggung jawab untuk menyimpan dan menyediakan daftar username dan password. Biasanya berisi method seperti getLoginInfo() yang mengembalikan data tersebut, kemungkinan besar dalam bentuk struktur data seperti HashMap<String, String>.
- Class LoginPage
adalah kelas yang membuat tampilan antarmuka untuk login. Objek ini memanfaatkan informasi login dari UsersandPasswords untuk memverifikasi apakah username dan password yang dimasukkan oleh pengguna sudah benar.


UsersandPasswords.java
-- 
UsersandPasswords adalah sebuah kelas yang bertanggung jawab untuk menyimpan informasi username dan password. Penjelasan tiap bagian:
- HashMap<String, String> logininfo: Struktur data yang menyimpan pasangan username dan password (key-value).

- Konstruktor UsersandPasswords(): Saat objek dibuat, langsung dimasukkan dua akun:
|Username admin dengan password 12345|
|Username customer dengan password abcde|

- Method getLoginInfo(): Mengembalikan objek HashMap berisi semua username dan password yang telah disimpan. Method ini dipanggil oleh kelas lain (seperti Main atau LoginPage) untuk mendapatkan data login.

LoginPage.java
--
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
 
AdminDashboard.java
--
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

BuySahamWindow.java
--
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

