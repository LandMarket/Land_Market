package land_market_proto.controller;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import land_market_proto.model.Land;
import land_market_proto.model.Seller;
import land_market_proto.repository.SellerController;
import land_market_proto.utility.IUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("update")
    public ResponseEntity<Object> updateSeller(@RequestHeader("Authorization") String token, @RequestBody Seller seller) {
        String login = utility.parseJwts(token);
        Seller updatedSeller = sellerController.findByLogin(login);

//        if (updatedSeller == null) {
//            return new ResponseEntity<>("seller doesn't exist", HttpStatus.CONFLICT);
//        }
        if (seller.getEmail() != null) {
            updatedSeller.setEmail(seller.getEmail());
        }
        if (seller.getPassword() != null) {
            updatedSeller.setPassword(seller.getPassword());
        }
        if (seller.getConfirm() != null) {
            updatedSeller.setConfirm(seller.getConfirm());
        }
          if(seller.getPhone()!=null){
         updatedSeller.setPhone(seller.getPhone());
        }
        if(seller.getPassport()!=null){
            updatedSeller.setPassport(seller.getPassport());
        }
        sellerController.save(seller);

        return new ResponseEntity<>("Seller is updated", HttpStatus.OK);
    }
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Seller>> getAllSellers() {
        return new ResponseEntity<>(sellerController.findAll(), HttpStatus.OK);
    }
    @PutMapping("delete")
    public ResponseEntity<Object> deletland( @RequestHeader("Authorization") String token, @RequestBody Seller seller) {
        String login = utility.parseJwts(token);
        Seller seller1 = sellerController.findByLogin(login);
        if (seller == null) {
            return new ResponseEntity<>("there is no such owner", HttpStatus.CONFLICT);
        }
        //a(landArray);
        sellerController.delete(seller);
        return new ResponseEntity<>("Seller was delete", HttpStatus.OK);
    }

}
