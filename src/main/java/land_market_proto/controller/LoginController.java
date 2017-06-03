package land_market_proto.controller;

import land_market_proto.model.Seller;
import land_market_proto.model.SelletAuthType;
import land_market_proto.repository.SellerController;
import land_market_proto.utility.IUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nik_NB on 02.06.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private final SellerController sellerController;
    private final IUtility utility;

    @Autowired
    public LoginController(SellerController sellerController, IUtility utility) {
        this.sellerController = sellerController;
        this.utility = utility;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login1(@RequestBody SelletAuthType authType) {

        if (authType == null || authType.getLogin() == null || authType.getPassword() == null) {
            return new ResponseEntity<>("Error, there is no auth info", HttpStatus.UNAUTHORIZED);
        }
        if (authType.getLogin().equals("") || authType.getPassword().equals("")) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.UNAUTHORIZED);
        } else if (sellerController.findByLogin(authType.getLogin()) != null) {
            Seller seller = sellerController.findByLogin(authType.getLogin());
            if (!utility.isPasswordCorrect(authType.getPassword(), seller.getPassword())) {
                return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>("{\"token\":" + "\"" + utility.buildJwts(seller.getLogin()) + "\"}", HttpStatus.OK);

        }
            return new ResponseEntity<>("Please register", HttpStatus.UNAUTHORIZED);
        }


}
