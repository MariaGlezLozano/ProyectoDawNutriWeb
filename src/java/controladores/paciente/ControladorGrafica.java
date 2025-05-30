/*
 * Controlador grafica evolucion peso paciente
 */
package controladores.paciente;

import entidades.Consulta;
import entidades.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorGrafica", urlPatterns = {"/paciente/ControladorGrafica"})
public class ControladorGrafica extends HttpServlet {

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
        EntityManager em = emf.createEntityManager();
        HttpSession session = request.getSession();

        Paciente paciente = (Paciente) session.getAttribute("paciente");

        if (paciente == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        // Obtener historial de peso ordenado por fecha
        List<Consulta> historialPeso = em.createQuery(
            "SELECT c FROM Consulta c WHERE c.paciente = :paciente ORDER BY c.fechaConsulta ASC", Consulta.class)
            .setParameter("paciente", paciente)
            .getResultList();

        request.setAttribute("historialPeso", historialPeso);
       // session.setAttribute("historialPeso", historialPeso);

        em.close();

        // Enviar los datos al JSP
      //  RequestDispatcher dispatcher = request.getRequestDispatcher("/pacientes/EvolucionPeso.jsp");
        //dispatcher.forward(request, response);
        request.getRequestDispatcher("/pacientes/EvolucionPeso.jsp").forward(request, response);
    
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
