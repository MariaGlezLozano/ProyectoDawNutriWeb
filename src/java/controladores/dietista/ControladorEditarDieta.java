/*
 * Controlador para editar dietas ya hechas
 */
package controladores.dietista;

import entidades.Dieta;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControladorEditarDieta", urlPatterns = {"/dietista/ControladorEditarDieta"})
public class ControladorEditarDieta extends HttpServlet {

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
            String idStr = request.getParameter("id");
            Long id = Long.parseLong(idStr);
            Dieta dieta = em.find(Dieta.class, id);
            
            if (dieta == null) {
                response.sendRedirect("InicioSesionDietista.jsp?error=Dieta+no+encontrada");
                return;
            }

            request.setAttribute("dieta", dieta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/dietistas/EditarDieta.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar la dieta", e);
        } finally {
            em.close();
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
        HttpSession session = request.getSession();

        try {
            Long dietaId = Long.parseLong(request.getParameter("id"));
            Dieta dieta = em.find(Dieta.class, dietaId);

            if (dieta == null) {
                response.sendRedirect("InicioSesionDietista.jsp?error=Dieta+no+encontrada");
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

            em.getTransaction().begin();

            dieta.setNombre(nombre);
            dieta.setDescripcion(descripcion.toString());

            em.getTransaction().commit();

            response.sendRedirect("../dietistas/InicioSesionDietista.jsp?mensaje=Dieta+actualizada");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("Error al actualizar la dieta", e);
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
