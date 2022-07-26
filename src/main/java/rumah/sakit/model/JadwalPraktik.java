package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "jadwal_praktik")
public class JadwalPraktik extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "jadwalPraktikSeq", sequenceName = "jadwal_praktik_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "jadwalPraktikSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(targetEntity = Dokter.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dokter_id", nullable = false)
    private Dokter dokter;

    @Column(name = "hari", nullable = false, columnDefinition = "varchar(50)")
    private String hari;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "deskripsi", columnDefinition = "text")
    private String deskripsi;

    public JadwalPraktik() {
    }

    public JadwalPraktik(Dokter dokter, String hari, Date startTime, Date endTime, String deskripsi) {
        this.dokter = dokter;
        this.hari = hari;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deskripsi = deskripsi;
    }

    public Long getId() {
        return id;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
