package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "perawat")
public class Perawat extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "perawatSeq", sequenceName = "perawat_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "perawatSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nama_lengkap", nullable = false, columnDefinition = "varchar(255)")
    private String namaLengkap;

    @Column(name = "gender", nullable = false, columnDefinition = "varchar(50)")
    private String gender;

    @Column(name = "gaji", nullable = false)
    private Long gaji;

    @Column(name = "status", nullable = false, columnDefinition = "varchar(50)")
    private String status;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(50)")
    private String phoneNumber;

    public Perawat() {
    }

    public Perawat(String namaLengkap, String gender, Long gaji, String status, String email, String phoneNumber) {
        this.namaLengkap = namaLengkap;
        this.gender = gender;
        this.gaji = gaji;
        this.status = status;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getGaji() {
        return gaji;
    }

    public void setGaji(Long gaji) {
        this.gaji = gaji;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
