package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import rumah.sakit.service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/rumah-sakit/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/login")
    public Response login (JsonObject jsonObject) {
        return userService.login(jsonObject);
    }

    @POST
    @Path("/register")
    @RolesAllowed({"superadmin"})
    public Response register (JsonObject jsonObject) {
        return userService.register(jsonObject);
    }

    @GET
    @Path("/profil")
    @RolesAllowed({"superadmin"})
    public Response profil(@Context SecurityContext securityContext) {
        Principal principal = securityContext.getUserPrincipal();
        JsonObject response = new JsonObject();
        response.put("name", principal.getName());
        response.put("permissions", jwt.getGroups());
        response.put("permissions", jwt.getIssuer());
        response.put("email", jwt.getClaim("email"));
        return Response.ok().entity(response).build();
    }
}
