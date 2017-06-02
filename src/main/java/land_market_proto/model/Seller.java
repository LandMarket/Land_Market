package land_market_proto.model;

import org.springframework.data.annotation.Id;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public class Seller {

    @Id
    private String id;


    //@Field("login")
    //@Indexed(unique = true)
    private String login;
    private String email;
    //@Field("password")
    private String password;
    private String confirm;
    //@Field("email")
    //private String email;
    //@Field("confirm")
    //private String confirm;
    //@Field("passport")
//    private String passport;
//    //@Field("companyName")
//    private String companyName;
//    //@Field("phone")
//    private String phone;
//   //@Field("address")
//    private String address;
    //@Field("email")

    //@Field("managerName")
//    private String managerName;
//    //@Field("skype")
//    private String skype;


    public Seller() {
    }

//    public Seller(String login, String password, String passport, String companyName, String phone, String address, String email, String managerName, String skype) {
//        this.login = login;
//        this.password = password;
//        this.passport = passport;
//        this.companyName = companyName;
//        this.phone = phone;
//        this.address = address;
//        this.email = email;
//        this.managerName = managerName;
//        this.skype = skype;
//        this.login = login;
//        this.password = password;
//    }

    public Seller(String login, String email, String password,  String confirm) {

        this.login = login;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
    }


//    public Seller(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getPassport() {
//        return passport;
//    }
//
//    public void setPassport(String passport) {
//        this.passport = passport;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }



//    public String getManagerName() {
//        return managerName;
//    }
//
//    public void setManagerName(String managerName) {
//        this.managerName = managerName;
//    }
//
//    public String getSkype() {
//        return skype;
//    }
//
//    public void setSkype(String skype) {
//        this.skype = skype;
//    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
