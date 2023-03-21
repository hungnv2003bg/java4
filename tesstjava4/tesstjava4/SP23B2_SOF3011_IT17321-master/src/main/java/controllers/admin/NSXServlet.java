package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.NSXRepository;
import view_model.NSX;
import view_model.QLChucVu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@WebServlet({
    "/nsx/index",    // GET
    "/nsx/create",   // GET
    "/nsx/edit",     // GET
    "/nsx/delete",   // GET
    "/nsx/store",    // POST
    "/nsx/update",   // POST
})
public class NSXServlet extends HttpServlet {
    private NSXRepository nsxRepo;

    public NSXServlet() {
        this.nsxRepo = new NSXRepository();
        this.nsxRepo.insert(new NSX("NSX1", "Microsoft"));
        this.nsxRepo.insert(new NSX("NSX2", "ABC"));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/nsx/create.jsp")
                .forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachNSX", this.nsxRepo.findAll());
        request.getRequestDispatcher("/views/nsx/index.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        NSX vm = this.nsxRepo.findByMa(ma);
        request.setAttribute("nsx", vm);
        request.getRequestDispatcher("/views/nsx/edit.jsp")
                .forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        NSX vm = this.nsxRepo.findByMa(ma);
        this.nsxRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        NSX nsx = new NSX(ma, ten);

        // Tạo ArrayList & thêm vào
        nsxRepo.update(nsx);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            NSX vm = new NSX();
            BeanUtils.populate(vm, request.getParameterMap());
            this.nsxRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
    }
}
