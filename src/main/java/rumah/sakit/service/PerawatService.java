package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Dokter;
import rumah.sakit.model.Perawat;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class PerawatService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject jsonObject) {
        String namaLengkap = jsonObject.getString("namaLengkap");
        String gender = jsonObject.getString("gender");
        String email = jsonObject.getString("email");
        String phoneNumber = jsonObject.getString("phoneNumber");
        Long gaji = jsonObject.getLong("gaji");
        String status = jsonObject.getString("status");

        if (namaLengkap == null || gender == null || email == null || phoneNumber == null || gaji == null || status == null ){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Perawat perawat = new Perawat();
        perawat.setNamaLengkap(namaLengkap);
        perawat.setGender(gender);
        perawat.setEmail(email);
        perawat.setPhoneNumber(phoneNumber);
        perawat.setGaji(gaji);
        perawat.setStatus(status);
        perawat.persist();

        return Response.ok().build();
    }

    public Response get(Integer maxResult, Integer page, String namaLengkap, String gender, String email, String phoneNumber){
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.perawat p ");
        sb.append(" WHERE TRUE ");

        if(gender != null){
            sb.append(" AND p.gender = :gender ");
        }

        if (namaLengkap != null){
            sb.append(" AND p.nama_lengkap = :nama_lengkap ");
        }

        if (email != null){
            sb.append(" AND p.email = :email ");
        }

        if (phoneNumber != null){
            sb.append(" AND p.phone_number = :phone_number ");
        }

        sb.append(" ORDER BY p.nama_lengkap ASC ");

        Query query = em.createNativeQuery(sb.toString(), Perawat.class);

        if(gender != null){
            query.setParameter("gender", gender);
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

        Integer total = query.getResultList().size();

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

        Integer totalPage = (int) Math.ceil((double) total / (double) query.getMaxResults());

        List<Perawat> perawat = query.getResultList();

        return ResponseUtil.ok(perawat, perawat.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response updateGaji(Long id, JsonObject jsonObject) {
        Long gaji = jsonObject.getLong("gaji");
        Perawat perawat = Perawat.findById(id);

        if (perawat == null || gaji == null || gaji == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        perawat.setGaji(gaji);
        perawat.persist();

        return Response.ok().build();
    }
}
