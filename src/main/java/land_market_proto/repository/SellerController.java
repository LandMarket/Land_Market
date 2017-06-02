package land_market_proto.repository;

import land_market_proto.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public interface SellerController extends MongoRepository<Seller, String> {
    Seller findByLogin(final String login);
}
