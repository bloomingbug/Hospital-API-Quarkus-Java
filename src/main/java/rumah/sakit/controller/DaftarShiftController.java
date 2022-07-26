package rumah.sakit.controller;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import rumah.sakit.model.DaftarShift;
import rumah.sakit.service.DaftarShiftService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rumah-sakit/daftar-shift")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DaftarShiftController {

    @Inject
    DaftarShiftService daftarShiftService;

    @POST
    @PermitAll
    public Response add(JsonObject jsonObject) {
        return daftarShiftService.add(jsonObject);
    }

    @PUT
    @Path("/{id}/update")
    @PermitAll
    public Response update(@PathParam("id") Long id, JsonObject jsonObject){
        return daftarShiftService.update(id, jsonObject);
    }

    @GET
    @PermitAll
    public Response get(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("kategori") String kategori
    ){
        return daftarShiftService.get(maxResult, page, kategori);
    }
}
