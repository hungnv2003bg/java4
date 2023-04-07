package DomainModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="ChiTietSP")

public class ChiTietSanPham{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(name = "Id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "IdNsx")
    private NSX nsx;
    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name = "IdDongSP")
    private DongSP dongSp;

    @Column(name = "NamSX")
    private int namSX;
    @Column(name = "MoTa")
    private String moTa;
    @Column(name = "SoLuongTon")
    private int soLuongTon;
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;
    @Column(name = "GiaBan")
    private BigDecimal giaBan;



}
