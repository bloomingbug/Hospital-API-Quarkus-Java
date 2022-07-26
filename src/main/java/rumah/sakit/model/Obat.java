package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "obat")
public class Obat extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "obatSeq", sequenceName = "obat_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "obatSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nama_obat", nullable = false, columnDefinition = "varchar(255)")
    private String namaObat;

    @Column(name = "produksi", nullable = false, columnDefinition = "varchar(255)")
    private String produksi;

    @Enumerated(EnumType.STRING)
    @Column(name = "obat_kategori", nullable = false)
    private ObatKategoriEnum obatKategori;

    @Column(name = "deskripsi", columnDefinition = "text")
    private String deskripsi;

    public Obat() {
    }

    public Obat(String namaObat, String produksi, ObatKategoriEnum obatKategori, String deskripsi) {
        this.namaObat = namaObat;
        this.produksi = produksi;
        this.obatKategori = obatKategori;
        this.deskripsi = deskripsi;
    }

    public Long getId() {
        return id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getProduksi() {
        return produksi;
    }

    public void setProduksi(String produksi) {
        this.produksi = produksi;
    }

    public ObatKategoriEnum getObatKategori() {
        return obatKategori;
    }

    public void setObatKategori(ObatKategoriEnum obatKategori) {
        this.obatKategori = obatKategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
