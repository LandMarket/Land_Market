package land_market_proto.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import land_market_proto.model.Land;
import land_market_proto.model.Seller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Nik_NB on 02.06.2017.
 */
@Service
public class Utility implements IUtility {

    @Override
    public String buildJwts(String login) {
        return Jwts.builder().
                setSubject(login).
                claim("roles", "user")
                .setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS256, "hfjgjkbghj34986u834932009fj").compact();
    }

    @Override
    public String parseJwts(String token) {
        String login = Jwts.parser()
                .setSigningKey("hfjgjkbghj34986u834932009fj")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);
        return login;
    }

    @Override
    public String hashPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean isLoginExist(Object obj) {
        if(obj instanceof Seller) {
            Seller seller = (Seller) obj;
            if(seller.getLogin().equals("") || seller.getLogin() == null || seller.getPassword().equals("") || seller.getPassword() == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isPasswordCorrect(String newPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(newPassword, hashedPassword);
    }

    @Override
    public Boolean isLandNameExist(Object obj) {
        if(obj instanceof Land) {
            Land land = (Land) obj;
            if(land.getOwner().equals("") || land.getOwner() == null) {
                return true;
            }
        }
        return false;
    }
}
