/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import DAO.DataBaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katangoori
 */
public class GetCities extends HttpServlet {
    public void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String state=request.getParameter("state");
            Connection connection= DataBaseConnection.getDataBaseConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select cityname from cities where statename='"+state+"'");
            String html="<option value=\"none\">----Select City----</option>";
            while(resultSet.next()) {
                html=html+"\n<option value=\""+resultSet.getString("cityname")+"\">"+resultSet.getString("cityname")+"</option>";
            }
            out.println(html);
        }
        catch (Exception e) {            
        }
        finally {
            out.println("<h1>Test</h1>");
            out.close();
        }
    }
            
    
}
