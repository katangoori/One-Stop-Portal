<%-- 
    Document   : Search
    Created on : Nov 13, 2015, 9:56:44 PM
    Author     : Katangoori
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="DAO.DataBaseConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="Service.ImagesSavingEngine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG_Project</title>
        <script>
            $(document).ready(function() {    
                $(".address-car-link").click(function() {
                    $.post("getAllDetails.jsp",
                    {
                        id : $(".address-car-link").val()
                    },
                    function(data,status) {
                        $(".content-cars-setting").html(data);
                    });
                });
           
                $(".state").change(function(){
                    $.post("getCities.html",
                    {
                        state : $(".state").val()
                    },
                    function(data,status){
                        $(".city").html(data);
                    });
                });
                
                $(".state").change(function(){
                    $.post("getSearchContent.html",
                    {
                        state : $(".state").val(),
                        city : $(".city").val(),
                        query : $(".search-box").val()
                    },
                    function(data,status){
                        $(".content-cars-setting").html(data);
                    });
                });
                
                $(".city").change(function(){
                    $.post("getSearchContent.html",
                    {
                        state : $(".state").val(),
                        city : $(".city").val(),
                        query : $(".search-box").val()
                    },
                    function(data,status){
                        $(".content-cars-setting").html(data);
                    });
                });
                
                $(".search-box").keyup(function(){
                    $.post("getSearchContent.html",
                    {
                        state : $(".state").val(),
                        city : $(".city").val(),
                        query : $(".search-box").val()
                    },
                    function(data,status){
                        $(".content-cars-setting").html(data);
                    });
                });
            });
        </script>
    </head>
    <body>
<%
        Connection c=DataBaseConnection.getDataBaseConnection();
        Statement s=c.createStatement();
        ResultSet rs=s.executeQuery("select statename from states");
%>
        <div class="container" style="padding-left: 100px; padding-top: 50px;">
            <div class="row" >
                <div class="col-lg-3">
                    <table  style="color: whitesmoke; font-size: 15px; font-family: sans-serif; font-weight: bolder; padding-left: 150px;">
                        <tr>
                            <td>State:</td>
                            <td>
                                <select class="state" name="state">
                                    <option value="none">---Select State---</option>
<%
        while(rs.next()) {
%>
                                        <option value="<%=rs.getString("statename")%>"><%=rs.getString("statename")%></option>
<%
        }
%>
                                </select>
                            </td>
                            <td>City:</td>
                            <td>
                                <select class="city" name="city">
                                    <option value="none">---Select City---</option>
                                </select>
                            </td>
                            <td>
                                <form id="custom-search-form" class="form-search form-horizontal pull-right">
                                    <div class="input-append span12">
                                        <input type="text" class="search-box" name="search-query" class="search-query mac-style" placeholder="Search">
                                    </div>
                                </form>
                            </td>
                            <td>
                                <a href="login.jsp"><button class="btn btn-primary" >Login</button></a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row content-cars-setting" style="color: white; margin-top: 50px;">
<%
        rs=s.executeQuery("select id,images,postingtitletext,price,Timing_of_Listing,cityname from carstrucksdetails");
        int i=1;
        while(rs.next() && i<=12) {
            try {
                String src=ImagesSavingEngine.getImageSplitURL(rs.getString("images"));
                if(src.equalsIgnoreCase("images/null" )) {
                        
                }
                else {
%>
                <div class="col-lg-3" style="float: left; margin: 10px;">
                    <a style="text-decoration: none; color: white;" href="#" value="<%=rs.getInt(1)%>" class="address-car-link"><img value="<%=rs.getInt(1)%>" src="<%=ImagesSavingEngine.getImageSplitURL(rs.getString("images"))%>" class="images-cars" style="width: 375px; height: 375px"/><br>
                    <h3 value="<%=rs.getInt(1)%>" style="width: 350px;"><span value="<%=rs.getInt(1)%>"><%=rs.getString("postingtitletext")%> - <%=rs.getString("price")%><br>Time:<%=rs.getString("Timing_of_Listing")%> - (<%=rs.getString("cityname")%>)</span></h3></a>
                </div>
<%
                    i++;
                }
            }   
            catch(Exception e) {
                          
            }
        }
        rs.close();s.close();c.close();
%>
            </div>
        </div>
    </body>
</html> 
