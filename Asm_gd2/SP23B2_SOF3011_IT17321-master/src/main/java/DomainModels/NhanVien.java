package DomainModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="NhanVien")
public class NhanVien {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="Ma")
    private String ma;

    @Column(name="Ho")
    private String ho;

    @Column(name="TenDem")
    private String tenDem;

    @Column(name="Ten")
    private String ten;

    @Column(name="GioiTinh")
    private String gioiTinh;

    @Column(name="NgaySinh")
    private Date ngaySinh;

    @Column(name="DiaChi")
    private String diaChi;

    @Column(name="Sdt")
    private String sdt;

    @Column(name="MatKhau")
    private String matKhau;

    @Column(name="TrangThai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name="IdCH")
    private CuaHang cuaHang;

    @Column(name="IdGuiBC")
    private String idGuiBC;

    // Lazy (OneToMany, ManyToMany)
    // Eager (ManyToOne, OneToOne)
    @ManyToOne
    @JoinColumn(name="IdCV")
    private ChucVu chucVu;




}
