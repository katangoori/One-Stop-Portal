<%-- 
    Document   : Home
    Created on : Oct 2, 2015, 6:30:17 PM
    Author     : sivad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG_Project</title>
        <h1>Dengai</h1>
    </head>
    <body>
<%  try{
        String emailID= (String)session.getAttribute("emailID");
        if(emailID==null){
            response.sendRedirect("login.jsp");
            session.invalidate();
        }
    }
    catch(Exception e) {
        
    }
%>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">SENG_Project</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="home.jsp">
                                <span class="glyphicon glyphicon-home">
                                </span>Home
                            </a>
                        </li>
                        <li id="uploadcloud" style="padding: 7px;">
                            <span class="glyphicon glyphicon-cloud">
                            </span>
                            <button class="btn btn-default updateCloudButton">Update DataBase Cloud</button>
                        </li>
                        <li>
                            <a href="viewstates.jsp">
                                <span class="glyphicon glyphicon-eye-open">
                                    
                                </span>View States
                            </a>
                        </li>
                        <li>
                            <a href="viewcities.jsp">
                                <span class="glyphicon glyphicon-eye-open">
                                    
                                </span>View Cities
                            </a>
                        </li>
                        <li>
                            <a href="viewcarsandtrucks.jsp">
                                <span class="glyphicon glyphicon-eye-open">
                                    
                                </span>View Cars and Trucks
                            </a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="logout.jsp">
                                <span class="glyphicon glyphicon-log-out">
                                    
                                </span> Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <script type="text/javascript">
    $(document).ready(function() {
        $(".updateCloudButton").click(function() {
            $(".updateCloudButton").prop('disabled',true);
            $(".updateCloudButton").removeClass(".btn-default");
            $(".updateCloudButton").addClass(".btn-danger");
            $(".notification_status").html("<div align=\"center\"><h1 color=\"red\" align=\"center\">Processing</h1></div>");
            $.post("updatecloud.html", {
                update_cloud: "update"
            },
            function(data,status) {
                if (status==="success") {
                    $(".notification_status").html(data);
                    $(".updateCloudButton").removeClass(".btn-default");
                    $(".updateCloudButton").prop('disabled',false);
                    $(".updateCloudButton").removeClass(".btn-danger");
                    $(".updateCloudButton").addClass(".btn-success");
                }
                else {
                    $(".notification_status").html("<div align=\"center\"><h1 color=\"red\" align=\"center\">Error:Unable to connect, Try Again! </h1></div>");
                }
            });
        });
    });
                    </script>
                    <div class="notification_status" align="center"></div>
    </body>
</html>
