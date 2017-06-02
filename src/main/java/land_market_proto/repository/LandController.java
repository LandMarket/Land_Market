package land_market_proto.repository;

import land_market_proto.model.Land;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public interface LandController extends MongoRepository<Land, String> {
    Land findByOwner(final String owner);
}
