import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LoginPage implements ActionListener {
    private HashMap<String,String> logininfo;
    private JFrame window;
    private JTextField userIDField;
    private JPasswordField userPasswordField;
    private JLabel messageLabel;
    private JButton loginButton;

    LoginPage(HashMap<String,String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;

        // Main frame
        window = new JFrame("AplikasiInvestasi");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        window.setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Judul
        JLabel titleLabel = new JLabel("Selamat datang di Aplikasi Investasi!", JLabel.CENTER);
        titleLabel.setFont(new Font("Osward", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Menggunakan GridBagLayout di Panel untuk kontrol yang lebih presisi
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen Username
        JLabel userIDLabel = new JLabel("User ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userIDLabel, gbc);

        userIDField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(userIDField, gbc);

        // Komponen Password
        JLabel userPasswordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(userPasswordLabel, gbc);

        userPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(userPasswordField, gbc);

        // Tombol login
        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        // Label pesan
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font(null, Font.ITALIC, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(messageLabel, gbc);

        // Memastikan agar formPanel ditengah
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Agar terpapar ke window
        window.add(mainPanel);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(logininfo.containsKey(userID)) {
                if(logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Berhasil");
                    window.dispose();

                    // Open different windows based on user role
                    if(userID.equals("admin")) {
                        AdminDashboard adminPage = new AdminDashboard();
                    } else if(userID.equals("customer")) {
                        CustomerPage customerPage = new CustomerPage(userID);
                    }
                }
                else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Password salah");
                }
            }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username tidak ditemukan");
            }
        }
    }
}