package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.service.PerawatService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/perawat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerawatController {

    @Inject
    PerawatService perawatService;

    @POST
    @PermitAll
    public Response add(JsonObject jsonObject) {
        return perawatService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response get(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("nama") String nama,
            @QueryParam("email") String email,
            @QueryParam("phoneNumber") String phoneNumber,
            @QueryParam("gender") String gender
    ) {
        return perawatService.get(maxResult, page, nama, gender, email, phoneNumber);
    }

    @PUT
    @Path("/{id}/update")
    public Response updateGaji(@PathParam("id") Long id, JsonObject jsonObject) {
        return perawatService.updateGaji(id, jsonObject);
    }
}
