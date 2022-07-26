package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.service.StaffService;
import rumah.sakit.utility.ResponseUtil;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/staff")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffController {

    @Inject
    StaffService staffService;

    @GET
    @Path("/kategori")
    @PermitAll
    public Response kategoriEnum() {
        return staffService.staffKategori();
    }

    @POST
    @PermitAll
    public Response add(JsonObject jsonObject) {
        return staffService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response get(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("nama") String nama,
            @QueryParam("email") String email,
            @QueryParam("phoneNumber") String phoneNumber
    ){
        return staffService.get(maxResult, page, nama, email, phoneNumber);
    }

    @DELETE
    @PermitAll
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Long id) {
        return staffService.delete(id);
    }

    @PUT
    @PermitAll
    @Path("/{id}/update")
    public Response updateGaji(@PathParam("id") Long id, JsonObject jsonObject) {
        return staffService.updateGaji(id, jsonObject);
    }
}

