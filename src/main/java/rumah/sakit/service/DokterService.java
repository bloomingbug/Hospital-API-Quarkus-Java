package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Dokter;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class DokterService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject jsonObject) {
        String namaLengkap = jsonObject.getString("namaLengkap");
        String email = jsonObject.getString("email");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String spesialis = jsonObject.getString("spesialis");
        Long gaji = jsonObject.getLong("gaji");
        String status = jsonObject.getString("status");

        if (namaLengkap == null || email == null || phoneNumber == null || gaji == null || status == null ){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Dokter dokter = new Dokter();
        dokter.setNamaLengkap(namaLengkap);
        dokter.setEmail(email);
        dokter.setPhoneNumber(phoneNumber);
        if (spesialis == null || spesialis.equals("-")) {
            dokter.setSpesialis(false);
        } else {
            dokter.setSpesialis(true);
            dokter.setSpesialisNama(spesialis);
        }
        dokter.setGaji(gaji);
        dokter.setStatus(status);
        dokter.persist();

        return Response.ok().build();
    }

    public Response get(Integer maxResult, Integer page, String spesialis, String namaLengkap, String email, String phoneNumber){
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.dokter d ");
        sb.append(" WHERE TRUE ");

        if(spesialis != null){
            sb.append(" AND d.spesialis_nama = :spesialis ");
        }

        if (namaLengkap != null){
            sb.append(" AND d.nama_lengkap = :nama_lengkap ");
        }

        if (email != null){
            sb.append(" AND d.email = :email ");
        }

        if (phoneNumber != null){
            sb.append(" AND d.phone_number = :phone_number ");
        }

        sb.append(" ORDER BY d.nama_lengkap ASC ");

        Query query = em.createNativeQuery(sb.toString(), Dokter.class);

        if(spesialis != null){
            query.setParameter("spesialis", spesialis);
        }

        if (namaLengkap != null){
            query.setParameter("nama_lengkap", namaLengkap);
        }

        if (email != null){
            query.setParameter("email", email);
        }

        if (phoneNumber != null){
            query.setParameter("phone_number", phoneNumber);
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

        List<Dokter> dokter = query.getResultList();

        return ResponseUtil.ok(dokter, dokter.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response updateGaji(Long id, JsonObject jsonObject) {
        Long gaji = jsonObject.getLong("gaji");
        Dokter dokter = Dokter.findById(id);

        if (dokter == null || gaji == null || gaji == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        dokter.setGaji(gaji);
        dokter.persist();

        return Response.ok().build();
    }
}
