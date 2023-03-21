package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.SanPhamRepository;
import view_model.QLChucVu;
import view_model.QLSanPham;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
    "/san-pham/index",    // GET
    "/san-pham/create",   // GET
    "/san-pham/edit",     // GET
    "/san-pham/delete",   // GET
    "/san-pham/store",    // POST
    "/san-pham/update",   // POST
})
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository spRepo;
    public SanPhamServlet()
    {
        this.spRepo = new SanPhamRepository();
        this.spRepo.insert(new QLSanPham("SP1", "Iphone 11"));
        this.spRepo.insert(new QLSanPham("SP2", "SamSung S20"));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/san_pham/create.jsp")
            .forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachSP", this.spRepo.findAll());
        request.getRequestDispatcher("/views/san_pham/index.jsp")
                .forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLSanPham vm = this.spRepo.findByMa(ma);
        request.setAttribute("sp", vm);
        request.getRequestDispatcher("/views/san_pham/edit.jsp")
                .forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLSanPham vm = this.spRepo.findByMa(ma);
        this.spRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        QLSanPham qlcv = new QLSanPham(ma, ten);

        // Tạo ArrayList & thêm vào
        spRepo.update(qlcv);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLSanPham vm = new QLSanPham();
            BeanUtils.populate(vm, request.getParameterMap());
            this.spRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
    }
}
