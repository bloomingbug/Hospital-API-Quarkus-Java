package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.service.DokterService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/dokter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DokterController {

    @Inject
    DokterService dokterService;

    @POST
    @PermitAll
    public Response add(JsonObject jsonObject) {
        return dokterService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response get(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("spesialis") String spesialis,
            @QueryParam("nama") String nama,
            @QueryParam("email") String email,
            @QueryParam("phoneNumber") String phoneNumber) {
        return dokterService.get(maxResult, page, spesialis, nama, email, phoneNumber);
    }

    @PUT
    @Path("/{id}/update")
    public Response updateGaji(@PathParam("id") Long id, JsonObject jsonObject) {
        return dokterService.updateGaji(id, jsonObject);
    }
}
