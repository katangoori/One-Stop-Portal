/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Katangoori
 */
public class ImagesSavingEngine {
    public static void main(String a[]) throws IOException {
        URL url;
        try {
            System.out.println("Just Wait");
            url = new URL("http://www.kcc.edu/campaigns/PublishingImages/poh.jpg");
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "jpg",new File("web\\images\\HelloNewImage__.jpg"));
            System.out.println("Completed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String saveImages(String addresslink,String imageAddresslink,String path) {
        String storedAddress="",commonUrl="";
        if(imageAddresslink != null && addresslink!=null) {
            BufferedImage image = null;
            String pathModify[]=addresslink.split(".html");
            pathModify=pathModify[0].split("/");
            try {
                String imageUrls[]=imageAddresslink.split("@@@");
                for(int i=0;i<imageUrls.length;i++) {
                    URL url = new URL(imageUrls[i]);
                    image = ImageIO.read(url);
                    commonUrl="images/"+pathModify[pathModify.length-1]+"__"+i+".jpg";
                    System.out.println(commonUrl);
                    ImageIO.write(image, "jpg",new File("web\\images\\"+pathModify[pathModify.length-1]+"__"+i+".jpg"));
                    storedAddress=storedAddress+commonUrl+"@@@";
                }
            }
            catch (IOException e) {
                
            }
        }
        return storedAddress;
    }
    
    public static String getImageSplitURL(String url) {
        try {
            if(url.length()>4) {
                String splitURL[]=url.split("@@@");
              splitURL=splitURL[0].split("images",2);
              url=splitURL[1].substring(1);       
              return "images/"+url;
            }
            else {
                return "images/null";
            }
        }
        catch(Exception e) {
            return "images/null";
        }
    }
    
    public static  String getImageSplitURLs(String url) {
        try {
            if(url.length()>4) {
                String splitURL[]=url.split("@@@");
              splitURL=splitURL[0].split("images");
              String newURL="";
              for(int i=0;i<splitURL.length;i++) {
                  url=splitURL[i].substring(1);
                  newURL+="images/"+url+"@";
              }
              return newURL;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }
}
