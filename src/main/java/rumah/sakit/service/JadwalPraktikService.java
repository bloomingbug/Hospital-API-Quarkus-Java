package rumah.sakit.service;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import rumah.sakit.model.DaftarShift;
import rumah.sakit.model.Dokter;
import rumah.sakit.model.JadwalPraktik;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class JadwalPraktikService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject jsonObject) {
        Dokter dokter = Dokter.findById(jsonObject.getLong("dokterId"));
        String hari = jsonObject.getString("hari");
        String deskripsi = jsonObject.getString("deskripsi");
        String start = jsonObject.getString("startDateTime");
        String end = jsonObject.getString("endDateTime");
        SimpleDateFormat timeParser = new SimpleDateFormat("hh:mm:ss");

        if (dokter == null || deskripsi == null || start == null || end == null || hari == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        JadwalPraktik jadwalPraktik = new JadwalPraktik();
        jadwalPraktik.setDokter(dokter);
        jadwalPraktik.setHari(hari);
        jadwalPraktik.setDeskripsi(deskripsi);

        try {
            jadwalPraktik.setStartTime(timeParser.parse(start));
            jadwalPraktik.setEndTime(timeParser.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        jadwalPraktik.persist();

        return Response.ok().build();
    }

    @Transactional
    public Response update(Long id, JsonObject jsonObject) {
        JadwalPraktik jadwalPraktik = JadwalPraktik.findById(id);
        Dokter dokter = Dokter.findById(jsonObject.getLong("dokterId"));
        String hari = jsonObject.getString("hari");
        String deskripsi = jsonObject.getString("deskripsi");
        String start = jsonObject.getString("startDateTime");
        String end = jsonObject.getString("endDateTime");
        SimpleDateFormat timeParser = new SimpleDateFormat("hh:mm:ss");

        if (jadwalPraktik == null || dokter == null || deskripsi == null || start == null || end == null || hari == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        jadwalPraktik.setDokter(dokter);
        jadwalPraktik.setHari(hari);
        jadwalPraktik.setDeskripsi(deskripsi);

        try {
            jadwalPraktik.setStartTime(timeParser.parse(start));
            jadwalPraktik.setEndTime(timeParser.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        jadwalPraktik.persist();

        return Response.ok().build();
    }

    public Response get(Integer maxResult, Integer page, String hari) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.jadwal_praktik jp ");
        sb.append(" WHERE TRUE ");

        if (hari != null) {
            sb.append(" AND jp.hari = :hari ");
        }

        Query query = em.createNativeQuery(sb.toString(), JadwalPraktik.class);

        if (hari != null) {
            query.setParameter("kategori", hari);
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

        List<JadwalPraktik> jadwalPraktik = query.getResultList();

        return ResponseUtil.ok(jadwalPraktik, jadwalPraktik.size(), query.getMaxResults(), total, page, totalPage);
    }
}
