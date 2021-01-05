public class Admin {

    private String loginID;
    private String password;

    public Admin(String loginID, String password) {
        this.loginID = loginID;
        this.password = password;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getPassword() {
        return password;
    }
}
