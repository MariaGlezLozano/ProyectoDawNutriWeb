/*
 * Controlador nueva cita
 */
package controladores;

import entidades.Cita;
import entidades.Dietista;
import entidades.Paciente;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javax.servlet.http.HttpSession;
import servicios.ServicioCita;
import servicios.ServicioDietista;
import servicios.ServicioPaciente;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorNuevaCIta", urlPatterns = {"/ControladorNuevaCita"})
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
        response.setContentType("text/html; charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();
        ServicioCita servicioCita = new ServicioCita(em);

        try {
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");
            String idDietista = request.getParameter("id_dietista");
            String idPaciente = request.getParameter("id_paciente");

            if (fecha == null || hora == null || idDietista == null || idPaciente == null) {
                request.setAttribute("mensaje", "Datos incompletos para la cita.");
                request.getRequestDispatcher("/pacientes/ListaCitasPaciente.jsp").forward(request, response);
                return;
            }

            LocalDateTime fechaHora = LocalDateTime.parse(fecha + "T" + hora);

            Long idDietistaLong = Long.parseLong(idDietista);
            Long idPacienteLong = Long.parseLong(idPaciente);

            em.getTransaction().begin();

            boolean disponibilidad = servicioCita.verificarDisponibilidad(fechaHora, idDietistaLong);
            boolean tieneCitaActiva = servicioCita.pacienteTieneCitaActiva(idPacienteLong);

            if (!disponibilidad) {
                request.setAttribute("mensaje", "La fecha y hora seleccionadas no están disponibles.");
            } else if (tieneCitaActiva) {
                request.setAttribute("mensaje", "El paciente ya tiene una cita activa.");
            } else {
                try {
                    servicioCita.asignarCita(fechaHora, idDietistaLong, idPacienteLong);
                    Dietista dietista = em.find(Dietista.class, idDietistaLong);
                    Paciente paciente = em.find(Paciente.class, idPacienteLong);
                    paciente.setDietista(dietista);
                    em.merge(paciente);
                    request.setAttribute("mensaje", "Cita asignada correctamente.");

                    em.getTransaction().commit();
                } catch (IllegalArgumentException e) {
                    request.setAttribute("mensaje", e.getMessage());
                }
            }

            Paciente paciente = em.find(Paciente.class, idPacienteLong);
            List<Cita> citasPaciente = servicioCita.obtenerCitasPaciente(idPacienteLong);

            // Convertimos fechaHora de cada cita a Strings formateadas
            DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

            for (Cita cita : citasPaciente) {
                LocalDateTime ldt = cita.getFechaHora();
                cita.setFechaFormateada(ldt.format(fechaFormatter));
                cita.setHoraFormateada(ldt.format(horaFormatter));
            }

            request.setAttribute("citasPaciente", citasPaciente);
            request.setAttribute("paciente", paciente);

            request.getRequestDispatcher("/pacientes/ListaCitasPaciente.jsp").forward(request, response);
            return;

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            List<Dietista> listaDietistas = em.createQuery("SELECT d FROM Dietista d", Dietista.class).getResultList();
            request.setAttribute("listaDietistas", listaDietistas);
            request.getRequestDispatcher("/pacientes/ListaCitasPaciente.jsp").forward(request, response);
            return;
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
