<%-- 
    Document   : ViewCarsAndTrucks
    Created on : Oct 10, 2015, 1:59:20 AM
    Author     : Katangoori
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAO.DataBaseConnection"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    catch(Exception ex){
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
                        <li ><a href="viewcities.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cities</a></li>
                        <li class="active"><a href="viewcarsandtrucks.jsp"><span class="glyphicon glyphicon-eye-open"></span>View Cars and Trucks</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <script type="text/javascript">
            $(document).ready(function(){
                $(".updateCloudButton").click(function(){
                    $(".updateCloudButton").disabled;
                    $(".updateCloudButton").removeClass(".btn-default");
                    $(".updateCloudButton").addClass(".disabled");
                    $(".updateCloudButton").addClass(".btn-danger");
                    $(".notofication_Status").html("<div align=\"center\" ><h1 color=\"red\" align=\"center\">Please Wait Until Updated and Do not Refresh !</h1><img src=\"loading-2.gif\" style=\" width: 200px; height: 200px;\"></div>");
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
                        else{
                            $(".notofication_Status").html("<div align=\"center\" ><h1 color=\"red\" align=\"center\">we are Sorry Unable to connect to Server please check your Internet Connection</h1></div>");
                        }
                    });
                });
            });
        </script>
        <div class="notofication_Status" align="center" ></div>
    </body>
<%
            Statement stmt=DataBaseConnection.getStatement();
            ResultSet rs=null;
            String sqlquery="select * from carstruckslinks";
            try{
                rs=stmt.executeQuery(sqlquery);
%>
    <table class="table" align="center">
        <thead>
            <th class="active">ID</th>
            <th class="active">Cars And Trucks Links</th>
            <th class="active">City</th>
            <th class="active">City Link</th>
            <th class="active">State</th>
            <th class="active">State Link</th>
        </thead>
        <tbody>
<%
                while(rs.next()) {
%>
            <tr>
                <td class="success"><%=rs.getInt("id")%></td>
                <td class="danger"><a target="_blank" href="<%=rs.getString("carstrucksaddresslink")%>"><%=rs.getString("carstrucksaddresslink")%></a></td>
                <td class="warning"><%=rs.getString("cityname")%></td>
                <td class="danger"><a target="_blank" href="<%=rs.getString("cityaddresslink")%>"><%=rs.getString("cityaddresslink")%></a></td>
                <td class="warning"><%=rs.getString("statename")%></td>
                <td class="danger"><a target="_blank" href="<%=rs.getString("stateaddresslink")%>"><%=rs.getString("stateaddresslink")%></a></td>
            </tr>
<%
                }
            }
            catch(Exception ex) {
                        
            }
%>
        </tbody>
    </table>
</html>
