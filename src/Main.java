public class Main {
    public static void main(String[] args) {

        UsersandPasswords usersandPasswords = new UsersandPasswords();

        new LoginPage(usersandPasswords.getLoginInfo());

    }
}
