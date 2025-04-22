/*
 * Controlador Editar paciente
 */
package controladores.paciente;

import entidades.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ServicioPaciente;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorEditarPerfil", urlPatterns = {"/paciente/ControladorEditarPerfil"})
public class ControladorEditarPerfil extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        ServicioPaciente sp = new ServicioPaciente(emf);
        String error = "";
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String apellidos = request.getParameter("apellidos");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            Long id = Long.parseLong(idStr);
            Paciente paciente = sp.findById(id);

            if (paciente == null) {
                response.sendRedirect("ControladorPrincipalAdmin");
                return;
            }
            if (request.getParameter("editar") != null) {
                paciente.setNombre(nombre);
                paciente.setApellidos(apellidos);
                try {
                    DateTimeFormatter formatoISO = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    DateTimeFormatter formatoAlternativo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaNacimiento;

                    try {
                        fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatoISO);
                    } catch (Exception e) {

                        fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatoAlternativo);
                    }

                    paciente.setFechaNacimiento(fechaNacimiento);

                } catch (Exception e) {
                    error = "La fecha de nacimiento no es válida";
                }

                paciente.setEmail(email);
                sp.edit(paciente);
                request.getSession().setAttribute("paciente", paciente);
                System.out.println("Paciente actualizado con éxito.");
                response.sendRedirect(request.getContextPath() + "/pacientes/menuPaciente.jsp");

            }
        } catch (Exception e) {
            error = "Error al actualizar los datos del paciente: " + e.getMessage();
            e.printStackTrace();
            request.setAttribute("error", error);

            response.sendRedirect("pacientes/perfil.jsp");

            response.sendRedirect(request.getContextPath() + "/pacientes/menuPaciente.jsp");
            // getServletContext().getRequestDispatcher("/pacientes/menuPaciente.jsp").forward(request, response);
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
