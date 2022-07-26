package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ruang_inap")
public class RuangInap extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ruangInapSeq", sequenceName = "ruang_inap_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "ruangInapSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "prefix_ruangan", nullable = false, columnDefinition = "varchar(50)")
    private String prefixRuangan;

    @Column(name = "nomor_ruangan", nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String nomorRuangan;

    @Enumerated(EnumType.STRING)
    @Column(name = "kategori_ruangan", nullable = false)
    private RuangInapKategoriEnum kategoriRuangan;

    @Column(name = "is_kosong")
    private Boolean isKosong;

    public RuangInap() {
    }

    public RuangInap(String prefixRuangan, String nomorRuangan, RuangInapKategoriEnum kategoriRuangan, Boolean isKosong) {
        this.prefixRuangan = prefixRuangan;
        this.nomorRuangan = nomorRuangan;
        this.kategoriRuangan = kategoriRuangan;
        this.isKosong = isKosong;
    }

    public Long getId() {
        return id;
    }

    public String getPrefixRuangan() {
        return prefixRuangan;
    }

    public void setPrefixRuangan(String prefixRuangan) {
        this.prefixRuangan = prefixRuangan;
    }

    public String getNomorRuangan() {
        return nomorRuangan;
    }

    public void setNomorRuangan(String nomorRuangan) {
        this.nomorRuangan = nomorRuangan;
    }

    public RuangInapKategoriEnum getKategoriRuangan() {
        return kategoriRuangan;
    }

    public void setKategoriRuangan(RuangInapKategoriEnum kategoriRuangan) {
        this.kategoriRuangan = kategoriRuangan;
    }

    public Boolean getKosong() {
        return isKosong;
    }

    public void setKosong(Boolean kosong) {
        isKosong = kosong;
    }
}
