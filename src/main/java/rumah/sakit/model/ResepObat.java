package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resep_obat")
public class ResepObat extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "resepObatSeq", sequenceName = "resep_obat_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "resepObatSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(targetEntity = DaftarPertemuan.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pertemuan_id", nullable = false)
    private DaftarPertemuan pertemuan;

    @ManyToOne(targetEntity = Obat.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "obat_id", nullable = false)
    private Obat obat;

    @Column(name = "dosis", nullable = false, columnDefinition = "varchar(50)")
    private String dosis;

    @Column(name = "deskripsi", nullable = false, columnDefinition = "text")
    private String deskripsi;

    public ResepObat() {
    }

    public ResepObat(DaftarPertemuan pertemuan, Obat obat, String dosis, String deskripsi) {
        this.pertemuan = pertemuan;
        this.obat = obat;
        this.dosis = dosis;
        this.deskripsi = deskripsi;
    }

    public Long getId() {
        return id;
    }

    public DaftarPertemuan getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(DaftarPertemuan pertemuan) {
        this.pertemuan = pertemuan;
    }

    public Obat getObat() {
        return obat;
    }

    public void setObat(Obat obat) {
        this.obat = obat;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
