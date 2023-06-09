package DomainModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="ChucVu")
public class ChucVu {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="Ma")
    private String ma;

    @Column(name="Ten")
    private String ten;
    // Lazy (OneToMany, ManyToMany)
    // Eager (ManyToOne, OneToOne)
    @OneToMany(mappedBy = "chucVu", fetch = FetchType.LAZY)
    private List<NhanVien> listNhanVien;


}
