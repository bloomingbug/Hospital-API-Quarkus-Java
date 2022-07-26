package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.User;
import rumah.sakit.utility.ResponseUtil;
import rumah.sakit.utility.TokenUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class UserService {
    public Response login(JsonObject request){
        String username = request.getString("username");
        String password = request.getString("password");

        User user = User.find("username = ?1", username).singleResult();
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (!user.getPassword().equalsIgnoreCase(Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)))){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return ResponseUtil.ok(user, TokenUtil.generate(user));
    }

    @Transactional
    public Response register(JsonObject request) {
        String username = request.getString("username");
        String password = request.getString("password");
        String email = request.getString("email");
        String name = request.getString("name");
        String phoneNumber = request.getString("phoneNumber");

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)));
        user.setPhoneNumber(phoneNumber);

        Set<String> permissions = new HashSet<>();
        permissions.add("admin");
        user.setUserType(permissions);
        user.persist();

        return Response.ok().build();
    }
}
