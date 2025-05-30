/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.paciente;

import entidades.Actividades;
import entidades.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ControladorActividadesPaciente", urlPatterns = {"/paciente/ControladorActividadesPaciente"})
public class ControladorActividadesPaciente extends HttpServlet {

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
        HttpSession session = request.getSession();

        Paciente paciente = (Paciente) session.getAttribute("paciente");

        if (paciente == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        // Obtener todas las actividades del paciente desde la base de datos
        List<Actividades> actividades = em.createQuery(
                "SELECT a FROM Actividades a JOIN a.pacientes p WHERE p = :paciente", Actividades.class)
                .setParameter("paciente", paciente)
                .getResultList();

        request.setAttribute("actividades", actividades);
        em.close();

        // Redirigir a la página JSP que mostrará las actividades
        RequestDispatcher dispatcher = request.getRequestDispatcher("../pacientes/ListaActividades.jsp");
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
        
        HttpSession session = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();

        Paciente paciente = (Paciente) session.getAttribute("paciente");

        if (paciente == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String nombreActividad = request.getParameter("nombre");
        Double tiempo = null;

        try {
            tiempo = Double.parseDouble(request.getParameter("tiempo"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El tiempo debe ser un número válido.");
            doGet(request, response);
            return;
        }

        em.getTransaction().begin();

        // Buscar la actividad si ya existe (para evitar duplicados innecesarios)
        List<Actividades> actividadExistente = em.createQuery("SELECT a FROM Actividades a WHERE a.nombre = :nombre", Actividades.class)
                .setParameter("nombre", nombreActividad)
                .getResultList();

        Actividades actividad;
        if (actividadExistente.isEmpty()) {
            // Si la actividad no existe, la creamos
            actividad = new Actividades();
            actividad.setNombre(nombreActividad);
            actividad.setTiempo(tiempo);

            em.persist(actividad); // Guardamos la nueva actividad en la BD
        } else {
            // Si existe, la reutilizamos
            actividad = actividadExistente.get(0);
        }

        // Asociar la actividad al paciente
        paciente.getActividades().add(actividad);
        if (actividad.getPacientes() == null) {
            actividad.setPacientes(new ArrayList<>()); // Inicializamos la lista
        }
        actividad.getPacientes().add(paciente);

        em.merge(paciente); // Guardamos la relación en la base de datos
        em.getTransaction().commit();
        em.close();

        // Redirigir a la lista de actividades del paciente
        response.sendRedirect(request.getContextPath() + "/paciente/ControladorActividadesPaciente");
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
