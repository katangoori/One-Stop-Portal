<%-- 
    Document   : UpdateCloud
    Created on : Oct 9, 2015, 8:40:53 PM
    Author     : Katangoori
--%>

<%@page import="org.jsoup.select.Elements"%>
<%@page import="Service.HtmlParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG_Project</title>
    </head>
    <body>
<%  
    try {
        String emailid=(String)session.getAttribute("emailid");
        if(emailid==null){
            response.sendRedirect("login.jsp");
            session.invalidate();
        }
    }
    catch(Exception ex) {
    }
%>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Buy Cars And Trucks</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li ><a href="home.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
                        <li id="uploadcloud"><span class="glyphicon glyphicon-cloud"></span><button class="btn btn-default updateCloudButton">Update DataBase Cloud</button></li>
                        <li ><a href="viewstates.jsp"><span class="glyphicon glyphicon-eye-open"></span>View States</a></li>
                        <li ><a href="viewcities.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cities</a></li>
                        <li ><a href="viewcarsandtrucks.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cars and Trucks</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div>
            <div class="updatestatus" id="updatestatus" align="center">
                <h1>
                    Updating Data! <br>
                    Please Wait
                </h1>
            </div>
<%
    try{
        HtmlParser htmlParser=new HtmlParser();
        Elements links=new HtmlParser().getStatesAndLinks(); 
        htmlParser.saveStatesAndLinks(links);
        htmlParser.saveCitiesLinks();
        htmlParser.getCarsAndTrucsLinks();
    }
    catch(Exception ex){
    }   
%>

            <script type="text/javascript">
                $(document).ready(function(){
                    var html="<h1 style=\"color:green\">Data Updated Successfully</h1>";
                    $("#updatestatus").html(html);
                });
            </script>
        </div>
    </body>
</html>
