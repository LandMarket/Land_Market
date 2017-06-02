package land_market_proto.utility;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public interface IUtility {

        String buildJwts(String login);
        String parseJwts(String token);
        String hashPassword(String password);
        Boolean isLoginExist(Object obj);
        Boolean isPasswordCorrect(String newPassword, String hashedPassword);
        Boolean isLandNameExist(Object obj);

}
