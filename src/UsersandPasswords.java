import java.util.HashMap;

public class UsersandPasswords {

    HashMap<String,String> logininfo = new HashMap<String,String>();

    UsersandPasswords(){
        logininfo.put("admin","12345");
        logininfo.put("customer","abcde");
    }

    public HashMap getLoginInfo(){
        return logininfo;
    }
}
