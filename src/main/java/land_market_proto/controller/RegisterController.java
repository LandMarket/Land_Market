package land_market_proto.controller;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import land_market_proto.model.Land;
import land_market_proto.model.Seller;
import land_market_proto.repository.LandController;
import land_market_proto.repository.SellerController;
import land_market_proto.utility.IUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;


/**
 * Created by Nik_NB on 02.06.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {

    private final SellerController sellerController;
    private final IUtility utility;
    private final LandController landController;

    @Autowired
    public RegisterController(SellerController sellerController, IUtility utility, LandController landController) {
        this.sellerController = sellerController;
        this.utility = utility;
        this.landController = landController;
    }

    @PostMapping("/seller")
    public ResponseEntity<Object> registerSeller(@RequestBody Seller seller) throws ServletException {

        if (utility.isLoginExist(seller)) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.CONFLICT);
        }
           else if (sellerController.findByLogin(seller.getLogin()) != null) {
            return new ResponseEntity<>("This user already exists", HttpStatus.CONFLICT); // Found same login
        }

        String str = utility.hashPassword(seller.getPassword());
        seller.setPassword(str);

       // seller.setLogin(adressStr);
        sellerController.save(seller);


        return new ResponseEntity<>("{\"token\":" + "\"" + utility.buildJwts(seller.getLogin()) + "\"}", HttpStatus.OK);
    }
    @PostMapping("/land")
    public ResponseEntity<Object> registerLand(@RequestBody Land land) throws ServletException {

        if (utility.isLandNameExist(land)) {
            return new ResponseEntity<>("Please fill land name", HttpStatus.CONFLICT);
        }
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCV43DMS9LJA9XaK10nY0I_sAGSxeDetlc");
        String adressStr = land.getAddress();
        GeocodingResult[] results = new GeocodingResult[0];
        if (adressStr != null) {
            try {
                results = GeocodingApi.geocode(context, adressStr).await();
                Geometry geometry = results[0].geometry;
                land.setPlaceId(results[0].placeId);
                land.setLatitude(geometry.location.lat);
                land.setLongitude(geometry.location.lng);
            } catch (Exception e) {
                land.setPlaceId(null);
                land.setLatitude(0);
                land.setLongitude(0);
            }
        }

             landController.save(land);

        return new ResponseEntity<>("{\"token\":" + "\"" + utility.buildJwts(land.getOwner()) + "\"}", HttpStatus.OK);
    }

}
