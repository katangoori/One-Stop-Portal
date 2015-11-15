<%-- 
    Document   : GetAllDetails
    Created on : Nov 7, 2015, 5:34:37 PM
    Author     : Katangoori
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="DAO.DataBaseConnection"%>
<%@page import="Service.ImagesSavingEngine"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try {
        Connection connection= DataBaseConnection.getDataBaseConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails where id=1"+request.getParameter("id"));
        HashMap<Integer,String> dbdetails=new HashMap<Integer, String>();
        HashMap<Integer,String> codedetails=new HashMap<Integer, String>();
        String columnNames[]={"id","postingtitletext","price","images","description","Timing_of_Listing","car_condition","fuel","odometer","title_status","transmission","cylinders","drive","paint_color","car_type","year_manufacture","make","model","Vin","address","longitude","latitude","name","mobile","email","carstrucksaddresslink","cityname","cityaddresslink","statename","state_link"};
        while(resultSet.next()){
            for(int i=0,k=0;i<30;i++){                    
                try{
                    if(resultSet.getString(columnNames[i])!=null){
                        dbdetails.put(k, resultSet.getString(columnNames[i]));
                        codedetails.put(k, columnNames[i]);
                        k++;
                    }                        
                }
                catch(NullPointerException e){
                    e.printStackTrace();
                }
            }
        }
%>
<div class="container">
    <div class="row">
        <h1 style="color: whitesmoke;"></h1>
    </div>
    <div class="row" style="padding-left: 100px;">
<%
        for(int i=0;i<dbdetails.size();i++) {
            if(codedetails.get(i).equalsIgnoreCase("postingtitletext") ) {
%>
        <h2><%=dbdetails.get(i)%></h2>
<%          }
        }
%>
    </div>
    <div class="row">
<% 
        for(int i=0;i<dbdetails.size();i++){
            if(codedetails.get(i).equalsIgnoreCase("images")){
                String al=dbdetails.get(i);
%>
        <div class="row">
            <div class="col-lg-2" align="center"><img src="<%= ImagesSavingEngine.getImageSplitURL(al)%>"></div>
        </div>
<%
            }
        }
%>
        <div class="col-lg-6" style="float: left;"></div>
        <div class="col-lg-2" style="float: left; width: 100px;"></div>&nbsp;
        <div class="col-lg-4" style="float: left; padding-left: 100px;">
            <table>
<%
        for(int i=0;i<dbdetails.size();i++) {
            if(codedetails.get(i).equalsIgnoreCase("id") ||codedetails.get(i).equalsIgnoreCase("postingtitletext") || codedetails.get(i).equalsIgnoreCase("images")||codedetails.get(i).equalsIgnoreCase("images") ||codedetails.get(i).equalsIgnoreCase("description")||codedetails.get(i).equalsIgnoreCase("carstrucksaddresslink")||codedetails.get(i).equalsIgnoreCase("cityaddresslink")||codedetails.get(i).equalsIgnoreCase("state_link")) {
            }
            else {                            
%>
                <tr>
                    <td><b><%=codedetails.get(i).toUpperCase()%> : </b></td>
                    <td><%=dbdetails.get(i).replace("?"," ")%></td>
                </tr>
<%
            }
        }
%>                
            </table>
        </div>
    </div><br>
    <div class="row" style="float: left;">
        <div class="col-lg-10">
<%
        for(int i=0;i<dbdetails.size();i++) {
            if(codedetails.get(i).equalsIgnoreCase("description") ){
%>
            <p style="width: 800px;"><%=dbdetails.get(i)%></p>
<%
            }
        }
%>
        </div>
    </div>
</div>
<%
    }
    catch(Exception e) {
        e.printStackTrace();
    }
%>
