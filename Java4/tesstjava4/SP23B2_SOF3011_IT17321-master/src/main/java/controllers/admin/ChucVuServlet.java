package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.KhachHangRepository;
import view_model.QLChucVu;
import view_model.QLKhachHang;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@WebServlet({
    "/chuc-vu/index",    // GET
    "/chuc-vu/create",   // GET
    "/chuc-vu/edit",     // GET
    "/chuc-vu/delete",   // GET
    "/chuc-vu/store",    // POST
    "/chuc-vu/update",   // POST
})
public class ChucVuServlet extends HttpServlet {
    private ChucVuRepository cvRepo;
    public ChucVuServlet()
    {
        this.cvRepo = new ChucVuRepository();
        this.cvRepo.insert(new QLChucVu("CV1", "Giám đốc"));
        this.cvRepo.insert(new QLChucVu("CV2", "Nhân viên"));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/chuc_vu/create.jsp")
            .forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachCV", this.cvRepo.findAll());
        request.getRequestDispatcher("/views/chuc_vu/index.jsp")
                .forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLChucVu vm = this.cvRepo.findByMa(ma);
        request.setAttribute("cv", vm);
        request.getRequestDispatcher("/views/chuc_vu/edit.jsp")
                .forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLChucVu vm = this.cvRepo.findByMa(ma);
        this.cvRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        QLChucVu qlcv = new QLChucVu(ma, ten);

        // Tạo ArrayList & thêm vào
        cvRepo.update(qlcv);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLChucVu vm = new QLChucVu();
            BeanUtils.populate(vm, request.getParameterMap());
            this.cvRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
    }
}
