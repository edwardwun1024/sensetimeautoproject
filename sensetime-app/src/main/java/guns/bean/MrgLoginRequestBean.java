package guns.bean;

public class MrgLoginRequestBean {

    private String username;
    private String password;
    private String accountType;
    private Integer userId;


    public MrgLoginRequestBean() {
    }

    public MrgLoginRequestBean(String username, String password, String accountType, Integer userId) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MrgLoginRequestBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", userId=" + userId +
                '}';
    }
}
