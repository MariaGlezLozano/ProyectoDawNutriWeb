/*
 * Controlador Inicio de sesion
 */
package controladores;

import entidades.Dietista;
import entidades.Paciente;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servicios.ServicioDietista;
import servicios.ServicioPaciente;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorInicioSesion", urlPatterns = {"/ControladorInicioSesion"})
public class ControladorInicioSesion extends HttpServlet {

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
        String tipo = request.getParameter("tipo");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repetirPassword = request.getParameter("repetirPassword");
        String error = "";

        if (tipo.equals("paciente")) {
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                error = "El e-mail y la contraseña son obligatorios";
            } else {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
                ServicioPaciente sp = new ServicioPaciente(emf);
                Paciente paciente = sp.validarPaciente(email, password);
                emf.close();
                if (paciente != null) {
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("paciente", paciente);
                    response.sendRedirect("paciente/ControladorPrincipalPaciente");
                    return;
                } else {
                    error = "e-mail o contraseña incorrectos";
                }
            }
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/InicioSesion.jsp").forward(request, response);
        }
        
         if (tipo.equals("empresa")) {
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                error = "El e-mail y la contraseña son obligatorios";
            } else {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
                ServicioDietista sp = new ServicioDietista(emf);
                Dietista dietista = sp.validarDietista(email, password);
                emf.close();
                if (dietista != null) {
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("dietista", dietista);
                    response.sendRedirect("dietista/ControladorInicio");
                    return;
                } else {
                    error = "e-mail o contraseña incorrectos";
                }
            }
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/InicioSesion.jsp").forward(request, response);
        }        
        
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
