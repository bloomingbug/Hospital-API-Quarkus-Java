package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "daftar_pertemuan")
public class DaftarPertemuan extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "daftarPertemuanSeq", sequenceName = "daftar_pertemuan_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "daftarPertemuanSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(targetEntity = Pasien.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pasien_id", nullable = false)
    private Pasien pasien;

    @OneToOne(targetEntity = Dokter.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dokter_id", nullable = false)
    private Dokter dokter;

    @Column(name = "kategori", nullable = false, columnDefinition = "varchar(255)")
    private String kategori;

    @Column(name = "deskripsi", columnDefinition = "text")
    private String deskripsi;

    @Column(name = "tanggal", nullable = false, columnDefinition = "timestamp")
    private LocalDate tanggal;

    public DaftarPertemuan() {
    }

    public DaftarPertemuan(Pasien pasien, Dokter dokter, String kategori, String deskripsi, LocalDate tanggal) {
        this.pasien = pasien;
        this.dokter = dokter;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
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

    public Dokter getDokter() {
        return dokter;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }
}
