package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pasien")
public class Pasien extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "pasienSeq", sequenceName = "pasien_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "pasienSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nama_lengkap", nullable = false, columnDefinition = "varchar(255)")
    private String namaLengkap;

    @Column(name = "gender", nullable = false, columnDefinition = "varchar(50)")
    private String gender;

    @Column(name = "status", nullable = false, columnDefinition = "varchar(50)")
    private String status;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(50)")
    private String phoneNumber;

    @Column(name = "is_cover_bpjs", nullable = false)
    private Boolean isCoverBPJS;

    public Pasien() {
    }

    public Pasien(String namaLengkap, String gender, String status, String address, String email, String phoneNumber, Boolean isCoverBPJS) {
        this.namaLengkap = namaLengkap;
        this.gender = gender;
        this.status = status;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isCoverBPJS = isCoverBPJS;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Boolean getCoverBPJS() {
        return isCoverBPJS;
    }

    public void setCoverBPJS(Boolean coverBPJS) {
        isCoverBPJS = coverBPJS;
    }
}
