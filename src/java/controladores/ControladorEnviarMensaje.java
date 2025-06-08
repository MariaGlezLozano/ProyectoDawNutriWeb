/*
 * ControladorEnviarMensaje
 */
package controladores;

import entidades.Dietista;
import entidades.Mensaje;
import entidades.Paciente;
import java.io.IOException;
import java.sql.Timestamp;
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
import servicios.ServicioDietista;
import servicios.ServicioPaciente;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorEnviarMensaje", urlPatterns = {"/ControladorEnviarMensaje"})
public class ControladorEnviarMensaje extends HttpServlet {

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
        //ServicioPaciente servicioPaciente = new ServicioPaciente(emf);

        String idPacienteStrg = request.getParameter("idPaciente");
        Long idPaciente = Long.valueOf(idPacienteStrg);

        Paciente paciente = em.find(Paciente.class, idPaciente);
        Dietista dietista = paciente.getDietista();
        em.close();
        if (dietista == null) {
            response.sendRedirect(request.getContextPath() + "/paciente/ControladorMensajesPaciente?idPaciente=" + idPacienteStrg + "&error=SinDietista");
            return;
        }

        request.setAttribute("dietistaId", dietista.getidDietista());
        request.setAttribute("nombreDietista", dietista.getNombre());

        request.getRequestDispatcher("/pacientes/NuevoMensaje.jsp").forward(request, response);

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        try {

            String idPacienteStr = request.getParameter("idPaciente");
            String receiverIdStr = request.getParameter("receiverId");
            String subject = request.getParameter("subject");
            String body = request.getParameter("body");
            // String replyToStr = request.getParameter("replyToId");

            if (receiverIdStr == null || subject == null || body == null || receiverIdStr.isEmpty() || subject.isEmpty() || body.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/paciente/ControladorMensajesPaciente?idPaciente=" + idPacienteStr + "&error=FaltanParametros");
                return;
            }

            Long idPaciente = Long.valueOf(idPacienteStr);
            Integer receiverId = Integer.valueOf(receiverIdStr);

            Paciente paciente = em.find(Paciente.class, idPaciente);
            if (paciente == null) {
                response.sendRedirect(request.getContextPath() + "/paciente/ControladorMensajesPaciente?idPaciente=" + idPacienteStr + "&error=PacienteNoEncontrado");
                return;
            }

            // Iniciar transacción y guardar mensaje
            em.getTransaction().begin();

            Mensaje m = new Mensaje();
            m.setSenderId(idPaciente.intValue());
            m.setReceiverId(receiverId);
            m.setSubject(subject);
            m.setBody(body);
            //m.setReplyToId(replyToId);
            m.setSentAt(new Timestamp(System.currentTimeMillis()));

            em.persist(m);
            em.getTransaction().commit();

            // Redirigir con confirmación
            response.sendRedirect(request.getContextPath() + "/paciente/ControladorMensajesPaciente?idPaciente=" + idPaciente);

            //response.sendRedirect(request.getContextPath() + "/pacientes/BandejaMensajes.jsp?enviado=1");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("Error al enviar el mensaje", e);
        } finally {
            em.close();
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
