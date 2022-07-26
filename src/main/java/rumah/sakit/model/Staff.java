package rumah.sakit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "staff")
public class Staff extends AuditModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "staffSeq", sequenceName = "staff_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "staffSeq", strategy = GenerationType.SEQUENCE)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "posisi", nullable = false)
    private StaffKategoriEnum posisi;

    @Column(name = "start_time", columnDefinition = "timestamp")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "timestamp")
    private LocalTime endTime;

    public Staff() {
    }

    public Staff(String namaLengkap, String gender, Long gaji, String status, String email, String phoneNumber, StaffKategoriEnum posisi, LocalTime startTime, LocalTime endTime) {
        this.namaLengkap = namaLengkap;
        this.gender = gender;
        this.gaji = gaji;
        this.status = status;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.posisi = posisi;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public StaffKategoriEnum getPosisi() {
        return posisi;
    }

    public void setPosisi(StaffKategoriEnum posisi) {
        this.posisi = posisi;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
