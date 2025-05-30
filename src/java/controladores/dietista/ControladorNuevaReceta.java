/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.dietista;

import entidades.Dietista;
import entidades.Paciente;
import entidades.Receta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
import servicios.ServicioPaciente;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorNuevaReceta", urlPatterns = {"/dietista/ControladorNuevaReceta"})
public class ControladorNuevaReceta extends HttpServlet {

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

        Dietista dietista = (Dietista) session.getAttribute("dietista");
        if (dietista == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        // Obtener todas las dietas creadas por el dietista
        List<Receta> recetas = em.createQuery("SELECT r FROM Receta r WHERE r.autor = :dietista", Receta.class)
                .setParameter("dietista", dietista)
                .getResultList();

        request.setAttribute("recetas", recetas);
        em.close();

        // Redirigir a la página JSP que mostrará las dietas
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dietistas/ListaRecetas.jsp");
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();
        HttpSession session = request.getSession();
        Dietista dietista = (Dietista) session.getAttribute("dietista");

        try {
            String nombre = request.getParameter("nombre");
            String ingredientesText = request.getParameter("ingredientes"); // texto con lista ingredientes
            String instrucciones = request.getParameter("preparacion");
            String notas = request.getParameter("notas"); // si quieres guardarlo en otro campo o ignorar
            //String idDietistaStr = request.getParameter("idDietista"); // supongo que se lo pasas o tienes sesión

            if (dietista == null) {
                request.setAttribute("mensaje", "Dietista no encontrado.");
                request.getRequestDispatcher("/dietistas/Recetas.jsp").forward(request, response);
                return;
            }

            if (nombre == null || ingredientesText == null || instrucciones == null) {
                request.setAttribute("mensaje", "Faltan datos para guardar la receta.");
                request.getRequestDispatcher("/dietistas/Recetas.jsp").forward(request, response);
                return;
            }

            //  Long idDietista = Long.parseLong(idDietistaStr);
            // Buscar dietista
            // Dietista dietista = em.find(Dietista.class, idDietista);
            dietista = em.find(Dietista.class, dietista.getidDietista());
            // dietista = em.merge(dietista);

            // Convertir texto ingredientes a lista
            // Ejemplo simple: cada línea es un ingrediente
            List<String> ingredientes = Arrays.stream(ingredientesText.split("\\r?\\n"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            Receta receta = new Receta();
            receta.setNombre(nombre);
            receta.setIngredientes(ingredientes);
            receta.setInstrucciones(instrucciones);
            receta.setNotas(notas);
            receta.setAutor(dietista);

            em.getTransaction().begin();
            System.out.println("Dietista ID: " + dietista.getidDietista());
            em.persist(receta);
            em.getTransaction().commit();

            request.setAttribute("mensaje", "Receta guardada correctamente.");
            response.sendRedirect(request.getContextPath() + "/dietistas/InicioSesionDietista.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("mensaje", "Error al guardar la receta: " + e.getMessage());
            request.getRequestDispatcher("/dietistas/Recetas.jsp").forward(request, response);
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
