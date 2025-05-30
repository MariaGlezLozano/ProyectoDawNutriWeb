/*
 * Controlador para registrar una nueva consulta
 */
package controladores.dietista;

import entidades.Consulta;
import entidades.Dieta;
import entidades.Dietista;
import entidades.Paciente;
import entidades.Receta;
import java.io.IOException;
import java.time.LocalDate;
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
import servicios.ServicioDieta;
import servicios.ServicioReceta;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorNuevaConsulta", urlPatterns = {"/dietista/ControladorNuevaConsulta"})
public class ControladorNuevaConsulta extends HttpServlet {

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
        Long idPaciente = Long.parseLong(request.getParameter("id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();
        ServicioDieta servicioDieta = new ServicioDieta(emf);
        ServicioReceta servicioReceta = new ServicioReceta(emf);

        // Obtener el paciente por ID
        Paciente paciente = em.find(Paciente.class, idPaciente);

        HttpSession session = request.getSession();
        Dietista dietista = (Dietista) session.getAttribute("dietista");

        if (paciente != null && dietista != null) {
            // Si el paciente existe, lo pasamos al JSP
            request.setAttribute("paciente", paciente);
            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();
            request.setAttribute("fechaActual", fechaActual);
            List<Dieta> listaDietas = servicioDieta.obtenerDietasPorDietista(dietista.getidDietista());
            List<Receta> listaRecetas = servicioReceta.obtenerRecetasPorDietista(dietista.getidDietista());
            request.setAttribute("dietas", listaDietas);
            request.setAttribute("recetas", listaRecetas);
        } else {
            // Si no se encuentra al paciente, redirigir a una página de error
            response.sendRedirect("error.jsp");
            return;
        }

        em.close();
        emf.close();

        // Redirigir al JSP para registrar la nueva consulta
        request.getRequestDispatcher("/dietistas/NuevaConsulta.jsp").forward(request, response);

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
        Long idPaciente = Long.parseLong(request.getParameter("idPaciente"));  // Obtener el ID del paciente desde el formulario
        Double peso = Double.parseDouble(request.getParameter("peso"));
        Double estatura = Double.parseDouble(request.getParameter("estatura"));
        Long idDietista = Long.parseLong(request.getParameter("idDietista"));
        Long idDieta = Long.parseLong(request.getParameter("idDieta"));
        Long idReceta = Long.parseLong(request.getParameter("idReceta"));
        // Obtener la fecha de la consulta desde el formulario (si está vacía, usar la fecha actual)
        String fechaConsultaParam = request.getParameter("fechaConsulta");
        LocalDate fechaConsulta = (fechaConsultaParam != null && !fechaConsultaParam.isEmpty())
                ? LocalDate.parse(fechaConsultaParam)
                : LocalDate.now();

        // Obtener la fecha de la consulta (puedes usar la fecha actual)
        //LocalDate fechaConsulta = LocalDate.now();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        // Obtener paciente
        Paciente paciente = em.find(Paciente.class, idPaciente);
        Receta receta = em.find(Receta.class, idReceta);
        Dieta dieta = em.find(Dieta.class, idDieta);

        if (paciente != null) {
            // Crear nueva consulta
            Consulta consulta = new Consulta();
            consulta.setFechaConsulta(fechaConsulta);
            consulta.setPeso(peso);
            consulta.setEstatura(estatura);
            consulta.setPaciente(paciente);
            consulta.setDieta(dieta);
            dieta.setPaciente(paciente);
            consulta.setReceta(receta);

            Dietista dietista = em.find(Dietista.class, idDietista);

            consulta.setDietista(dietista);

            // Persistir consulta
            em.getTransaction().begin();
            em.persist(consulta);
            em.merge(dieta);
            em.getTransaction().commit();
        }

        em.close();
        emf.close();

        // Redirigir al paciente o a una página de éxito
        response.sendRedirect("../dietistas/InicioSesionDietista.jsp");

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
