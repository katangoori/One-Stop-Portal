/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.DataBaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Katangoori
 */
public class SearchEngineHelper {
    
    public static String makeHtmlCode(ArrayList<String> hm) {
     String html="";
        try{
            for(int i=0;i<hm.size();i++){
                System.out.println(hm.get(i).toString());
            }
            for(int i=0;i<hm.size();i++){
                //System.out.println("called");
                Connection connection=DataBaseConnection.getDataBaseConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select id,images,postingtitletext,price,Timing_of_Listing,cityname from carstrucksdetails where id="+hm.get(i).toString()+"");
                while(resultSet.next()){
                    String src=ImagesSavingEngine.getImageSplitURL(resultSet.getString("images"));
                    if(src.equalsIgnoreCase("images/null" )){
                        html+="<div class='col-lg-3 address-car-link' style='float: left; margin: 10px;'>" +
                "           <a style='text-decoration: none; color: white;' href='#' value='"+resultSet.getInt(1)+"' class='address-car-link'><br>" +
                "               <h3 value='"+resultSet.getInt(1)+"' style='width: 350px;'><span value='"+resultSet.getInt(1)+"'>"+resultSet.getString("postingtitletext")+" - "+resultSet.getString("price")+"<br>Time:"+resultSet.getString("Timing_of_Listing")+" - ("+resultSet.getString("cityname")+")</span></h3></a>" +
                "                   </div>";                          
                    }
                    else {
                        html+="<div class='col-lg-3 address-car-link' style='float: left; margin: 10px;'>" +
                "           <a style='text-decoration: none; color: white;' href='#' value='"+resultSet.getInt(1)+" class='address-car-link'> <img value='"+resultSet.getInt(1)+" src='"+ImagesSavingEngine.getImageSplitURL(resultSet.getString("images"))+"' class='images-caresultSet' style='width: 375px; height: 375px'><br>" +
                "               <h3 value='"+resultSet.getInt(1)+"' style='width: 350px;'><span value='"+resultSet.getInt(1)+"'>"+resultSet.getString("postingtitletext")+" - "+resultSet.getString("price")+"<br>Time:"+resultSet.getString("Timing_of_Listing")+" - ("+resultSet.getString("cityname")+")</span></h3></a>" +
                "                   </div>";                              
                    }
                }
                resultSet.close();statement.close();connection.close();
               // System.out.println(html);                
            }            
            return html;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public static String getCommonContent(String searchQuery,String state,String city) {
        String searchHtml="";
        CharSequence searchQuery_cCharSequence=searchQuery;
        //System.out.println(searchQuery+" "+" "+state+" "+city);
        int i=0;        
        try{        
            Connection connection=DataBaseConnection.getDataBaseConnection();
            Statement statement=connection.createStatement();
            //System.out.println(searchQuery);
            ArrayList<String> hm=new ArrayList<String>(); 
            //System.out.println(hm.size());
            if(state.equalsIgnoreCase("none")){                
                if(searchQuery.contains(" ")){
                    //System.out.println("state=none");
                    String searchHtmlSplit[]=searchQuery.split(" ");
                    for(i=1;i<searchHtmlSplit.length;i++){
                        searchQuery_cCharSequence=searchHtmlSplit[i];
                        ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails");
                        while(resultSet.next()){
                            if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("postingtitletext"))){
                                //System.out.println("1 at if "+resultSet.getString("id"));
                                hm.add(resultSet.getString("id"));
                            }
                            else if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("description"))){
                                //System.out.println("2 at if "+resultSet.getString("id"));
                                hm.add(resultSet.getString("id"));
                            }
                        }
                        searchHtml=searchHtml+makeHtmlCode(hm);
                    }
                    return searchHtml;
                }
                else if(searchQuery_cCharSequence.length()>0){
                    ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails");
                    while(resultSet.next()){
                        //System.out.println(searchQuery_cCharSequence+" \n"+resultSet.getString("postingtitletext"));
                        //System.out.println(resultSet.getString("postingtitletext"));
                        if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("postingtitletext"))){
                            //System.out.println("true"+ resultSet.getString("id"));
                            System.out.println("3 at if "+resultSet.getString("id"));
                            hm.add(resultSet.getString("id"));
                        }
                        else if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("description"))){
                            //System.out.println("true"+ resultSet.getString("id"));
                            //System.out.println("4 at if "+resultSet.getString("id"));
                            hm.add(resultSet.getString("id"));
                        }
                    }
                    searchHtml=searchHtml+makeHtmlCode(hm);
                    //System.out.println(searchHtml);
                    return searchHtml;                        
                }
                else{
                    //System.out.println("helloooooooooo");
                    return searchHtml;
                }
            }
            else if(city.equalsIgnoreCase("none")){
                if(searchQuery.contains(" ")){
                    //System.out.println("state=none");
                    String searchHtmlSplit[]=searchQuery.split(" ");
                    for(i=1;i<searchHtmlSplit.length;i++){
                        searchQuery_cCharSequence=searchHtmlSplit[i];
                        ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails where statename='"+state+"'");
                        while(resultSet.next()){
                            if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("postingtitletext"))){
                                //System.out.println("1 at if "+resultSet.getString("id"));
                                hm.add(resultSet.getString("id"));
                            }
                            else if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("description"))){
                                //System.out.println("2 at if "+resultSet.getString("id"));
                                hm.add(resultSet.getString("id"));
                            }
                        }
                        searchHtml=searchHtml+makeHtmlCode(hm);
                    }
                    return searchHtml;
                }
                else if(searchQuery_cCharSequence.length()>0){
                    ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails where statename='"+state+"'");
                    while(resultSet.next()){
                        //System.out.println(searchQuery_cCharSequence+" \n"+resultSet.getString("postingtitletext"));
                        //System.out.println(resultSet.getString("postingtitletext"));
                        if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("postingtitletext"))){
                            //System.out.println("true"+ resultSet.getString("id"));
                            System.out.println("3 at if "+resultSet.getString("id"));
                            hm.add(resultSet.getString("id"));
                        }
                        else if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("description"))){
                            //System.out.println("true"+ resultSet.getString("id"));
                            //System.out.println("4 at if "+resultSet.getString("id"));
                            hm.add(resultSet.getString("id"));
                        }
                    }                        
                    searchHtml=searchHtml+makeHtmlCode(hm);
                    //System.out.println(searchHtml);
                    return searchHtml;                        
                }
                else{
                    //System.out.println("helloooooooooo");
                    return searchHtml;
                }                
            }
            else if(searchQuery.length()==0){
                for(i=0;i<12;i++){
                hm.add(""+i);                    
                }
                searchHtml+=makeHtmlCode(hm);
            }
            else{
                try{                                    
                    if(searchQuery.contains(" ")){
                        //System.out.println("state=none");
                        String searchHtmlSplit[]=searchQuery.split(" ");
                        for(i=1;i<searchHtmlSplit.length;i++){
                            searchQuery_cCharSequence=searchHtmlSplit[i];
                            ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails where statename='"+state+"' and cityname='"+city+"'");
                            while(resultSet.next()){
                                if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("postingtitletext"))){
                                    //System.out.println("1 at if "+resultSet.getString("id"));
                                    hm.add(resultSet.getString("id"));
                                }
                                else if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("description"))){
                                    //System.out.println("2 at if "+resultSet.getString("id"));
                                    hm.add(resultSet.getString("id"));
                                }
                            }
                            searchHtml=searchHtml+makeHtmlCode(hm);
                        }
                        return searchHtml;
                    }
                    else if(searchQuery_cCharSequence.length()>0){
                        ResultSet resultSet=statement.executeQuery("select * from carstrucksdetails");
                        while(resultSet.next()){
                            //System.out.println(searchQuery_cCharSequence+" \n"+resultSet.getString("postingtitletext"));
                            //System.out.println(resultSet.getString("postingtitletext"));
                            if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("postingtitletext"))){
                                //System.out.println("true"+ resultSet.getString("id"));
                                System.out.println("3 at if "+resultSet.getString("id"));
                                hm.add(resultSet.getString("id"));
                            }
                            else if(getHelpSearchQueryContains(searchQuery_cCharSequence.toString(), resultSet.getString("description"))){
                                //System.out.println("true"+ resultSet.getString("id"));
                                //System.out.println("4 at if "+resultSet.getString("id"));
                                hm.add(resultSet.getString("id"));
                            }
                        }
                        searchHtml=searchHtml+makeHtmlCode(hm);
                        //System.out.println(searchHtml);
                        return searchHtml;                        
                    }
                    else{
                        //System.out.println("helloooooooooo");
                        return searchHtml;
                    }
                }
                catch(Exception e){
                    
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  searchHtml;
    }
    
    public static Boolean getHelpSearchQueryContains(String searchQuery,String data) {
        ArrayList<String> dataSplit=new ArrayList<String>();
        try{
            String dataSplitContent[]=data.split(" ");
            dataSplit.addAll(Arrays.asList(dataSplitContent));
            for(String d: dataSplit){
                if(d.toLowerCase().contains(d.toLowerCase())){
                    System.out.println("Matched"+d);
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
