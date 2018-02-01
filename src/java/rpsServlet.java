/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Tracker.Scoreboard;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TecMilenio
 */
@WebServlet(urlPatterns = {"/rpsServlet"})
public class rpsServlet extends HttpServlet {

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
        HttpSession s = request.getSession();
        
        if(request.getParameter("resetGame") != null) {
            s.invalidate();
        } else {
            /*
            score[0] = jugada ganada
            score[1] = jugada empatada
            score[2] = partida ganada
            score[3] = partida empatada
            */
            int min = 1, max = 3;
            String servletMove = "", ganador = "";
            int servletMoveNum =  (int) Math.abs(Math.floor(Math.random()*(max-min+1)+min));
            /*
            1 = piedra
            2 = papel
            3 = tijeritas
            */

            Scoreboard sb = (Scoreboard) s.getAttribute("scoreboard");
            sb.nextRound();

            String move = request.getParameter("move");
            sb.move = move;

            sb.concludeMatch();

            switch(servletMoveNum) {
                case 1:
                    servletMove = "piedra";
                    switch(move) {
                        case "piedra":
                            sb.playOutcome(1);
                            ganador = "Empate";
                            break;
                        case "papel":
                            sb.playOutcome(0);
                            ganador = "Cliente";
                            break;
                        case "tijeritas":
                            ganador = "Servidor";
                            break;
                    }
                    break;
                case 2:
                    servletMove = "papel";
                    switch(move) {
                        case "piedra":
                            ganador = "Servidor";
                            break;
                        case "papel":
                            sb.playOutcome(1);
                            ganador = "Empate";
                            break;
                        case "tijeritas":
                            sb.playOutcome(0);
                            ganador = "Cliente";
                            break;
                    }
                    break;
                case 3:
                    servletMove = "tijera";
                    switch(move) {
                        case "piedra":
                            sb.playOutcome(0);
                            ganador = "Cliente";
                            break;
                        case "papel":

                            ganador = "Servidor";
                            break;
                        case "tijeritas":
                            sb.playOutcome(1);
                            ganador = "Empate";
                            break;
                    }
                    break;
            }
            sb.servletMove = servletMove;
            sb.currentWinner = ganador;
            s.setAttribute("scoreboard", sb);
        }
        response.sendRedirect("index.jsp");
        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet rpsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet rpsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            */
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
        processRequest(request, response);
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
