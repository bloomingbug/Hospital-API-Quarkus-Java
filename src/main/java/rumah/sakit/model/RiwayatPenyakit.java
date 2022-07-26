package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "riwayat_penyakit")
public class RiwayatPenyakit extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "riwayatPenyakitSeq", sequenceName = "riwayat_penyakit_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "riwayatPenyakitSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(targetEntity = Pasien.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pasien_id", nullable = false)
    private Pasien pasien;

    @Column(name = "nama", nullable = false, columnDefinition = "varchar(255)")
    private String nama;

    @Column(name = "deskripsi", columnDefinition = "text")
    private String deskripsi;

    @Column(name = "awal_date", nullable = false)
    private LocalDate awalDate;

    @Column(name = "sembuh_date", nullable = false)
    private LocalDate sembuhDate;

    public RiwayatPenyakit() {
    }

    public RiwayatPenyakit(Pasien pasien, String nama, String deskripsi, LocalDate awalDate, LocalDate sembuhDate) {
        this.pasien = pasien;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.awalDate = awalDate;
        this.sembuhDate = sembuhDate;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDate getAwalDate() {
        return awalDate;
    }

    public void setAwalDate(LocalDate awalDate) {
        this.awalDate = awalDate;
    }

    public LocalDate getSembuhDate() {
        return sembuhDate;
    }

    public void setSembuhDate(LocalDate sembuhDate) {
        this.sembuhDate = sembuhDate;
    }
}
