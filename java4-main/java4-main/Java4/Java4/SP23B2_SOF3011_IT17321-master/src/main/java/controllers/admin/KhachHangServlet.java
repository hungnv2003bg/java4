package controllers.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.KhachHangRepository;
import view_model.QLKhachHang;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@WebServlet({
    "/khach-hang/index",    // GET
    "/khach-hang/create",   // GET
    "/khach-hang/edit",     // GET
    "/khach-hang/delete",   // GET
    "/khach-hang/store",    // POST
    "/khach-hang/update",   // POST
})
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepository khRepo;
    public KhachHangServlet()
    {
        this.khRepo = new KhachHangRepository();
        this.khRepo.insert(new QLKhachHang("PH1", "Ng", "Van", "A", "HN", "0123123123", "1231231", "HN", "VN", "12-12-2022"));
        this.khRepo.insert(new QLKhachHang("PH2", "Tr", "Thi", "B", "HN", "0123123123", "1231231", "HN", "VN", "12-12-2022"));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("view", "/views/khach_hang/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLKhachHang kh = this.khRepo.findByMa(ma);
        request.setAttribute("kh", kh);
        request.setAttribute("view", "/views/khach_hang/edit.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLKhachHang vm = this.khRepo.findByMa(ma);
        this.khRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachKH", this.khRepo.findAll());
        request.setAttribute("view", "/views/khach_hang/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String ten_dem = request.getParameter("ten_dem");
        String ten = request.getParameter("ten");
        String ngay_sinh = request.getParameter("ngay_sinh");
        String sdt = request.getParameter("sdt");
        String dia_chi = request.getParameter("dia_chi");
        String mat_khau = request.getParameter("mat_khau");
        String quoc_gia = request.getParameter("quoc_gia");
        String thanh_pho = request.getParameter("thanh_pho");
        QLKhachHang vm = new QLKhachHang(ma, ho, ten_dem, ten, ngay_sinh, sdt, dia_chi, mat_khau, quoc_gia, thanh_pho);

        this.khRepo.update(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLKhachHang vm = new QLKhachHang();
            BeanUtils.populate(vm, request.getParameterMap());
            this.khRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/index");
    }
}
