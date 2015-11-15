<%-- 
    Document   : ViewCities
    Created on : Oct 10, 2015, 4:18:44 AM
    Author     : Katangoori
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="DAO.DataBaseConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function() {
                $(".updateCloudButton").click(function() {
                    $(".updateCloudButton").disabled;
                    $(".updateCloudButton").removeClass(".btn-default");
                    $(".updateCloudButton").addClass(".disabled");
                    $(".updateCloudButton").addClass(".btn-danger");
                    $(".notification_Status").html("<div align=\"center\" ><h1 color=\"red\" align=\"center\">Please Wait!</h1></div>");
                    $.post("UpdateCloud.html",
                    {
                        update_cloud: "update"
                    },
                    function(data,status){
                        if(status==="success"){
                            $(".updateCloudButton").removeAttr("disabled");
                            $(".notofication_Status").html(data);
                            $(".updateCloudButton").removeClass(".btn-default");
                            $(".updateCloudButton").removeClass(".disabled");
                            $(".updateCloudButton").removeClass(".btn-danger");
                            $(".updateCloudButton").addClass(".btn-success");
                        } 
                        else {
                            $(".notification_Status").html("<div align=\"center\" ><h1 color=\"red\" align=\"center\">Unable to connect. <br> Try Again!</h1></div>");
                        }
                    });
                });
            });
        </script>
        <div class="notification_status" align="center"></div>
    </body>
        
<%
        try {
            String emailid=(String)session.getAttribute("emailid");
            if(emailid==null){
                response.sendRedirect("login.jsp");
                session.invalidate();
            }
        } 
        catch (Exception ex) {
                
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
                        <li id="uploadcloud" style="padding: 7px;"><span class="glyphicon glyphicon-cloud"></span><button class="btn btn-default updateCloudButton">Update DataBase Cloud</button></li>
                        <li ><a href="viewstates.jsp"><span class="glyphicon glyphicon-eye-open"></span>View States</a></li>
                        <li class="active"><a href="viewcities.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cities</a></li>
                        <li ><a href="viewcarsandtrucks.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cars and Trucks</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div id="uploadcloudstatus" align="center" ></div>
        <div align="center">
<%
        Statement stmt=DataBaseConnection.getStatement();
        ResultSet rs=null;
        String sqlquery="select * from cities";
        try {
            rs=stmt.executeQuery(sqlquery);
%>
            <table class="table" align="center">
                <thead>
                    <th class="active">ID</th>
                        <th class="active">City</th>
                        <th class="active">City Link</th>
                        <th class="active">State</th>
                        <th class="active">State Link</th>
                </thead>
                <tbody>
<%
            while(rs.next()){
%>
                    <tr>
                        <td class="success"><%=rs.getInt("id")%></td>
                        <td class="warning"><%=rs.getString("cityname")%></td>
                        <td class="danger"><%=rs.getString("cityaddresslink")%></td>
                        <td class="warning"><%=rs.getString("statename")%></td>
                        <td class="danger"><%=rs.getString("stateaddresslink")%></td>
                    </tr>
<%
            }
        } 
        catch(Exception ex){
                        
        }
%>
                </tbody>
            </table>
        </div>
</html>
