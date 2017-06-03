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

import java.util.ArrayList;
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

//    @GetMapping("services")
//    public ResponseEntity<Object> getMasterServices(@RequestHeader("Authorization") String token) {
//        String owner = utility.parseJwts(token);
//
//
//        Land land = landController.findByOwner(owner);
//        if (owner == null) {
//            return new ResponseEntity<>("there is no such owner", HttpStatus.CONFLICT);
//        }
//        ArrayList<Services> services = master.getSerivce();
//        return new ResponseEntity<>(services, HttpStatus.OK);
//    }

//    @PostMapping("service")
//    public ResponseEntity<Object> setMasterServices(@RequestHeader("Authorization") String token, @RequestBody ArrayList<Services> services) {
//        String owner = utility.parseJwts(token);
//
//
//        Land land = landController.findByOwner(owner);
//        if (land == null) {
//            return new ResponseEntity<>("there is no such owner", HttpStatus.CONFLICT);
//        }
//      //  master.setSerivce(services);
//        landController.save(land);
//        return new ResponseEntity<>("Master controller were updated", HttpStatus.OK);
//
//    }

    @PutMapping("service")
    public ResponseEntity<Object> addland(@RequestHeader("Authorization") String token, @RequestBody Land land) {
        String owner = utility.parseJwts(token);

        Land land1 = landController.findByOwner(owner);
        if (land1 == null) {
            return new ResponseEntity<>("there is no such owner", HttpStatus.CONFLICT);
        }
        //master.addServise(service);
        landController.save(land);
        return new ResponseEntity<>("Land was added", HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Land>> getAllMasters() {
        return new ResponseEntity<>(landController.findAll(), HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Object> updateSeller(@RequestHeader("Authorization") String token, @RequestBody Land land) {
        String owner = utility.parseJwts(token);
        Land updateland = landController.findByOwner(owner);

        if (updateland == null) {
            return new ResponseEntity<>("owner doesn't exist", HttpStatus.CONFLICT);
        }
        if (land.getArea() != null) {
            updateland.setArea(land.getArea());
        }
        if (land.getAddress() != null) {
            updateland.setAddress(land.getAddress());
        }
        if (land.getAssignment() != null) {
            land.setAssignment(land.getAssignment());
        }
        if (land.getDescription() != null) {
            land.setDescription(land.getDescription());
        }
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
