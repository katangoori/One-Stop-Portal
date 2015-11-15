package Pages;
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
 * @author Katangoori
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

public void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            HttpSession session=request.getSession();
            System.out.println("Hello");
            if(request.getParameter("emailid").equals("sivadeepkatangoori@gmail.com") && request.getParameter("password").equals("sivadeep")) 
            {
                session.setAttribute("emailid", request.getParameter("emailid").equals("sivadeepkatangoori@gmail.com"));
                response.sendRedirect("home.jsp");
            }
		else {
                	session.setAttribute("emailid", "invalid");
                	response.sendRedirect("login.jsp");
                     }
        }
	catch(Exception ex)
        {
            ex.printStackTrace();
        }
	finally {   
            out.close();
        }
    }
}