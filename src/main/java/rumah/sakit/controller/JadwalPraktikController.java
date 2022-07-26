package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.JadwalPraktik;
import rumah.sakit.service.JadwalPraktikService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/jadwal-praktik")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JadwalPraktikController {

    @Inject
    JadwalPraktikService jadwalPraktikService;

    @POST
    @PermitAll
    public Response add(JsonObject jsonObject) {
        return jadwalPraktikService.add(jsonObject);
    }

    @PUT
    @Path("/{id}/update")
    public Response update(@PathParam("id") Long id, JsonObject jsonObject){
        return jadwalPraktikService.update(id, jsonObject);
    }

    @GET
    public Response get(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("hari") String hari
    ){
        return jadwalPraktikService.get(maxResult, page, hari);
    }
}
