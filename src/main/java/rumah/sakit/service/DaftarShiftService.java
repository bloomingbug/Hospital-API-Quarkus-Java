package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.model.DaftarShift;
import rumah.sakit.model.Dokter;
import rumah.sakit.model.Perawat;
import rumah.sakit.model.Staff;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class DaftarShiftService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject jsonObject) {
        String kategori = jsonObject.getString("kategori");
        Long foreignId = jsonObject.getLong("foreignId");
        String start = jsonObject.getString("startDateTime");
        String end = jsonObject.getString("endDateTime");
        Set<String> hari = new HashSet<>(jsonObject.getJsonArray("hari").getList());
        SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");

        if (kategori == null || foreignId == null || start == null || end == null || hari == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        DaftarShift daftarShift = new DaftarShift();
        daftarShift.setKategori(kategori);

//        if (kategori.equalsIgnoreCase("perawat")) {
//            Perawat perawat = Perawat.findById(foreignId);
//        } else if (kategori.equalsIgnoreCase("staff")) {
//            Staff staff = Staff.findById(foreignId);
//        }

        daftarShift.setForeignId(foreignId);
        try {
            daftarShift.setStartDateTime(dateParser.parse(start));
            daftarShift.setEndDateTime(dateParser.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        daftarShift.setHari(hari);
        daftarShift.persist();

        return Response.ok().build();
    }

    @Transactional
    public Response update(Long id, JsonObject jsonObject) {
        DaftarShift daftarShift = DaftarShift.findById(id);
        String start = jsonObject.getString("startDateTime");
        String end = jsonObject.getString("endDateTime");
        Set<String> hari = new HashSet<>(jsonObject.getJsonArray("hari").getList());
        SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");

        if (daftarShift == null || start == null || end == null || hari == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        daftarShift.setHari(hari);
        try {
            daftarShift.setStartDateTime(dateParser.parse(start));
            daftarShift.setEndDateTime(dateParser.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        daftarShift.persist();

        return Response.ok().build();
    }

    public Response get(Integer maxResult, Integer page, String kategori) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.daftar_shift d ");
        sb.append(" WHERE TRUE ");

        if (kategori != null) {
            sb.append(" AND d.kategori = :kategori ");
        }

        Query query = em.createNativeQuery(sb.toString(), DaftarShift.class);

        if (kategori != null) {
            query.setParameter("kategori", kategori);
        }

        Integer total = query.getResultList().size();

        if (maxResult == null || maxResult == 0) {
            query.setMaxResults(20);
        } else {
            query.setMaxResults(maxResult);
        }

        if (page == null || page == 1) {
            query.setFirstResult(0);
            page = 1;
        } else {
            query.setFirstResult((page - 1) * query.getMaxResults());
        }

        Integer totalPage = (int) Math.ceil((double) total / (double) query.getMaxResults());

        List<DaftarShift> daftarShift = query.getResultList();

        return ResponseUtil.ok(daftarShift, daftarShift.size(), query.getMaxResults(), total, page, totalPage);
    }
}
