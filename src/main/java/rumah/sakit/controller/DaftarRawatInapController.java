package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.service.DaftarRawatInapService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/daftar-rawat-inap")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DaftarRawatInapController {

    @Inject
    DaftarRawatInapService daftarRawatInapService;

    @POST
    @PermitAll
    public Response add (JsonObject jsonObject) {
        return daftarRawatInapService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response getAll(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page
    ){
        return daftarRawatInapService.getAll(maxResult, page);
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response get(@PathParam("id") Long id){
        return daftarRawatInapService.get(id);
    }

    @PUT
    @Path("/{id}/checkout")
    @PermitAll
    public Response checkout(@PathParam("id") Long id, JsonObject jsonObject){
        return daftarRawatInapService.update(id, jsonObject);
    }
}
