package DomainModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DongSP")
public class DongSP {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="Ma")
    private String ma;

    @Column(name="Ten")
    private String ten;
    @OneToMany(mappedBy = "dongSp", fetch = FetchType.LAZY)
    private List<ChiTietSanPham> listChiTietSanPham;



}
