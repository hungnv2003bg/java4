package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.KhachHangRepository;
import repositories.NhanVienRepository;
import view_model.QLKhachHang;
import view_model.QLNhanVien;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
    "/nhan-vien/index",    // GET
    "/nhan-vien/create",   // GET
    "/nhan-vien/edit",     // GET
    "/nhan-vien/delete",   // GET
    "/nhan-vien/store",    // POST
    "/nhan-vien/update",   // POST
})
public class NhanVienServlet extends HttpServlet {
    private NhanVienRepository nvRepo;
    public NhanVienServlet()
    {
        this.nvRepo = new NhanVienRepository();
        this.nvRepo.insert(new QLNhanVien("NV1", "Nguyễn", "Van", "Hung", true, "2003-06-25", "0123456789", "BG", "123","VN"));
        this.nvRepo.insert(new QLNhanVien("NV2", "Tran", "Thi", "Sac", false, "1999-06-01", "0789645242", "HN", "321","VN"));
    }
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/nhan_vien/create.jsp")
            .forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLNhanVien nv = this.nvRepo.findByMa(ma);
        request.setAttribute("nv", nv);
        request.getRequestDispatcher("/views/nhan_vien/edit.jsp")
                .forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLNhanVien vm = this.nvRepo.findByMa(ma);
        this.nvRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachNV", this.nvRepo.findAll());
        request.getRequestDispatcher("/views/nhan_vien/index.jsp")
                .forward(request, response);
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String ten_dem = request.getParameter("ten_dem");
        String ten = request.getParameter("ten");
        String gioi_tinh = request.getParameter("gioi_tinh");
        String ngay_sinh = request.getParameter("ngay_sinh");
        String sdt = request.getParameter("sdt");
        String dia_chi = request.getParameter("dia_chi");
        String mat_khau = request.getParameter("mat_khau");
        String quoc_gia = request.getParameter("quoc_gia");
        QLNhanVien nv = new QLNhanVien(ma, ho, ten_dem, ten, Boolean.valueOf(gioi_tinh),ngay_sinh, sdt, dia_chi, mat_khau, quoc_gia);

        this.nvRepo.update(nv);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLNhanVien nv = new QLNhanVien();
            BeanUtils.populate(nv, request.getParameterMap());
            this.nvRepo.insert(nv);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
    }
}
