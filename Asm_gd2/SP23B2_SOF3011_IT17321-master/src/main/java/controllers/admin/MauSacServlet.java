package controllers.admin;

import DomainModels.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.MauSacRepository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

    public MauSacServlet()
    {
        msRepo = new MauSacRepository();
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
        MauSac domainModelMS = this.msRepo.findByMa(ma);
        this.msRepo.delete(domainModelMS);

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachMS", this.msRepo.findAll());
//        request.getRequestDispatcher("/views/khach_hang/index.jsp")
        request.setAttribute("view", "/views/mau_sac/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/mau_sac/create.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        MauSac domainModelMS = this.msRepo.findByMa(ma);
        request.setAttribute("ms", domainModelMS);
        request.getRequestDispatcher("/views/mau_sac/edit.jsp")
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau_sac/index");
            // 404
            // 405
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            MauSac domainModelMS = new MauSac();
            BeanUtils.populate(domainModelMS, request.getParameterMap());
            System.out.println(domainModelMS.getMa());
            this.msRepo.insert(domainModelMS);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
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
            MauSac domainModelMS = this.msRepo.findByMa(ma);
            BeanUtils.populate(domainModelMS, request.getParameterMap());
            this.msRepo.update(domainModelMS);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index");
    }
}
