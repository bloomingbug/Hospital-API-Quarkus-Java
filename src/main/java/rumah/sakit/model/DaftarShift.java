package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "daftar_shift")
public class DaftarShift extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "daftarShiftSeq", sequenceName = "daftar_shift_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "daftarShiftSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "foreign_id", nullable = false)
    private Long foreignId;

    @Column(name = "kategori", nullable = false, columnDefinition = "varchar(50)")
    private String kategori;

    @Column(name = "start_datetime", nullable = false, columnDefinition = "timestamp")
    private Date startDateTime;

    @Column(name = "end_datetime", nullable = false, columnDefinition = "timestamp")
    private Date endDateTime;

    @ElementCollection
    @CollectionTable(name = "daftar_shift_hari", joinColumns = @JoinColumn(name = "daftar_shift_id"))
    @Column(name = "hari", nullable = false)
    Set<String> hari = new HashSet<>();

    public DaftarShift() {
    }

    public DaftarShift(Long foreignId, String kategori, Date startDateTime, Date endDateTime, Set<String> hari) {
        this.foreignId = foreignId;
        this.kategori = kategori;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.hari = hari;
    }

    public Long getId() {
        return id;
    }

    public Long getForeignId() {
        return foreignId;
    }

    public void setForeignId(Long foreignId) {
        this.foreignId = foreignId;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public Set<String> getHari() {
        return hari;
    }

    public void setHari(Set<String> hari) {
        this.hari = hari;
    }
}
