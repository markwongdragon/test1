/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mark
 */
public class Stock2 {
final static int size=1024;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
       
        System.out.println(dateFormat.format(cal.getTime()));
       //   cal.add(Calendar.DATE, -1);   
       for (int i =0;i<=300;i++){
              System.out.println("http://www.free88.org/free88trigger/DownloadFile.aspx?Exchange=KLSE&Filename=KLSE_"+dateFormat.format(cal.getTime())+".txt");
    
         fileDownload("http://www.free88.org/free88trigger/DownloadFile.aspx?Exchange=KLSE&Filename=KLSE_"+dateFormat.format(cal.getTime())+".txt","data");
           
         cal.add(Calendar.DATE, -1);   
     }
   }
    
    
    public static void fileDownload(String fAddress, String destinationDir)
{
 
  int slashIndex =fAddress.lastIndexOf('_');
int periodIndex =fAddress.lastIndexOf('.');

String fileName=fAddress.substring(slashIndex + 1);

if (periodIndex >=1 &&  slashIndex >= 0 && slashIndex < fAddress.length()-1)
{
fileUrl(fAddress,fileName,destinationDir);
}
else
{
System.err.println("path or file name.");
}}
    public static void fileUrl(String fAddress, String localFileName, String destinationDir) {
  
        
    
    
    
OutputStream outStream = null;
URLConnection  uCon = null;


InputStream is = null;
try {
URL Url;
byte[] buf;
int ByteRead,ByteWritten=0;
Url= new URL(fAddress);
outStream = new BufferedOutputStream(new
FileOutputStream(destinationDir+"\\"+"rdy.txt",true));

uCon = Url.openConnection();
is = uCon.getInputStream();
buf = new byte[size];
while ((ByteRead = is.read(buf)) != -1) {

   // System.out.println();
outStream.write(buf, 0, ByteRead);
ByteWritten += ByteRead;
}

 //processfile( destinationDir+"\\"+localFileName);

}
catch (Exception e) {
System.out.println("fAddress"+fAddress);
}
finally {
try {
   
    if (is != null){
is.close();
outStream.close();

    }
}
catch (IOException e) {
e.printStackTrace();
}}
    
}
    
      public static  void processfile(String FileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            String line;
            boolean first= false;
            while ((line = br.readLine()) != null) {
         
               String value[]=line.split(",");
                      if (value.length==10){
  if(first){
            String year=value[3].substring(0, 4);
                   String month=value[3].substring(4, 6);
                         String day=value[3].substring(6, 8);
        //ArrayList value2= new MainDatabase().selectTemporary();
        
       if (new MainDatabase().selectTemporary(value[1])==0){
            
            System.out.println(value[1]);
           new MainDatabase().createTable(value[1]);
      new MainDatabase().insertTemporaryTable(value[0],value[1]);
  }
           
               new MainDatabase().insertDatabase( value,year+"-"+month+"-"+day);
  }
            first=true;          
                      
                      }
            }} catch (IOException ex) {
                    System.out.println("processfile table"+ex);
        }
  }
  
      public static  boolean checkExist(ArrayList value, String name){
          boolean result=false;
          
          
          for (int i =0 ; i <value.size();i++){
              if (value.get(i).equals(name)){
                  result= true;
              }
          }
          
          System.out.println("checkExist     "+result);
          return result;
      }
      
}