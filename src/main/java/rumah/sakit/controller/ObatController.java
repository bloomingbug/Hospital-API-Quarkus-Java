package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.service.ObatService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/obat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObatController {

    @Inject
    ObatService obatService;

    @GET
    @Path("/kategori")
    @PermitAll
    public Response kategori(){
        return obatService.obatKategori();
    }

    @POST
    @PermitAll
    public Response add(JsonObject jsonObject) {
        return obatService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response getAll(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("namaObat") String namaObat,
            @QueryParam("produksi") String produksi,
            @QueryParam("obatKategori") String obatKategori
    ){
        return obatService.getAll(maxResult, page, namaObat, produksi, obatKategori);
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response get(@PathParam("id") Long id) {
        return obatService.get(id);
    }

    @PUT
    @Path("/{id}/update")
    @PermitAll
    public Response update(@PathParam("id") Long id, JsonObject jsonObject){
        return obatService.update(id, jsonObject);
    }

    @DELETE
    @Path("{id}/delete")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return obatService.delete(id);
    }
}
