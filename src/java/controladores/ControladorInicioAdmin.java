/*
 * Controlador Inicio de Admin
 */
package controladores;

import entidades.Admin;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servicios.ServicioAdmin;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorInicioAdmin", urlPatterns = {"/ControladorInicioAdmin"})
public class ControladorInicioAdmin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // getServletContext().getRequestDispatcher("principal.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repetirPassword = request.getParameter("repetirPassword");
        String error = "";
      
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                error = "El e-mail y la contraseña son obligatorios";
            }
            if(!password.equals(repetirPassword)){
                error = "Las contraseñas no coinciden";
            } else {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
                ServicioAdmin sp = new ServicioAdmin(emf);
                Admin admin = sp.validarAdmin(email, password);
                emf.close();
                if (admin != null) {
                    HttpSession sesion = request.getSession(true);
                    sesion.setAttribute("admin", admin);
                    response.sendRedirect("admin/ControladorPrincipalAdmin");
                    //response.sendRedirect(request.getContextPath() + "/admin/principalAdmin.jsp");
                    return;
                } else {
                    error = "e-mail o contraseña incorrectos";
                }
            }
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/AdministradorInicio.jsp").forward(request, response);
        }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
