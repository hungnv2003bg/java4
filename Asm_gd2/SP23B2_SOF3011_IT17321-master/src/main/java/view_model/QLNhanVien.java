package view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class QLNhanVien {
    private String ma;
    private String ten;
    private String ten_dem;
    private String ho;
    private String gioi_tinh;
    private String ngay_sinh;
    private String dia_chi;
    private String sdt;
    private String mat_khau;
    private Boolean trang_thai;
}
