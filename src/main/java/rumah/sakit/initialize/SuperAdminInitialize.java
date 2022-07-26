package rumah.sakit.initialize;

import io.quarkus.runtime.StartupEvent;
import rumah.sakit.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@ApplicationScoped
public class SuperAdminInitialize {
    private static final Logger LOGGER = Logger.getLogger("InitialSuperAdmin");

    @Transactional
    Response onStart(@Observes StartupEvent ev) {
        String password = "superadmin123";

        User user = new User();
        user.setName("Tarmuji");
        user.setUsername("tarmuji");
        user.setEmail("tarmuji1514@gmail.com");
        user.setPassword(Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)));
        user.setPhoneNumber("085156976647");

        Set<String> permissions = new HashSet<>();
        permissions.add("superadmin");
        user.setUserType(permissions);
        user.persist();

        return Response.ok().build();
    }
}
