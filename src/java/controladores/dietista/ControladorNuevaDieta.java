/*
 * Controlador para guardar una nueva dieta y mostrar lista de dietas creadas
 */
package controladores.dietista;

import entidades.Dieta;
import entidades.Dietista;
import entidades.Paciente;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ControladorNuevaDieta", urlPatterns = {"/dietista/ControladorNuevaDieta"})
public class ControladorNuevaDieta extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();
        HttpSession session = request.getSession();

        Dietista dietista = (Dietista) session.getAttribute("dietista"); // Obtener el dietista actual
        if (dietista == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        // Obtener todas las dietas creadas por el dietista
        List<Dieta> dietas = em.createQuery("SELECT d FROM Dieta d WHERE d.dietista = :dietista", Dieta.class)
                .setParameter("dietista", dietista)
                .getResultList();

        request.setAttribute("dietas", dietas);
        em.close();

        // Redirigir a la página JSP que mostrará las dietas
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dietistas/ListaDietas.jsp");
        dispatcher.forward(request, response);
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
        HttpSession session = request.getSession();
        Dieta dieta = new Dieta();
        Dietista dietista = (Dietista) session.getAttribute("dietista");

        if (dietista == null) {
            response.sendRedirect("login.jsp"); // O muestra error de autenticación
            return;
        }

        String nombre = request.getParameter("nombre");
        String notas = request.getParameter("notas");

        String[] dias = {"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
        StringBuilder descripcion = new StringBuilder();

        for (String dia : dias) {
            descripcion.append(dia.substring(0, 1).toUpperCase())
                    .append(dia.substring(1)).append(":\n");

            descripcion.append("Desayuno: ").append(request.getParameter(dia + "_desayuno")).append("\n");
            descripcion.append("Media mañana: ").append(request.getParameter(dia + "_almuerzo")).append("\n");
            descripcion.append("Comida: ").append(request.getParameter(dia + "_comida")).append("\n");
            descripcion.append("Merienda: ").append(request.getParameter(dia + "_merienda")).append("\n");
            descripcion.append("Cena: ").append(request.getParameter(dia + "_cena")).append("\n\n");
        }

        descripcion.append("Notas adicionales:\n").append(notas);

        try {
            em.getTransaction().begin();

            dieta.setNombre(nombre);
            dieta.setDescripcion(descripcion.toString());
            dieta.setDietista(dietista);  // Asignamos el dietista
            dieta.setPaciente(null);      // No se asigna aún un paciente

            em.persist(dieta);
            em.getTransaction().commit();

            response.sendRedirect(request.getContextPath() + "/dietistas/InicioSesionDietista.jsp?mensaje=Dieta+creada");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("Error al guardar la dieta", e);
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
