/*
 * Filtro dietista
 */
package filtros;

import entidades.Dietista;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
@WebFilter(filterName = "FiltroDietista", urlPatterns = {"/dietista/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class FiltroDietista implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession sesion = req.getSession();
        Dietista dietista = (Dietista) sesion.getAttribute("dietista");
        if (dietista == null) {
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
