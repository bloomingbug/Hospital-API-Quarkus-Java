package rumah.sakit.utility;

import io.smallrye.jwt.build.Jwt;
import rumah.sakit.model.User;

public class TokenUtil {
    public static String generate(User user){
        return Jwt.issuer("http://rsudkarawang/issuer")
                .expiresIn(600L)
                .upn(user.getUsername())
                .groups(user.getUserType())
                .claim("email", user.getEmail())
                .sign();
    }
}
