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
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katangoori
 */
public class GetAllDetails extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Connection connection= DataBaseConnection.getDataBaseConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails where id="+request.getParameter("id"));
            String html="";
            while(resultSet.next()) {                
            }
        }
            catch (Exception e) {
                    e.printStackTrace();
                    }
            finally {
                    out.close();
                    }            
        }
    }
 
