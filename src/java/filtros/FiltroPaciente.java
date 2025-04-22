/**
 * Filtro paciente
 * Controla el acceso a todos los recursos de pacientes registrados
 */
package filtros;

import entidades.Paciente;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
@WebFilter(filterName = "FiltroPaciente", urlPatterns = {"/paciente/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class FiltroPaciente implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession sesion = req.getSession();
        Paciente paciente = (Paciente) sesion.getAttribute("paciente");
        if (paciente == null) {
            res.sendRedirect(req.getServletContext().getContextPath() + "/ControladorPrincipal");
            return;
        }
        chain.doFilter(request, response);

    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }
}
