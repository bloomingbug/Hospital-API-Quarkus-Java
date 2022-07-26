package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Obat;
import rumah.sakit.model.ObatKategoriEnum;
import rumah.sakit.model.RuangInap;
import rumah.sakit.model.RuangInapKategoriEnum;
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
public class RuangInapService {

    @Inject
    EntityManager em;

    public Response ruangInapKategori() {
        List<String> enumValues = Stream.of(RuangInapKategoriEnum.values()).map(e -> e.name()).collect(Collectors.toList());
        return ResponseUtil.ok(enumValues);
    }

    @Transactional
    public Response add(JsonObject jsonObject) {
        String prefix = jsonObject.getString("prefix");
        String nomor = jsonObject.getString("nomor");
        String kategori = jsonObject.getString("kategori");

        if (prefix == null || nomor == null || kategori == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        RuangInap ruangInap = new RuangInap();
        ruangInap.setPrefixRuangan(prefix);
        ruangInap.setNomorRuangan(nomor);

        switch (kategori.toLowerCase()){
            case "standard":
                ruangInap.setKategoriRuangan(RuangInapKategoriEnum.Standard);
                break;
            case "vip":
                ruangInap.setKategoriRuangan(RuangInapKategoriEnum.VIP);
                break;
            case "vvip":
                ruangInap.setKategoriRuangan(RuangInapKategoriEnum.VVIP);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }

        ruangInap.persist();
        return Response.ok().build();
    }

    public Response get (Long id) {
        RuangInap ruangInap = RuangInap.findById(id);

        if (ruangInap == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return ResponseUtil.ok(ruangInap);
    }

    public Response getAll(Integer maxResult, Integer page, String prefixRuangan, String nomorRuangan, String kategori, Boolean isKosong){
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.ruang_inap ri ");
        sb.append(" WHERE TRUE ");

        if(prefixRuangan != null){
            sb.append(" AND ri.prefix_ruangan = :prefix_ruangan ");
        }

        if(nomorRuangan != null){
            sb.append(" AND ri.nomor_ruangan = :nomor_ruangan ");
        }

        if(kategori != null){
            sb.append(" AND ri.kategori_ruangan = :kategori_ruangan ");
        }

        if (isKosong != null){
            sb.append(" AND ri.is_kosong = :is_kosong ");
        }

        sb.append(" ORDER BY ri.prefix_ruangan ASC ");

        Query query = em.createNativeQuery(sb.toString(), RuangInap.class);

        if(prefixRuangan != null){
            query.setParameter("prefix_ruangan", prefixRuangan);
        }

        if(nomorRuangan != null){
            query.setParameter("nomor_ruangan", nomorRuangan);
        }

        if(kategori != null){
            query.setParameter("kategori_ruangan", kategori);
        }

        if (isKosong != null){
            query.setParameter("is_kosong", isKosong);
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

        List<RuangInap> ruangInap = query.getResultList();

        return ResponseUtil.ok(ruangInap, ruangInap.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response update(Long id, JsonObject jsonObject) {
        RuangInap ruangInap = RuangInap.findById(id);
        String prefix = jsonObject.getString("prefix");
        String nomor = jsonObject.getString("nomor");
        String kategori = jsonObject.getString("kategori");

        if (ruangInap == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (ruangInap.getKosong() == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (prefix == null || nomor == null || kategori == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        ruangInap.setPrefixRuangan(prefix);
        ruangInap.setNomorRuangan(nomor);

        switch (kategori.toLowerCase()){
            case "standard":
                ruangInap.setKategoriRuangan(RuangInapKategoriEnum.Standard);
                break;
            case "vip":
                ruangInap.setKategoriRuangan(RuangInapKategoriEnum.VIP);
                break;
            case "vvip":
                ruangInap.setKategoriRuangan(RuangInapKategoriEnum.VVIP);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }

        ruangInap.persist();
        return Response.ok().build();
    }

    @Transactional
    public Response delete(Long id) {
        RuangInap ruangInap = RuangInap.findById(id);

        if (ruangInap == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (ruangInap.getKosong() == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        ruangInap.delete();

        return Response.ok().build();
    }
}
