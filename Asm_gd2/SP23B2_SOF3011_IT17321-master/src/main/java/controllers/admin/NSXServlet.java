package controllers.admin;

import DomainModels.NSX;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.NSXRepository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

    public NSXServlet()
    {
        nsxRepo = new NSXRepository();
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
        NSX domainModelNSX = this.nsxRepo.findByMa(ma);
        this.nsxRepo.delete(domainModelNSX);

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachNSX", this.nsxRepo.findAll());
//        request.getRequestDispatcher("/views/khach_hang/index.jsp")
        request.setAttribute("view", "/views/nsx/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/nsx/create.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        NSX domainModelNSX = this.nsxRepo.findByMa(ma);
        request.setAttribute("nsx", domainModelNSX);
        request.getRequestDispatcher("/views/nsx/edit.jsp")
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
            // 404
            // 405
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            NSX domainModelNSX = new NSX();
            BeanUtils.populate(domainModelNSX, request.getParameterMap());
            System.out.println(domainModelNSX.getMa());
            this.nsxRepo.insert(domainModelNSX);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
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
            NSX domainModelNSX = this.nsxRepo.findByMa(ma);
            BeanUtils.populate(domainModelNSX, request.getParameterMap());
            this.nsxRepo.update(domainModelNSX);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nsx/index");
    }
}
