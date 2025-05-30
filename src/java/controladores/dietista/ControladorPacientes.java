/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.dietista;

import entidades.Paciente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
@WebServlet(name = "ControladorPacientes", urlPatterns = {"/dietista/ControladorPacientes"})
public class ControladorPacientes extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String filtro = request.getParameter("filtro");
        String idParam = request.getParameter("idDietista");

        if (filtro == null) {
            filtro = "";
        }

        Long idDietista = null;
        try {
            idDietista = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de dietista inválido.");
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Paciente> crearConsulta = em.createQuery(
                    "SELECT p FROM Paciente p WHERE p.dietista.idDietista = :idDietista AND "
                    + "(LOWER(p.nombre) LIKE :filtro OR LOWER(p.apellidos) LIKE :filtro)",
                    Paciente.class
            );
            crearConsulta.setParameter("idDietista", idDietista);
            crearConsulta.setParameter("filtro", "%" + filtro.toLowerCase() + "%");

            List<Paciente> pacientesFiltrados = crearConsulta.getResultList();

            request.setAttribute("pacientesFiltrados", pacientesFiltrados);
            request.setAttribute("idDietista", idDietista); 
            getServletContext().getRequestDispatcher("/dietistas/Pacientes.jsp").forward(request, response);
        } finally {
            em.close();
            emf.close();
        }
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

        String filtro = request.getParameter("filtro");
        String idDietistaStr = request.getParameter("idDietista");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        try {
            List<Paciente> pacientes = new ArrayList<>();

            if (filtro != null && !filtro.trim().isEmpty() && idDietistaStr != null && !idDietistaStr.isEmpty()) {
                // Buscar por filtro Y dietista
                TypedQuery<Paciente> query = em.createQuery(
                        "SELECT p FROM Paciente p WHERE "
                        + "(LOWER(p.nombre) LIKE :filtro OR LOWER(p.apellidos) LIKE :filtro) AND p.dietista.idDietista = :idDietista",
                        Paciente.class
                );
                query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
                query.setParameter("idDietista", Long.parseLong(idDietistaStr));
                pacientes = query.getResultList();
                request.setAttribute("pacientesFiltrados", pacientes);

            } else if (filtro != null && !filtro.trim().isEmpty()) {
                // Buscar solo por filtro (sin dietista)
                TypedQuery<Paciente> query = em.createQuery(
                        "SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE :filtro OR LOWER(p.apellidos) LIKE :filtro",
                        Paciente.class
                );
                query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
                pacientes = query.getResultList();
                request.setAttribute("pacientesFiltrados", pacientes);

            } else if (idDietistaStr != null && !idDietistaStr.isEmpty()) {
                // Buscar solo por dietista
                ServicioPaciente servicioPaciente = new ServicioPaciente(emf);
                pacientes = servicioPaciente.obtenerPacientesPorDietista(Long.parseLong(idDietistaStr));
                request.setAttribute("listaPacientes", pacientes);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros insuficientes.");
                return;
            }

            // Mantener el idDietista para futuras búsquedas
            request.setAttribute("idDietista", idDietistaStr);

            request.getRequestDispatcher("/dietistas/Pacientes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().println("ERROR DETECTADO:");
            response.getWriter().println("Tipo: " + e.getClass().getName());
            response.getWriter().println("Mensaje: " + e.getMessage());
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
        //processRequest(request, response);
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
