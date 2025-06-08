/*
 * Controlador HistorialConsultas
 */
package controladores.dietista;

import entidades.Consulta;
import entidades.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorHistorialConsultas", urlPatterns = {"/dietista/ControladorHistorialConsultas"})
public class ControladorHistorialConsultas extends HttpServlet {

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
        Long idPaciente = Long.parseLong(request.getParameter("idPaciente"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        Paciente paciente = em.find(Paciente.class, idPaciente);

        List<Consulta> consultas = em.createQuery(
            "SELECT c FROM Consulta c WHERE c.paciente.idPaciente = :idPaciente ORDER BY c.fechaConsulta DESC",
            Consulta.class)
            .setParameter("idPaciente", idPaciente)
            .getResultList();

        request.setAttribute("paciente", paciente);
        request.setAttribute("consultas", consultas);

        em.close();
        emf.close();

        request.getRequestDispatcher("../dietistas/HistorialConsultas.jsp").forward(request, response);
    
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
