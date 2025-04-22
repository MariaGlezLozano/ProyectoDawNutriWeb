/*
 * Filtro administrador
 */
package filtros;

import entidades.Admin;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maria
 */
@WebFilter(filterName = "FiltroAdmin", urlPatterns = {"/admin/*"})
public class FiltroAdmin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession sesion = req.getSession(false);

        Admin admin = null;

        if (sesion != null) {
            admin = (Admin) sesion.getAttribute("admin");
        }

        if (admin != null) {
        }

        if (admin == null || !"admin".equalsIgnoreCase(admin.getTipo())) {
            res.sendRedirect(req.getContextPath() + "/principal.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}
