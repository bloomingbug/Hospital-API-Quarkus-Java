package rumah.sakit.service;

import io.vertx.core.json.JsonObject;
import rumah.sakit.controller.DaftarRawatInapController;
import rumah.sakit.model.*;
import rumah.sakit.utility.ResponseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ApplicationScoped
public class DaftarRawatInapService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject jsonObject){
        Pasien pasien = Pasien.findById(jsonObject.getLong("pasienId"));
        RuangInap ruangInap = RuangInap.findById(jsonObject.getLong("ruangInapId"));
        Dokter dokter = Dokter.findById(jsonObject.getLong("dokterId"));
        Perawat perawatSatu = Perawat.findById(jsonObject.getLong("perawatSatu"));
        Perawat perawatDua = Perawat.findById(jsonObject.getLong("perawatDua"));
        String start = jsonObject.getString("startDateTime");
        SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");

        if (pasien == null || ruangInap == null || dokter == null || perawatSatu == null || perawatDua == null || start == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        DaftarRawatInap daftarRawatInap = new DaftarRawatInap();
        daftarRawatInap.setPasien(pasien);
        daftarRawatInap.setRuangInap(ruangInap);
        daftarRawatInap.setDokter(dokter);
        daftarRawatInap.setPerawatSatu(perawatSatu);
        daftarRawatInap.setPerawatDua(perawatDua);

        try {
            daftarRawatInap.setStartDateTime(dateParser.parse(start));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        daftarRawatInap.setCheckout(false);
        ruangInap.setKosong(false);

        ruangInap.persist();
        daftarRawatInap.persist();

        return Response.ok().build();
    }

    public Response get(Long id){
        DaftarRawatInap daftarRawatInap = DaftarRawatInap.findById(id);

        if (daftarRawatInap == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return ResponseUtil.ok(daftarRawatInap);
    }

    public Response getAll(Integer maxResult, Integer page) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM rumah_sakit.daftar_rawat_inap dri ");

        Query query = em.createNativeQuery(sb.toString(), DaftarRawatInap.class);

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

        List<DaftarRawatInapController> daftarRawatInapControllers = query.getResultList();

        return ResponseUtil.ok(daftarRawatInapControllers, daftarRawatInapControllers.size(), query.getMaxResults(), total, page, totalPage);
    }

    @Transactional
    public Response update(Long id, JsonObject jsonObject){
        DaftarRawatInap daftarRawatInap = DaftarRawatInap.findById(id);
        String end = jsonObject.getString("endDateTime");
        SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");

        if (daftarRawatInap == null || end == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            daftarRawatInap.setEndDateTime(dateParser.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        daftarRawatInap.setCheckout(true);
        daftarRawatInap.getRuangInap().setKosong(true);
        daftarRawatInap.persist();

        return Response.ok().build();
    }
}
