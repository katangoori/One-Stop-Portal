/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Service.SearchEngineHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katangoori
 */
public class GetSearchContent extends HttpServlet {
    public void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String state=request.getParameter("state");
        String city=request.getParameter("city");
        String searchQuery=request.getParameter("query");
        String sqlquery="",searchHtml="";int i=0;
        try {
            searchHtml=SearchEngineHelper.getCommonContent(searchQuery, state, city);
            out.println(searchHtml);
        }
        catch (ArrayIndexOutOfBoundsException e) {   
            
        }
        catch (Exception e) {
            
        }
        finally {
            out.close();
        }
    }
    
}
