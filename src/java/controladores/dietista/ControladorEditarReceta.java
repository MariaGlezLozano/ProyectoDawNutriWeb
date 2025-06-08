/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.dietista;

import entidades.Receta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ServicioReceta;

/**
 *
 * @author Maria
 */
@WebServlet(name = "ControladorEditarReceta", urlPatterns = {"/dietista/ControladorEditarReceta"})
public class ControladorEditarReceta extends HttpServlet {

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
        ServicioReceta servicioReceta = new ServicioReceta(emf);
        String idStr = request.getParameter("id");

        if (idStr == null) {
            response.sendRedirect("ControladorNuevaReceta");
            return;
        }

        Long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("ControladorNuevaReceta");
            return;
        }

        Receta receta = servicioReceta.findById(id);
        if (receta == null) {
            response.sendRedirect("ControladorNuevaReceta");
            return;
        }
        request.setAttribute("receta", receta);
        request.getRequestDispatcher("/dietistas/EditarReceta.jsp").forward(request, response);

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
        ServicioReceta servicioReceta = new ServicioReceta(emf);
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String instrucciones = request.getParameter("instrucciones");
        String notas = request.getParameter("notas");
        String ingredientesRaw = request.getParameter("ingredientes"); // Ingredientes separados por línea o coma

        String error = "";

        if (nombre == null || nombre.trim().isEmpty()) {
            error = "El nombre es obligatorio.";
        }

        if (error.isEmpty()) {
            Long id = Long.parseLong(idStr);
            Receta receta = servicioReceta.findById(id);
            if (receta == null) {
                error = "Receta no encontrada.";
            } else {
                receta.setNombre(nombre);
                receta.setInstrucciones(instrucciones);
                receta.setNotas(notas);

                // Convertir ingredientes en lista
                List<String> ingredientes = Arrays.stream(ingredientesRaw.split("\\r?\\n"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();
                receta.setIngredientes(ingredientes);

                try {
                    servicioReceta.update(receta);
                    request.getSession().setAttribute("mensaje", "Receta actualizada correctamente.");
                    response.sendRedirect("ControladorNuevaReceta");
                    return;
                } catch (Exception e) {
                    error = "Error al actualizar la receta: " + e.getMessage();
                }
            }
        }

        request.setAttribute("error", error);
        request.getRequestDispatcher("/dietistas/EditarReceta.jsp").forward(request, response);

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
