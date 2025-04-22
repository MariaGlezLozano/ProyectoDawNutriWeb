/*
 * Controlador nueva cita
 */
package controladores.dietista;

import entidades.Cita;
import entidades.Dietista;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ServicioCita;
import servicios.ServicioDietista;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorNuevaCIta", urlPatterns = {"/dietista/ControladorNuevaCIta"})
public class ControladorNuevaCita extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        ServicioCita servicioCita = new ServicioCita(em);

        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");   
        String idDietista = request.getParameter("id_dietista");
        String idPaciente = request.getParameter("id_paciente");

        try {
            LocalDateTime fechaHora = LocalDateTime.parse(fecha + "T" + hora);

            boolean disponibilidad = servicioCita.verificarDisponibilidad(fechaHora, Long.parseLong(idDietista));

            if (!disponibilidad) {
                request.setAttribute("mensaje", "La fecha y hora seleccionadas no están disponibles.");
                request.getRequestDispatcher("/agenda.jsp").forward(request, response);
                return;
            }

            if (disponibilidad && !servicioCita.pacienteTieneCitaActiva(Long.parseLong(idPaciente))) {

                servicioCita.asignarCita(fechaHora, Long.parseLong(idDietista), Long.parseLong(idPaciente));

                request.setAttribute("mensaje", "Cita asignada correctamente.");
            } else {
                request.setAttribute("mensaje", "La fecha y hora seleccionadas no están disponibles o el paciente ya tiene una cita activa.");
            }

            List<Cita> citas = servicioCita.obtenerCitasPaciente(Long.parseLong(idPaciente));
            List<Cita> citasPaciente = servicioCita.obtenerCitasPaciente(Long.parseLong(idPaciente));
            request.setAttribute("citasPaciente", citasPaciente); 

            request.getRequestDispatcher("/agenda.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("/agenda.jsp").forward(request, response);
        } finally {
            em.close();  
            emf.close();
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
