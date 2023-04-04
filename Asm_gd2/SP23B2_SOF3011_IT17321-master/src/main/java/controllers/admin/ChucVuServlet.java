package controllers.admin;

import DomainModels.ChucVu;
import DomainModels.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.KhachHangRepository;
import services.ChucVuService;
import services.impl.ChucVuServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
    "/chuc-vu/index",    // GET
    "/chuc-vu/create",   // GET
    "/chuc-vu/edit",     // GET
    "/chuc-vu/delete",   // GET
    "/chuc-vu/store",    // POST
    "/chuc-vu/update",   // POST
})
public class ChucVuServlet extends HttpServlet {
    ChucVuService cvImpl;

    public ChucVuServlet()
    {
         cvImpl = new ChucVuServiceImpl();
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
        ChucVu domainModelCV = this.cvImpl.findByMa(ma);
        this.cvImpl.delete(domainModelCV);

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachCV", this.cvImpl.getAll());
//        request.getRequestDispatcher("/views/khach_hang/index.jsp")
        request.setAttribute("view", "/views/chuc_vu/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/chuc_vu/create.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        ChucVu domainModelCV = this.cvImpl.findByMa(ma);
        request.setAttribute("cv", domainModelCV);
        request.getRequestDispatcher("/views/chuc_vu/edit.jsp")
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
            // 404
            // 405
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            ChucVu domainModelCV = new ChucVu();
            BeanUtils.populate(domainModelCV, request.getParameterMap());
            System.out.println(domainModelCV.getMa());
            this.cvImpl.add(domainModelCV);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
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
            ChucVu domainModelCV = this.cvImpl.findByMa(ma);
            BeanUtils.populate(domainModelCV, request.getParameterMap());
            this.cvImpl.update(domainModelCV);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index");
    }
}
