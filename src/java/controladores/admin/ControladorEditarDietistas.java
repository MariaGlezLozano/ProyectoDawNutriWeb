/*
 * Controlador Editar dietista
 */
package controladores.admin;

import entidades.Dietista;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ServicioDietista;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorEditarDietistas", urlPatterns = {"/admin/ControladorEditarDietistas"})
public class ControladorEditarDietistas extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        ServicioDietista sd = new ServicioDietista(emf);

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("ControladorAdmin");
            return;
        }

        try {
            Long id = Long.parseLong(idStr);
            Dietista dietista = sd.findById(id);

            if (dietista == null) {
                response.sendRedirect("ControladorAdmin");
                return;
            }

            request.setAttribute("id", dietista.getidDietista());
            request.setAttribute("nombre", dietista.getNombre());
            request.setAttribute("email", dietista.getEmail());
            request.setAttribute("activo", dietista.isActivo());

            getServletContext().getRequestDispatcher("/admin/editarDietista.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("ControladorPrincipalAdmin");
        } finally {
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
        ServicioDietista sd = new ServicioDietista(emf);
        String error = "";
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String nif = request.getParameter("nif");
        String activoStr = request.getParameter("activo");
        boolean activo = "true".equalsIgnoreCase(activoStr);

        try {
            Long id = Long.parseLong(idStr);
            Dietista dietista = sd.findById(id);

            if (dietista == null) {
                response.sendRedirect("ControladorPrincipalAdmin");
                return;
            }
            boolean estabaInactivo = !dietista.isActivo();

            if (request.getParameter("editar") != null) {
                dietista.setNombre(nombre);
                dietista.setEmail(email);
                dietista.setActivo(activo);
                sd.edit(dietista);
            }

            if (estabaInactivo && activo) {
                final String remitente = "gonzalez.lozano.maria@iescamas.es";
                final String clave = "kwhl qnyd pnmq swec";
                String destinatario = dietista.getEmail();
                String asunto = "Has sido activado en la base de datos";
                String cuerpo = "Has sido activado en la base de datos de la aplicación NutriWeb, ya puedes hacer uso de ella.";

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remitente, clave);
                    }
                });

                try {
                    MimeMessage mensaje = new MimeMessage(session);
                    mensaje.setFrom(new InternetAddress(remitente));
                    mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                    mensaje.setSubject(asunto);
                    mensaje.setText(cuerpo);

                    Transport.send(mensaje);
                    System.out.println("Correo de activación enviado a " + destinatario);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }

            if (request.getParameter("eliminar") != null) {
                try {
                    if (dietista == null) {
                        error = "El usuario no existe.";
                    } else {
                        sd.destroy(id);
                        emf.close();
                        response.sendRedirect("ControladorPrincipalAdmin");
                        return;
                    }
                } catch (Exception e) {
                    error = "Error al eliminar el usuario: " + e.getMessage();
                    e.printStackTrace();
                }
            }

            emf.close();
            request.setAttribute("error", error);
            request.setAttribute("id", idStr);
            request.setAttribute("nombre", nombre);
            request.setAttribute("email", email);
            request.setAttribute("activo", activoStr);
            getServletContext().getRequestDispatcher("/admin/editarDietista.jsp").forward(request, response);

        } catch (Exception e) {
            error = "Error al actualizar/eliminar usuario: " + e.getMessage();
            e.printStackTrace();
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/admin/editarDietista.jsp").forward(request, response);
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
