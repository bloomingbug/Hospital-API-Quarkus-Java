package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Pasien;
import rumah.sakit.service.PasienService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/pasien")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasienController {

    @Inject
    PasienService pasienService;

    @POST
    @PermitAll
    public Response add (JsonObject jsonObject) {
        return pasienService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response getAll(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("namaLengkap") String namaLengkap,
            @QueryParam("email") String email,
            @QueryParam("phoneNumber") String phoneNumber
    ){
        return pasienService.getAll(maxResult, page, namaLengkap, email, phoneNumber);
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response get(@PathParam("id") Long id){
        return pasienService.get(id);
    }

    @PUT
    @Path("/{id}/update")
    @PermitAll
    public Response update(@PathParam("id") Long id, JsonObject jsonObject){
        return pasienService.update(id, jsonObject);
    }

    @DELETE
    @Path("/{id}/delete")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return pasienService.delete(id);
    }
}
