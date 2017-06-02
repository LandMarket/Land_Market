package land_market_proto.controller;

import land_market_proto.model.Seller;
import land_market_proto.repository.SellerController;
import land_market_proto.utility.IUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nik_NB on 02.06.2017.
 */

@RestController
@CrossOrigin
@RequestMapping("seller")
public class SellerController1 {

    private final SellerController sellerController;
    private final IUtility utility;

    @Autowired
    public SellerController1(SellerController sellerController, IUtility utility) {
        this.sellerController = sellerController;
        this.utility = utility;
    }
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Seller>> getAllMasters() {
        return new ResponseEntity<>(sellerController.findAll(), HttpStatus.OK);
    }

}
