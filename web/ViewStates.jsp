<%-- 
    Document   : ViewStates
    Created on : Oct 10, 2015, 4:19:00 AM
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
            $(document).ready(function () {
                $(".updateCloudButton").click(function() {
                    $(".updateCloudButton").disabled;
                    $(".updateCloudButton").removeClass(".btn-default");
                    $(".updateCloudButton").addClass(".disabled");
                    $(".updateCloudButton").addClass(".btn-danger");
                    $(".notification_status").html("<div align=\"center\"><h1 color=\"red\" align=\"center\">Please Wait</h1></div>);
                    $.post("#uploadcloud.html",
                    {
                        update_cloud:"update"
                    },
                    fuction(date,status){
                        if (status=="success") {
                            $(".updateCloudButton").removeAttr("disabled");
                            $(".notofication_Status").html(data);
                            $(".updateCloudButton").removeClass(".btn-default");
                            $(".updateCloudButton").removeClass(".disabled");
                            $(".updateCloudButton").removeClass(".btn-danger");
                            $(".updateCloudButton").addClass(".btn-success");
                        } 
                        else {
                            $".notification_Status").html("<div align=\"center\" ><h1 color=\"red\" align=\"center\">Unable to connect.<br> Try Again!</h1></div>");
                        }
                    });
                });
            });
                </script>
                <div class="notification_status" align="center"></div>
    </body>
<%      try {
            String emailid=(String)session.getAttribute("emailid");
            if(emailid==null){
                response.sendRedirect("login.jsp");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
%>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Buy Cars And Trucks</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li><a href="Home.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
                        <li id="uploadcloud" style="padding: 7px;">
                            <span class="glyphicon glyphicon-cloud"></span>
                            <button class="btn btn-default updateCloudButton">Update DataBase Cloud</button>
                        </li>
                        <li class="active"><a href="viewstates.jsp"><span class="glyphicon glyphicon-eye-open"></span>View States</a></li>
                        <li >
                            <a href="viewcities.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cities</a>
                        </li>
                        <li >
                            <a href="viewcarsandtrucks.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cars and Trucks</a>
                        </li>
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
        String sqlquery="select * from states";
        try {
            rs=stmt.executeQuery(sqlquery);
%>
            <table class="table" align="center">
                <thead>
                    <th class="active">ID</th>
                    <th class="active">State Name</th>
                    <th class="active">Link</th>
                </thead>
                <tbody>
<%
            while (rs.next()){
%>
                    <tr>
                        <td class="success"><%=rs.getInt("id")%></td>
                        <td class="warning"><%=rs.getString("Statename")%></td>
                        <td class="danger"><%=rs.getString("addresslink")%></td>
                    </tr>
<%
            }
        }
        catch(Exception ex) {

        }
%>
                </tbody>
            </table>
        </div>
</body>
</html>
