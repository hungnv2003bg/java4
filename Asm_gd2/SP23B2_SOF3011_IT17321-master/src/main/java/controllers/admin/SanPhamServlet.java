package controllers.admin;

import DomainModels.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.SanPhamRepository;
import services.SanPhamService;
import services.impl.SanPhamServiceImpl;

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
    private SanPhamService spImpl;

    public SanPhamServlet()
    {
        spImpl = new SanPhamServiceImpl();
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

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham domainModelSP = this.spImpl.findByMa(ma);
        this.spImpl.delete(domainModelSP);

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachSP", this.spImpl.getAll());
//        request.getRequestDispatcher("/views/khach_hang/index.jsp")
        request.setAttribute("view", "/views/san_pham/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/san_pham/create.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham domainModelSP = this.spImpl.findByMa(ma);
        request.setAttribute("sp", domainModelSP);
        request.getRequestDispatcher("/views/san_pham/edit.jsp")
                .forward(request, response);
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
            // 404
            // 405
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            SanPham domainModelSP = new SanPham();
            BeanUtils.populate(domainModelSP, request.getParameterMap());
            System.out.println(domainModelSP.getMa());
            this.spImpl.add(domainModelSP);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            /*
             * domainModelKH: Dữ liệu đang có trong DB
             * request.getParameterMap(): Dữ liệu người dùng cập nhật
             */
            SanPham domainModelSP = this.spImpl.findByMa(ma);
            BeanUtils.populate(domainModelSP, request.getParameterMap());
            this.spImpl.update(domainModelSP);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index");
    }
}
