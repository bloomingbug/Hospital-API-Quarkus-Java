package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "daftar_rawat_inap")
public class DaftarRawatInap extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "daftarRawatInapSeq", sequenceName = "daftar_rawat_inap_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "daftarRawatInapSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(targetEntity = Pasien.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pasien_id", nullable = false)
    private Pasien pasien;

    @ManyToOne(targetEntity = RuangInap.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ruang_inap_id", nullable = false)
    private RuangInap ruangInap;

    @ManyToOne(targetEntity = Dokter.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dokter_id", nullable = false)
    private Dokter dokter;

    @ManyToOne(targetEntity = Perawat.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "perawat_satu_id", nullable = false)
    private Perawat perawatSatu;

    @ManyToOne(targetEntity = Perawat.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "perawat_dua_id", nullable = false)
    private Perawat PerawatDua;

    @Column(name = "start_datetime")
    private Date startDateTime;

    @Column(name = "end_datetime")
    private Date endDateTime;

    @Column(name = "is_checkout", nullable = false)
    private Boolean isCheckout;

    public DaftarRawatInap() {
    }

    public DaftarRawatInap(Pasien pasien, RuangInap ruangInap, Dokter dokter, Perawat perawatSatu, Perawat perawatDua, Date startDateTime, Date endDateTime, Boolean isCheckout) {
        this.pasien = pasien;
        this.ruangInap = ruangInap;
        this.dokter = dokter;
        this.perawatSatu = perawatSatu;
        this.PerawatDua = perawatDua;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isCheckout = isCheckout;
    }

    public Long getId() {
        return id;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public RuangInap getRuangInap() {
        return ruangInap;
    }

    public void setRuangInap(RuangInap ruangInap) {
        this.ruangInap = ruangInap;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public Perawat getPerawatSatu() {
        return perawatSatu;
    }

    public void setPerawatSatu(Perawat perawatSatu) {
        this.perawatSatu = perawatSatu;
    }

    public Perawat getPerawatDua() {
        return PerawatDua;
    }

    public void setPerawatDua(Perawat perawatDua) {
        PerawatDua = perawatDua;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getCheckout() {
        return isCheckout;
    }

    public void setCheckout(Boolean checkout) {
        isCheckout = checkout;
    }
}
