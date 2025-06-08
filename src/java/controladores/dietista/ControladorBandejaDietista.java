/*
 * Controlador bandejaEntrada dietista
 */
package controladores.dietista;

import entidades.Mensaje;
import entidades.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
@WebServlet(name = "ControladorBandejaDietista", urlPatterns = {"/dietista/ControladorBandejaDietista"})
public class ControladorBandejaDietista extends HttpServlet {

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

        try {
            HttpSession session = request.getSession(false);
            Long idDietista = (Long) session.getAttribute("idDietista");
            if (idDietista == null) {
                throw new ServletException("Sesión no válida. No hay dietista logueado.");
            }

        
            TypedQuery<Mensaje> query = em.createQuery(
                    "SELECT m FROM Mensaje m WHERE m.receiverId = :dietistaId ORDER BY m.sentAt DESC",
                    Mensaje.class);
            query.setParameter("dietistaId", idDietista.intValue());
            List<Mensaje> mensajes = query.getResultList();

     
            Map<Integer, String> remitenteNombres = new HashMap<>();
            for (Mensaje m : mensajes) {
                int senderId = m.getSenderId();
                if (!remitenteNombres.containsKey(senderId)) {

                    Paciente remitente = em.find(Paciente.class, Long.valueOf(senderId));
                    String nombre = (remitente != null) ? remitente.getNombre() : "Desconocido";
                    remitenteNombres.put(senderId, nombre);
                }
            }

 
            request.setAttribute("mensajes", mensajes);
            request.setAttribute("remitenteNombres", remitenteNombres);
            request.getRequestDispatcher("/dietistas/BandejaMensajesDietista.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error al cargar la bandeja del dietista", e);
        } finally {
            em.close();
            emf.close();
        }
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

    try {
    
        String receiverIdStr = request.getParameter("receiverId");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");
        String replyToStr = request.getParameter("replyToId");

        if (receiverIdStr == null || subject == null || body == null) {
            throw new ServletException("Faltan parámetros obligatorios.");
        }
        int receiverId = Integer.parseInt(receiverIdStr);
        Integer replyToId = (replyToStr != null && !replyToStr.isEmpty())
                ? Integer.valueOf(replyToStr)
                : null;

 
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idDietista") == null) {
            response.sendRedirect(request.getContextPath() + "/InicioSesion.jsp");
            return;
        }
        Long idDietistaLong = (Long) session.getAttribute("idDietista");
        int senderId = idDietistaLong.intValue();

      
        em.getTransaction().begin();
        Mensaje m = new Mensaje();
        m.setSenderId(senderId);
        m.setReceiverId(receiverId);
        m.setSubject(subject);
        m.setBody(body);
        m.setReplyToId(replyToId);
        m.setSentAt(new Timestamp(System.currentTimeMillis()));
        em.persist(m);
        em.getTransaction().commit();

        TypedQuery<Mensaje> query = em.createQuery(
            "SELECT m FROM Mensaje m WHERE m.receiverId = :dietistaId ORDER BY m.sentAt DESC", Mensaje.class);
        query.setParameter("dietistaId", senderId);
        List<Mensaje> mensajes = query.getResultList();

 
        Map<Integer, String> remitenteNombres = new HashMap<>();
        for (Mensaje msg : mensajes) {
            int remitenteId = msg.getSenderId();
            if (!remitenteNombres.containsKey(remitenteId)) {
            
                Paciente remitente = em.find(Paciente.class, (long) remitenteId);
                if (remitente != null) {
                    remitenteNombres.put(remitenteId, remitente.getNombre());
                } else {
                    remitenteNombres.put(remitenteId, "Desconocido");
                }
            }
        }

        request.setAttribute("mensajes", mensajes);
        request.setAttribute("remitenteNombres", remitenteNombres);
        request.setAttribute("enviado", true); // para mostrar mensaje de éxito

        
        request.getRequestDispatcher("/dietistas/BandejaMensajesDietista.jsp").forward(request, response);

    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new ServletException("Error al enviar el mensaje", e);
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
