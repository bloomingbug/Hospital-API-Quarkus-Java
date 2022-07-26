package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Obat;
import rumah.sakit.model.ObatKategoriEnum;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ObatService {

    @Inject
    EntityManager em;

    public Response obatKategori() {

        List<String> enumValues = Stream.of(ObatKategoriEnum.values()).map(e -> e.name()).collect(Collectors.toList());

        return ResponseUtil.ok(enumValues);
    }

    @Transactional
    public Response add (JsonObject jsonObject) {
        String namaObat = jsonObject.getString("namaObat");
        String produksi = jsonObject.getString("produksi");
        String obatKategori = jsonObject.getString("obatKategori");
        String deskripsi = jsonObject.getString("deskripsi");

        if (namaObat == null || produksi == null || obatKategori == null || deskripsi == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Obat obat = new Obat();
        obat.setNamaObat(namaObat);

        switch (obatKategori.toLowerCase()){
            case "syrup":
                obat.setObatKategori(ObatKategoriEnum.Syrup);
                break;
            case "pil":
                obat.setObatKategori(ObatKategoriEnum.Pil);
                break;
            case "tablet":
                obat.setObatKategori(ObatKategoriEnum.Tablet);
                break;
            case "cair":
                obat.setObatKategori(ObatKategoriEnum.Cair);
                break;
            case "other":
                obat.setObatKategori(ObatKategoriEnum.Other);
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }

        obat.setProduksi(produksi);
        obat.setDeskripsi(deskripsi);

        obat.persist();

        return Response.ok().build();
    }

    public Response get(Long id){
        Obat obat = Obat.findById(id);

        if (obat == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return ResponseUtil.ok(obat);
    }

    public Response getAll(Integer maxResult, Integer page, String namaObat, String produksi, String obatKategori) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.obat o ");
        sb.append(" WHERE TRUE ");

        if(namaObat != null){
            sb.append(" AND o.nama_obat = :nama_obat ");
        }

        if(produksi != null){
            sb.append(" AND o.produksi = :produksi ");
        }

        if (obatKategori != null){
            sb.append(" AND o.obat_kategori = :obat_kategori ");
        }

        sb.append(" ORDER BY o.nama_obat ASC ");

        Query query = em.createNativeQuery(sb.toString(), Obat.class);

        if(namaObat != null){
            query.setParameter("nama_obat", namaObat);
        }

        if(produksi != null){
            query.setParameter("produksi", produksi);
        }

        if (obatKategori != null){
            query.setParameter("obat_kategori", obatKategori);
        }

        if (maxResult == null || maxResult == 0){
            query.setMaxResults(20);
        } else {
            query.setMaxResults(maxResult);
        }

        if (page == null || page == 1){
            query.setFirstResult(0);
            page = 1;
        } else {
            query.setFirstResult((page - 1) * query.getMaxResults());
        }

        Integer total = query.getResultList().size();

        Integer totalPage = (int) Math.ceil((double) total / (double) query.getMaxResults());

        List<Obat> obat = query.getResultList();

        return ResponseUtil.ok(obat, obat.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response update(Long id, JsonObject jsonObject) {
        Obat obat = Obat.findById(id);
        String namaObat = jsonObject.getString("namaObat");
        String produksi = jsonObject.getString("produksi");
        String obatKategori = jsonObject.getString("obatKategori");
        String deskripsi = jsonObject.getString("deskripsi");

        if (obat == null || namaObat == null || produksi == null || obatKategori == null || deskripsi == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        obat.setNamaObat(namaObat);
        obat.setProduksi(produksi);

        switch (obatKategori.toLowerCase()){
            case "syrup":
                obat.setObatKategori(ObatKategoriEnum.Syrup);
                break;
            case "pil":
                obat.setObatKategori(ObatKategoriEnum.Pil);
                break;
            case "tablet":
                obat.setObatKategori(ObatKategoriEnum.Tablet);
                break;
            case "cair":
                obat.setObatKategori(ObatKategoriEnum.Cair);
                break;
            case "other":
                obat.setObatKategori(ObatKategoriEnum.Other);
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }

        obat.setDeskripsi(deskripsi);

        obat.persist();

        return Response.ok().build();
    }

    @Transactional
    public Response delete(Long id){
        Obat obat = Obat.findById(id);

        if (obat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        obat.delete();

        return Response.ok().build();
    }
}
