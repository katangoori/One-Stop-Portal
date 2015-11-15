/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.DataBaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author Katangoori
 */
public class HtmlParser {
    public static void main(String a[]) throws IOException {
//        System.out.println("fetching data please wait until success it may take a while ");
//        Elements links=new HtmlParser().getStatesAndLinks(); 
//        new HtmlParser().saveStatesAndLinks(links);
//        new HtmlParser().saveCitiesLinks();
//        new HtmlParser().getCarsAndTrucsLinks();
//        System.out.println("seccuess fully loaded and inserted");
    }
    
    public Elements getStatesAndLinks() throws IOException {
        Document html=Jsoup.connect("http://eastnc.craigslist.org/").get();
        String menu=html.body().getElementsByClass("menu").html();
        String ul[]=menu.split("<h5 class=\"ban\">us states</h5>",2);
        for(int i=0;i<ul.length;i++ ) {
            //System.out.println("\n\n\n"+i+"\n\n"+ul[i]+"\n\n\n");
        }
        ul=ul[1].split("</ul> </li>",2);
        for(int i=0;i<ul.length;i++ ) {
            //System.out.println("\n\n\n"+i+"\n\n"+ul[i]+"\n\n\n");
        }
        Document uss=Jsoup.parse(ul[0]);
        Elements links = uss.select("a[href]");
        return links;
    }
    
    public void saveStates() {
        try {
            Elements links=new HtmlParser().getStatesAndLinks();
            new HtmlParser().saveStatesAndLinks(links);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveStatesAndLinks(Elements links) {
        try {
            String insertstates="truncate table states;";         
            Statement statement=DataBaseConnection.getStatement();
            statement.executeUpdate(insertstates);
            int i=0;  
            String addresslink,formattedlink[];
            for (Element link : links) {
                addresslink=link.attr("href");
                formattedlink=addresslink.split("//", 2);
                if(!formattedlink[1].endsWith("craigslist.org")){
                insertstates="insert into states(statename,addresslink) values('"+link.text()+"','http://"+formattedlink[1]+"');";
                //System.out.println(insertstates);
                try {
                    i=statement.executeUpdate(insertstates);
                }
                catch (SQLException ex) {
                    //ex.printStackTrace();
                }
                //System.out.println("\nlink : http://" + formattedlink[1]);  
                //System.out.println("text : " + link.text()); 
                //System.out.println("inserted success fully" );
                }
            }
        }
        catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }
    
    public void saveCitiesLinks() {
        Document html;
        Statement statement=DataBaseConnection.getStatement();
        Statement statement1=DataBaseConnection.getStatement();
        Connection connection=DataBaseConnection.getDataBaseConnection();
        ResultSet resultSet;
        try {
            int i=0;
            String selectQuery="select * from states";
            String insertQuery="truncate table cities";
            i=statement1.executeUpdate(insertQuery);
            resultSet=statement.executeQuery(selectQuery);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into cities(cityname,cityaddresslink,statename,stateaddresslink) values(?,?,?,?)");
            //System.out.println("fetching data please wait until success ");
            while (resultSet.next()) {
                try {
                    html = Jsoup.connect(resultSet.getString("addresslink")).get();
                    String list=html.body().getElementById("list").html();
                    if (list!=null) {
                        Document cities=Jsoup.parse(list);
                        Elements links = cities.select("a[href]"); 
                        if(links!=null)
                        for (Element link : links) {
                            preparedStatement.setString(1, link.text());
                            preparedStatement.setString(2, link.attr("href"));
                            preparedStatement.setString(3, resultSet.getString("statename"));
                            preparedStatement.setString(4, resultSet.getString("addresslink"));
                            preparedStatement.executeUpdate();
//                            insertQuery="insert into cities(cityname,cityaddresslink,statename,stateaddresslink) values('"+link.text()+"','"+link.attr("href")+"','"+resultSet.getString("statename")+"','"+resultSet.getString("addresslink")+"')";
//                            i=statement1.executeUpdate(insertQuery);
//                            System.out.println("old link : " + resultSet.getString("addresslink"));  
//                            System.out.println("statename : " + resultSet.getString("statename"));
//                            System.out.println("link : " + link.attr("href"));  
//                            System.out.println("city name : " + link.text());
                        }
                    }
                }
                catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }
        }
        catch(NullPointerException ex) {
            //ex.printStackTrace();
        }
        catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }
    
    public void getCarsAndTrucsLinks() {
        Statement statement=DataBaseConnection.getStatement();
        Statement statement1=DataBaseConnection.getStatement();
        Connection connection=DataBaseConnection.getDataBaseConnection();
        try {
            int c=statement1.executeUpdate("truncate table carstruckslinks");
        }
        catch (SQLException ex) {
            
        }
        ResultSet resultSet;
        Document html;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("insert into carstruckslinks(carstrucksaddresslink,cityname,cityaddresslink,statename,stateaddresslink) values(?,?,?,?,?)");
            int i=0;
            String selectQuery="select * from cities";
            //String insertQuery="truncate table carstrucs";
            //i=statement1.executeUpdate(insertQuery);
            resultSet=statement.executeQuery(selectQuery);
            String cityaddresslink="";
            int j=1;
            while (resultSet.next()) {
                try {
                    cityaddresslink=resultSet.getString("cityaddresslink");
                    String cityname=resultSet.getString("cityname");
                    String statename=resultSet.getString("statename");
                    String stateaddresslink=resultSet.getString("stateaddresslink");
                    //System.out.println("\n\n\ncityaddresslink :"+cityaddresslink+"\n\n\n");
                    html = Jsoup.connect(cityaddresslink+"/search/cta").get();
                    String carstrucks=html.body().getElementsByClass("row").html();
                    String totalcounts[];
                    String total=html.body().getElementsByClass("totalcount").html();
                    //System.out.println("total String"+total);
                    totalcounts=total.split("\n", 2);
                    //System.out.println("total counts string="+totalcounts[0]+"\nlength="+totalcounts[0].length());
                    int totalcarstrucks=Integer.parseInt(totalcounts[0]);
                    //System.out.println("totalcarstrucks value"+totalcarstrucks);
                    if(carstrucks!=null) {
                        //System.out.println("\n\n\n"+totalcarstrucks+"\n\n\n");                        
                        Document carstruckslinks=Jsoup.parse(carstrucks);
                        Elements links = carstruckslinks.getElementsByClass("hdrlnk");
                        if (links!=null) {
                            for (Element link : links) {
                                preparedStatement.setString(1, cityaddresslink+ link.attr("href"));
                                preparedStatement.setString(2, cityname);
                                preparedStatement.setString(3, cityaddresslink);
                                preparedStatement.setString(4, statename);
                                preparedStatement.setString(5,stateaddresslink);
                                preparedStatement.executeUpdate();
                                //System.out.println(j);j++;
                                //System.out.println("\nlink : " +cityaddresslink+ link.attr("href"));  
                                //System.out.println("data-id : " + link.attr("data-id"));
                            }
                            int count=100;
                            while(count<totalcarstrucks) {
                                try {
                                    //System.out.println("\n\n\ncityaddresslink+\"/search/cta?s=\"+count link:"+cityaddresslink+"/search/cta?s="+count);
                                    html = Jsoup.connect(cityaddresslink+"/search/cta?s="+count).get();
                                    carstrucks=html.body().getElementsByClass("row").html();
                                    if (carstrucks!=null) {
                                        carstruckslinks=Jsoup.parse(carstrucks);
                                        links = carstruckslinks.getElementsByClass("hdrlnk");
                                        if (links!=null) {
                                            for (Element link : links) {
                                                //System.out.println(j);j++;
                                                preparedStatement.setString(1, cityaddresslink+ link.attr("href"));
                                                preparedStatement.setString(2, cityname);
                                                preparedStatement.setString(3, cityaddresslink);
                                                preparedStatement.setString(4, statename);
                                                preparedStatement.setString(5, stateaddresslink);
                                                preparedStatement.executeUpdate();
                                            }
                                        }
                                    }
                                    count=count+100;
                                }
                                catch(Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            j=0;
                        }
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            preparedStatement.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void saveCarsTrucksdetails(String path) {
        try {
            System.out.println("Stated Working to save CarsTrucksdetails in DB");
            Connection connection=DataBaseConnection.getDataBaseConnection();
            Statement statement=connection.createStatement();
            String query="select * from carstruckslinks";
            ResultSet resultSet=statement.executeQuery(query);
            String sql="postingtitletext,price,images,description,Timing_of_Listing,car_condition,fuel,odometer,title_status,";
            sql=sql+"transmission,cylinders,drive,paint_color,car_type,year_manufacture,make,model,Vin,address,longitude,";
            sql=sql+"latitude,name,mobile,email,carstrucksaddresslink,cityname,cityaddresslink,statename,state_link";
            query="insert into carstrucksdetails("+sql+") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            while(resultSet.next()) {
                try {
                    Document html=Jsoup.connect(resultSet.getString("carstrucksaddresslink")).timeout(1000*1000).get();
                    //1 postingtitletext
                    System.out.println(resultSet.getString("carstrucksaddresslink"));
                    String postingtitletext=html.body().getElementsByClass("postingtitletext").html();
                    String postingtitletext1[]=new String[2];
                    postingtitletext1=postingtitletext.split(" - ",2);
                    preparedStatement.setString(1, postingtitletext1[0].trim());
                    //2 price
                    String price=html.body().getElementsByClass("price").text().trim();
                    preparedStatement.setString(2, price);                
                    //3 images   
                    String images=null;
                    try {
                        images=html.body().getElementById("thumbs").html();
                    }
                    catch(NullPointerException n) {
                        System.out.println("No Images");
                        images=null;
                    }
                    if (images!=null) {
                        Document images_html=Jsoup.parse(images);
                        Elements links =images_html.getElementsByTag("a");
                        images="";
                        if(links!=null) {
                            for (Element link : links) {
                                images=images+link.attr("href")+"@@@";
                                //@@@ is used to separator for downloading images by split("@@@")
                            }
                            images=ImagesSavingEngine.saveImages(resultSet.getString("carstrucksaddresslink"), images,path);
                            System.out.println(images);
                        }
                        preparedStatement.setString(3, images);
                    }
                    else {
                        preparedStatement.setString(3, null);
                    }
                    //4 description
                    String postingbody="";
                    try {
                        postingbody=html.body().getElementById("postingbody").text().trim();
                    }
                    catch(NullPointerException n) {
                        System.out.println("Null");
                    }
                    finally {
                        preparedStatement.setString(4, postingbody);
                    }
                    //5 Timing_of_Listing Time
                    String Timing_of_Listing=html.body().getElementById("display-date").html();
                    Document Timinng_of_Listing_Document=Jsoup.parse(Timing_of_Listing);
                    Timing_of_Listing=Timinng_of_Listing_Document.body().getElementsByTag("time").text();
                    preparedStatement.setString(5, Timing_of_Listing);
                    String attrgroup=html.body().getElementsByClass("attrgroup").html();
                    Document attrgroupDocument=Jsoup.parse(attrgroup);
                    attrgroup=attrgroupDocument.body().getElementsByTag("span").html();
                    String attrgroup_Arr[]=null;
                    attrgroup_Arr=attrgroup.split("\n");
                    for(int j=6;j<=19;j++) {
                        preparedStatement.setString(j, null);
                    }
                    for(int i=0;i<attrgroup_Arr.length;i++) {
                        if(attrgroup_Arr[i].contains("condition")) {
                            preparedStatement.setString(6, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());
                        }
                        if(attrgroup_Arr[i].contains("fuel")){
                            preparedStatement.setString(7, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("odometer")){
                            preparedStatement.setString(8, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("title status")){
                            preparedStatement.setString(9, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("transmission")){
                            preparedStatement.setString(10, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("cylinders")){
                            preparedStatement.setString(11, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("drive")){
                            preparedStatement.setString(12, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("paint color")){
                            preparedStatement.setString(13, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("type")){
                            preparedStatement.setString(14, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("year")){
                            preparedStatement.setString(15, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                           
                        }
			if(attrgroup_Arr[i].contains("make")){
                            preparedStatement.setString(16, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("model")){
                            preparedStatement.setString(17, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("vin")){
                            preparedStatement.setString(18, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
			if(attrgroup_Arr[i].contains("address")){
                            preparedStatement.setString(19, Jsoup.parse(attrgroup_Arr[i]).body().getElementsByTag("b").text());                            
                        }
                    }
                    String longitude=null,lotitude=null;
                    try {
                        longitude=html.body().getElementById("map").attr("data-latitude");
                        lotitude=html.body().getElementById("map").attr("data-longitude");
                    }
                    catch(NullPointerException e) {
                        longitude=null;lotitude=null;
                    }
                    finally {
                        preparedStatement.setString(20, longitude);
                        preparedStatement.setString(21, lotitude);
                    }
                    String email=null,mobile=null,name=null,contact_content=null,con=null;Document contact;
                    try {
                        String urlsplit[]=resultSet.getString("carstrucksaddresslink").split("/");
                        contact=Jsoup.connect(urlsplit[0]+"//"+urlsplit[1]+urlsplit[2]+html.body().getElementById("replylink").attr("href")).get();
                        contact_content=contact.body().getElementsByClass("reply_options").html();
                        con=Jsoup.parse(contact_content).getElementsByTag("li").html();
                        urlsplit=con.split("\n"); 
                        try {
                            name=urlsplit[0];
                        }
                        catch(Exception e) {
                            
                        }
                        try {
                            mobile=urlsplit[1];
                        }
                        catch(Exception e) {
                            
                        }
                        try {
                            email =contact.body().getElementsByClass("anonemail").text();
                        }
                        catch(Exception e) {
                            
                        }
                    }
                    catch(Exception e) {
                        
                    }
                    finally {
                        preparedStatement.setString(22, name);
                        preparedStatement.setString(23, mobile);
                        preparedStatement.setString(24, email);
                    }
                    preparedStatement.setString(25, resultSet.getString("carstrucksaddresslink"));
                    preparedStatement.setString(26, resultSet.getString("cityname"));
                    preparedStatement.setString(27, resultSet.getString("cityaddresslink"));
                    preparedStatement.setString(28, resultSet.getString("statename"));
                    preparedStatement.setString(29, resultSet.getString("stateaddresslink"));
                    int executeUpdate = preparedStatement.executeUpdate();
                }
                catch (Exception e) {
                    //e.printStackTrace();
                    //System.out.println("This posting has been deleted by its author.");
                }
            }
        }
        catch(Exception e) {
            //e.printStackTrace();
        }
    }
}
