package land_market_proto.model;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public class SelletAuthType {
    String login;
    String password;

    public SelletAuthType() {
    }

    public SelletAuthType(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}