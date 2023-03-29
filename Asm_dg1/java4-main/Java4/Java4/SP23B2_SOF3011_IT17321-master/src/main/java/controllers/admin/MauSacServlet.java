package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.MauSacRepository;
import view_model.NSX;
import view_model.QLChucVu;
import view_model.QLMauSac;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@WebServlet({
    "/mau-sac/index",    // GET
    "/mau-sac/create",   // GET
    "/mau-sac/edit",     // GET
    "/mau-sac/delete",   // GET
    "/mau-sac/store",    // POST
    "/mau-sac/update",   // POST
})
public class MauSacServlet extends HttpServlet {
    private MauSacRepository msRepo;

    public MauSacServlet() {
        this.msRepo = new MauSacRepository();
        this.msRepo.insert(new QLMauSac("MS1", "Red"));
        this.msRepo.insert(new QLMauSac("MS2", "Blue"));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("view", "/views/mau_sac/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachMS", this.msRepo.findAll());
        request.setAttribute("view", "/views/mau_sac/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLMauSac vm = this.msRepo.findByMa(ma);
        request.setAttribute("ms", vm);
        request.setAttribute("view", "/views/mau_sac/edit.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLMauSac vm = this.msRepo.findByMa(ma);
        this.msRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        QLMauSac qlms = new QLMauSac(ma, ten);

        // Tạo ArrayList & thêm vào
        msRepo.update(qlms);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLMauSac vm = new QLMauSac();
            BeanUtils.populate(vm, request.getParameterMap());
            this.msRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
    }
}
