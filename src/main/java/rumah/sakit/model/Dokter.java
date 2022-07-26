package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dokter")
public class Dokter extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "dokterSeq", sequenceName = "dokter_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "dokterSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nama_lengkap", nullable = false, columnDefinition = "varchar(255)")
    private String namaLengkap;

    @Column(name = "is_spesialis", nullable = false)
    private Boolean isSpesialis;

    @Column(name = "spesialis_nama", columnDefinition = "varchar(50)")
    private String spesialisNama;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(50)")
    private String phoneNumber;

    @Column(name = "status", nullable = false, columnDefinition = "varchar(50)")
    private String status;

    @Column(name = "gaji", nullable = false)
    private Long gaji;

    public Dokter() {
    }

    public Dokter(String namaLengkap, Boolean isSpesialis, String spesialisNama, String email, String phoneNumber, String status, Long gaji) {
        this.namaLengkap = namaLengkap;
        this.isSpesialis = isSpesialis;
        this.spesialisNama = spesialisNama;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.gaji = gaji;
    }

    public Long getId() {
        return id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Boolean getSpesialis() {
        return isSpesialis;
    }

    public void setSpesialis(Boolean spesialis) {
        isSpesialis = spesialis;
    }

    public String getSpesialisNama() {
        return spesialisNama;
    }

    public void setSpesialisNama(String spesialisNama) {
        this.spesialisNama = spesialisNama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGaji() {
        return gaji;
    }

    public void setGaji(Long gaji) {
        this.gaji = gaji;
    }
}
