/*
 * Controlador de Registro
 */
package controladores;

import entidades.Dietista;
import entidades.Paciente;
import java.io.IOException;
import java.time.LocalDate;
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
@WebServlet(name = "ControladorRegistro", urlPatterns = {"/ControladorRegistro"})
public class ControladorRegistro extends HttpServlet {

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
        processRequest(request, response);
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
        String apellidos = request.getParameter("apellidos");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String tipo = request.getParameter("tipo");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nif = request.getParameter("nif");
        String direccion = request.getParameter("direccion");
        String profesional = request.getParameter("profesional");
        String repetirPassword = request.getParameter("repetirPassword");
        String error = "";
        String vista = "ControladorPrincipal";

        if (tipo.equals("paciente")) {
            if (nombre == null || nombre.trim().isEmpty() || apellidos == null || apellidos.trim().isEmpty() || fechaNacimiento == null
                    || fechaNacimiento.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("Registro.jsp").forward(request, response);
                return;
            }
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
            ServicioPaciente sp = new ServicioPaciente(emf);

            if (request.getParameter("registrarse") != null) {

                if (!password.equals(repetirPassword)) {
                    error = "Las contraseñas no coinciden.";
                    vista = "ControladorPrincipal";
                } else {
                    Paciente pacienteExistente = sp.findByEmail(email);
                    if (pacienteExistente != null) {
                        error = "El email ya está registrado. Usa otro.";
                    }

                    Paciente paciente = new Paciente();
                    paciente.setNombre(nombre);
                    paciente.setApellidos(apellidos);
                    paciente.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
                    paciente.setEmail(email);
                    paciente.setPassword(password);

                    try {
                        sp.create(paciente);
                        request.getSession().setAttribute("mensaje", "El usuario ha sido registrado con éxito.");
                        response.sendRedirect("ControladorPrincipal");
                        return;
                    } catch (Exception e) {
                        System.out.println("Error al crear usuario: " + e.getMessage());
                        error = "Error al registrar usuario.";
                    }
                }
            }

        }

        if (tipo.equals("empresa")) {
            if (nombre == null || nombre.trim().isEmpty() || nif == null || nif.trim().isEmpty() || direccion == null
                    || direccion.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("Registro.jsp").forward(request, response);
                return;
            }
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
            ServicioDietista sd = new ServicioDietista(emf);

            if (request.getParameter("registrarse") != null) {
                if (!password.equals(repetirPassword)) {
                    error = "Las contraseñas no coinciden.";
                    vista = "/ControladorPrincipal";
                } else {
                    Dietista dietistaExistente = sd.findByEmail(email);
                    if (dietistaExistente != null) {
                        error = "El email ya está registrado. Usa otro.";
                    }

                    Dietista dietista = new Dietista();
                    dietista.setNombre(nombre);
                    dietista.setNif(nif);
                    dietista.setProfesional(profesional);
                    dietista.setDireccion(direccion);
                    dietista.setEmail(email);
                    dietista.setPassword(password);
                    dietista.setActivo(false);

                    try {
                        sd.create(dietista);
                        request.getSession().setAttribute("mensaje", "El administrador activará su cuenta si ha abonado la tarifa.");
                        response.sendRedirect("ControladorPrincipal");
                        return;
                    } catch (Exception e) {
                        System.out.println("Error al crear empresa: " + e.getMessage());
                        error = "Error al registrar empresa.";
                    }
                }
            } else {
                error = "Las contraseñas no coinciden.";
            }
        }
        request.setAttribute("error", error);
        getServletContext().getRequestDispatcher(vista).forward(request, response);
        request.setAttribute("error", null);
        request.setAttribute("mensaje", null);
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
