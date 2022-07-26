package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Pasien;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class PasienService {
    @Inject
    EntityManager em;

    @Transactional
    public Response add (JsonObject jsonObject) {
        String namaLengkap = jsonObject.getString("namaLengkap");
        String gender = jsonObject.getString("gender");
        String status = jsonObject.getString("status");
        String address = jsonObject.getString("address");
        String email = jsonObject.getString("email");
        String phoneNumber = jsonObject.getString("phoneNumber");
        Boolean isCoverBPJS = jsonObject.getBoolean("isCoverBPJS");

        if(namaLengkap == null || gender == null || status == null || address == null || email == null || phoneNumber == null || isCoverBPJS == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Pasien pasien = new Pasien();
        pasien.setNamaLengkap(namaLengkap);
        pasien.setGender(gender);
        pasien.setStatus(status);
        pasien.setAddress(address);
        pasien.setEmail(email);
        pasien.setPhoneNumber(phoneNumber);
        pasien.setCoverBPJS(isCoverBPJS);
        pasien.persist();

        return Response.ok().build();
    }

    public Response get(Long id){
        Pasien pasien = Pasien.findById(id);

        if (pasien == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return ResponseUtil.ok(pasien);
    }

    public Response getAll(Integer maxResult, Integer page, String namaLengkap, String email, String phoneNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.pasien p ");
        sb.append(" WHERE TRUE ");

        if(namaLengkap != null){
            sb.append(" AND p.nama_lengkap = :nama_lengkap ");
        }

        if(email != null){
            sb.append(" AND p.email = :email ");
        }

        if (phoneNumber != null){
            sb.append(" AND p.phone_number = :phone_number ");
        }

        sb.append(" ORDER BY p.nama_lengkap ASC ");

        Query query = em.createNativeQuery(sb.toString(), Pasien.class);

        if(namaLengkap != null){
            query.setParameter("nama_obat", namaLengkap);
        }

        if(email != null){
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

        List<Pasien> pasien = query.getResultList();

        return ResponseUtil.ok(pasien, pasien.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response update(Long id, JsonObject jsonObject){
        Pasien pasien = Pasien.findById(id);

        if (pasien == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        String namaLengkap = jsonObject.getString("namaLengkap");
        String gender = jsonObject.getString("gender");
        String status = jsonObject.getString("status");
        String address = jsonObject.getString("address");
        String email = jsonObject.getString("email");
        String phoneNumber = jsonObject.getString("phoneNumber");
        Boolean isCoverBPJS = jsonObject.getBoolean("isCoverBPJS");

        if(namaLengkap == null || gender == null || status == null || address == null || email == null || phoneNumber == null || isCoverBPJS == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        pasien.setNamaLengkap(namaLengkap);
        pasien.setGender(gender);
        pasien.setStatus(status);
        pasien.setAddress(address);
        pasien.setEmail(email);
        pasien.setPhoneNumber(phoneNumber);
        pasien.setCoverBPJS(isCoverBPJS);
        pasien.persist();

        return Response.ok().build();
    }

    @Transactional
    public Response delete(Long id) {
        Pasien pasien = Pasien.findById(id);

        if (pasien == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        pasien.delete();

        return Response.ok().build();
    }
}
