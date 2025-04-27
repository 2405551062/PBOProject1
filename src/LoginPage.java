import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginPage implements ActionListener {
    private final HashMap<String,String> logininfo;
    private final JFrame window;
    private final JTextField userIDField;
    private final JPasswordField userPasswordField;
    private final JLabel messageLabel;
    private final JButton loginButton;

    public LoginPage(HashMap<String,String> loginInfoOriginal) {
        this.logininfo = loginInfoOriginal;

        // Frame
        window = new JFrame("Aplikasi Investasi");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(450, 350);
        window.setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Judul
        JLabel titleLabel = new JLabel("Selamat Datang di Aplikasi Investasi", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen username
        JLabel userIDLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userIDLabel, gbc);

        userIDField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(userIDField, gbc);

        // Komponen password
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
        loginButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        // Label pesan
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(messageLabel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        window.add(mainPanel);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String userID = userIDField.getText().trim();
            String password = String.valueOf(userPasswordField.getPassword());

            if(logininfo.containsKey(userID)) {
                if(logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Berhasil");
                    window.dispose();

                    // Open appropriate dashboard based on user role
                    SwingUtilities.invokeLater(() -> {
                        if(userID.equals("admin")) {
                            new AdminDashboard().setVisible(true);
                        } else {
                            new CustomerPage(userID).setVisible(true);
                        }
                    });
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Password salah");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username tidak ditemukan");
            }
        }
    }
}