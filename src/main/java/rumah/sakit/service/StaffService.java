package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.Staff;
import rumah.sakit.model.StaffKategoriEnum;
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
public class StaffService {
    @Inject
    EntityManager em;

    public Response staffKategori() {

        List<String> enumValues = Stream.of(StaffKategoriEnum.values()).map(e -> e.name()).collect(Collectors.toList());

        return ResponseUtil.ok(enumValues);
    }

    @Transactional
    public Response add(JsonObject jsonObject) {
        String namaLengkap = jsonObject.getString("namaLengkap");
        String gender = jsonObject.getString("gender");
        String email = jsonObject.getString("email");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String posisi = jsonObject.getString("posisi");
        String status = jsonObject.getString("status");
        Long gaji = jsonObject.getLong("gaji");

        if (namaLengkap == null || email == null || phoneNumber == null || gender == null || posisi == null ){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Staff staff = new Staff();
        staff.setNamaLengkap(namaLengkap);
        staff.setGender(gender);
        staff.setEmail(email);
        staff.setPhoneNumber(phoneNumber);
        staff.setStatus(status);

        switch (posisi.toLowerCase()){
            case "security":
                staff.setPosisi(StaffKategoriEnum.Security);
                break;
            case "janitor":
                staff.setPosisi(StaffKategoriEnum.Janitor);
                break;
            case "receipt":
                staff.setPosisi(StaffKategoriEnum.Receipt);
                break;
            case "engineer":
                staff.setPosisi(StaffKategoriEnum.Engineer);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }

        staff.setGaji(gaji);
//        staff.setStartTime(LocalTime.now());
        staff.persist();

        return Response.ok().build();
    }

    public Response get(Integer maxResult, Integer page, String namaLengkap, String email, String phoneNumber){
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.staff s ");
        sb.append(" WHERE TRUE ");

        if (namaLengkap != null){
            sb.append(" AND s.nama_lengkap = :nama_lengkap ");
        }

        if (email != null){
            sb.append(" AND s.email = :email ");
        }

        if (phoneNumber != null){
            sb.append(" AND s.phone_number = :phone_number ");
        }

        sb.append(" ORDER BY s.nama_lengkap ASC ");

        Query query = em.createNativeQuery(sb.toString(), Staff.class);

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

        List<Staff> staff = query.getResultList();

        return ResponseUtil.ok(staff, staff.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response delete(Long id){
        Staff staff = Staff.findById(id);

        if (staff == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        staff.delete();
        return Response.ok().build();
    }

    @Transactional
    public Response updateGaji(Long id, JsonObject jsonObject) {
        Long gaji = jsonObject.getLong("gaji");
        Staff staff = Staff.findById(id);

        if (staff == null || gaji == null || gaji == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        staff.setGaji(gaji);
        staff.persist();

        return Response.ok().build();
    }
}
