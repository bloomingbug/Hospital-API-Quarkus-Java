package rumah.sakit.controller;

import io.vertx.core.json.JsonObject;
import rumah.sakit.service.RuangInapService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rumah-sakit/ruang-inap")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RuangInapController {

    @Inject
    RuangInapService ruangInapService;

    @GET
    @Path("/kategori")
    @PermitAll
    public Response kategori(){
        return ruangInapService.ruangInapKategori();
    }

    @POST
    @PermitAll
    public Response add (JsonObject jsonObject) {
        return  ruangInapService.add(jsonObject);
    }

    @GET
    @PermitAll
    public Response getAll(
            @HeaderParam("maxResult") Integer maxResult,
            @QueryParam("page") Integer page,
            @QueryParam("prefixRuangan") String prefixRuangan,
            @QueryParam("nomorRuangan") String nomorRuangan,
            @QueryParam("kategori") String kategori,
            @QueryParam("isKosong") Boolean isKosong
    ){
        return ruangInapService.getAll(maxResult, page, prefixRuangan, nomorRuangan, kategori, isKosong);
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response get(@PathParam("id") Long id) {
        return ruangInapService.get(id);
    }

    @PUT
    @Path("/{id}/update")
    @PermitAll
    public Response update(@PathParam("id") Long id, JsonObject jsonObject){
        return ruangInapService.update(id, jsonObject);
    }

    @DELETE
    @Path("/{id}/delete")
    @PermitAll
    public Response delete(@PathParam("id") Long id){
        return ruangInapService.delete(id);
    }
}
