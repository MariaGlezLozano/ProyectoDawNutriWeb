/*
 * Controlador Contacto
 */
package controladores;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorContacto", urlPatterns = {"/ControladorContacto"})
public class ControladorContacto extends HttpServlet {

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
        response.sendRedirect("Contacto.jsp"); // Si alguien accede directamente a la URL sin datos
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

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String cuerpo = request.getParameter("mensaje");

        if (nombre == null || nombre.trim().isEmpty() || email == null || email.trim().isEmpty() || cuerpo == null || cuerpo.trim().isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("Contacto.jsp").forward(request, response);
            return;
        } else {
            final String remitente = "gonzalez.lozano.maria@iescamas.es";
            final String clave = "kwhl qnyd pnmq swec";
            String destinatario = email;
            String asunto = "Se ha enviado un formulario de contacto";
            String cuerpoMensaje = cuerpo;

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
                mensaje.setText(cuerpoMensaje);

                Transport.send(mensaje);
                System.out.println("Correo de activación enviado a " + destinatario);
                request.setAttribute("mensajeExito", "Gracias por su mensaje, contactaremos con usted lo antes posible.");
            } catch (Exception e) {
                request.setAttribute("mensajeExito", "Hubo un error al enviar el mensaje. Inténtelo más tarde.");
            }
            request.getRequestDispatcher("/Contacto.jsp").forward(request, response);
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
