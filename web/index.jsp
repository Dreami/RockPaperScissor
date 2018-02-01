<%-- 
    Document   : index
    Created on : 31/01/2018, 04:37:48 PM
    Author     : TecMilenio
--%>
<%@ page import="Tracker.Scoreboard" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
            body {
                font-size: 24px;
                padding: 15px;
                margin-bottom: 10px;
            }
            
            table, tr, td {
                border: solid 1px black;
                padding: 10px;
            }
        </style>
        
    </head>
    <body>
        <%
            HttpSession s = request.getSession();
            Scoreboard sb =  new Scoreboard();
            if(s.getAttribute("scoreboard") == null) {
               s.setAttribute("scoreboard", sb);
            } else {
                sb = (Scoreboard) s.getAttribute("scoreboard");
            }
        %>
        <table>
            <tr>
                <td>Jugada <%=sb.round%></td>
                <td>Cliente</td>
                <td>Servidor</td>
            </tr>
            <tr>
                <td>Movimiento</td>
                <td><%=sb.move%></td>
                <td><%=sb.servletMove%></td>
            </tr>
            <tr>
                <td>Ganador</td>
                <td><%=sb.currentWinner%></td>
            </tr>
            <tr>
                <td>Jugadas ganadas</td>
                <td><%=sb.scores[0]%></td>
            </tr>
            <tr>
                <td>Jugadas empatadas</td>
                <td><%=sb.scores[1]%></td>
            </tr>
            <tr>
                <td>Partidas ganadas</td>
                <td><%=sb.scores[2]%></td>
            </tr><tr>
                <td>Partidas empatadas</td>
                <td><%=sb.scores[3]%></td>
            </tr>
        </table>
            
        </table>
        
        <form action="rpsServlet" method="GET">
            <input type="radio" name="move" value="piedra" checked> Piedra
            <input type="radio" name="move" value="papel"> Papel
            <input type="radio" name="move" value="tijeritas"> Tijeritas
            <button type="submit">YO TE ELIJO</button><br/>
            <button type="submit" name="resetGame">Empezar de nuevo</button>
        </form>
        </form>
    </body>
</html>
