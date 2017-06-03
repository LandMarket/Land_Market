package land_market_proto.controller;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import land_market_proto.model.Land;
import land_market_proto.repository.LandController;
import land_market_proto.utility.IUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Виктор on 03.06.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("land")
public class LandController1 {

    private final LandController landController;
    private final IUtility utility;

    @Autowired
    public LandController1(LandController landController, IUtility utility) {
        this.landController = landController;
        this.utility = utility;
    }
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Land>> getAllMasters() {
        return new ResponseEntity<>(landController.findAll(), HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Object> updateSeller(@RequestHeader("Authorization") String token, @RequestBody Land land) {
        String owner = utility.parseJwts(token);
        Land updateland = landController.findByOwner(owner);

//        if (updateland == null) {
//            return new ResponseEntity<>("master doesn't exist", HttpStatus.CONFLICT);
//        }
//        if (master.getPhoneNumber() != null) {
//            updatedMaster.setPhoneNumber(master.getPhoneNumber());
//        }
//        if (master.getLastName() != null) {
//            updateland.setLastName(master.getLastName());
//        }
//        if (master.getName() != null) {
//            updateland.setName(master.getName());
//        }
//        if (master.getLang() != null) {
//            updateland.setLang(master.getLang());
//        }
//        if (master.getMasterType() != null) {
//            updatedMaster.setMasterType(master.getMasterType());
//        }
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCV43DMS9LJA9XaK10nY0I_sAGSxeDetlc");
        String adressStr = land.getAddress();
        GeocodingResult[] results = new GeocodingResult[0];
        if (adressStr != null) {
            try {
                results = GeocodingApi.geocode(context, adressStr).await();
                Geometry geometry = results[0].geometry;
                updateland.setPlaceId(results[0].placeId);
                updateland.setLatitude(geometry.location.lat);
                updateland.setLongitude(geometry.location.lng);
                updateland.setAddress(adressStr);
            } catch (Exception e) {
                updateland.setPlaceId(null);
                updateland.setLatitude(0);
                updateland.setLongitude(0);
            }
        }

        landController.save(updateland);

        return new ResponseEntity<>("Owner is updated", HttpStatus.OK);
    }
}
