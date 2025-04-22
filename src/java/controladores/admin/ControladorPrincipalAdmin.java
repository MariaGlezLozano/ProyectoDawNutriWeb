/*
 * Controlador principal del administrador
 */
package controladores.admin;

import entidades.Dietista;
import entidades.Paciente;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ServicioDietista;
import servicios.ServicioPaciente;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorPrincipalAdmin", urlPatterns = {"/admin/ControladorPrincipalAdmin"})
public class ControladorPrincipalAdmin extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");

        ServicioDietista servicioDietista = new ServicioDietista(emf);
        ServicioPaciente servicioPaciente = new ServicioPaciente(emf);

        List<Dietista> listaDietistas = servicioDietista.findAll();
        List<Paciente> listaPacientes = servicioPaciente.findAll();

        emf.close();

        request.setAttribute("listaDietistas", listaDietistas);
        request.setAttribute("listaPacientes", listaPacientes);

        request.getRequestDispatcher("/admin/principalAdmin.jsp").forward(request, response);
    //getServletContext().getRequestDispatcher("/admin/principalAdmin.jsp").forward(request, response);
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
        processRequest(request, response);
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
