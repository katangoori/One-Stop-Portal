/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Service.HtmlParser;
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
public class UpdateCloud extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try {
                System.out.println(request.getContextPath());
                HtmlParser htmlParser=new HtmlParser();
                //Elements links=new HtmlParser().getStatesAndLinks(); 
                //htmlParser.saveStatesAndLinks(links);
                out.println();
                //htmlParser.saveCitiesLinks();
                //htmlParser.getCarsAndTrucsLinks();
                htmlParser.saveCarsTrucksdetails(request.getContextPath()+"/images/");
                System.out.println(request.getContextPath()+"/images/");
                out.println("<div align=\"center\" ><h1 color=\"green\" align=\"center\">Successfully Updated</h1><img src=\"tick-mark-green.png\" style=\" width: 200px; height: 200px;\"></div>");                
            }
            catch (Exception e) {
                out.println("<div align=\"center\" ><h1 color=\"red\" align=\"center\">we are Sorry Unable to process your request contact your developer</h1></div>");
            }
        }
        finally {
            out.close();
        }
    }
    
}
