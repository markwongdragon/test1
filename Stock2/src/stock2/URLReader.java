/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock2;

/**
 *
 * @author mark
 */
import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static stock2.Stock2.fileDownload;

public class URLReader {
    public static void main(String[] args) throws Exception {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
         PrintWriter out = null;
        System.out.println(dateFormat.format(cal.getTime()));
        // cal.add(Calendar.DATE, -4);   
       for (int i =0;i<=4750;i++){
      System.out.println(i);
        // fileDownload("http://www.free88.org/free88trigger/DownloadFile.aspx?Exchange=KLSE&Filename=KLSE_"+dateFormat.format(cal.getTime())+".txt","data");
              URL oracle = new URL("http://www.free88.org/free88trigger/DownloadFile.aspx?Exchange=KLSE&Filename=KLSE_"+dateFormat.format(cal.getTime())+".txt");
      
    
     

       BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
         
if (!inputLine.contains("<") && inputLine.contains(",")){
      String value[]=inputLine.split(",");
          String year=value[3].substring(0, 4);
                   String month=value[3].substring(4, 6);
                         String day=value[3].substring(6, 8);
      if(value.length==10 && dateFormat.format(cal.getTime()).equals(year+""+month+""+day)){
   
                         
        out= new PrintWriter(new BufferedWriter(new FileWriter("data/"+value[1]+".txt", true)));
                    out.write(value[2]+","+year+"-"+month+"-"+day+","+value[4]+","+value[5]+","+value[6]+","+value[7]+","+value[8]+","+value[9]+"\n");
                       out.close();
      }
}   
        }
             cal.add(Calendar.DATE, -1);   
      
        in.close();
    }
System.out.println("DOnE downlaod file");
        listFilesForFolder(folder);
        System.out.println("DOnE downlaod file");
    }
      public static File folder = new File("data/");
  static String temp = "";
      public static void listFilesForFolder(final File folder) {

    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
        listFilesForFolder(fileEntry);
      } else {
        if (fileEntry.isFile()) {
          temp = fileEntry.getName();
       
          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt")){
       String value[]=temp.split(".txt");
                new MainDatabase().createTable(value[0]);
                    new MainDatabase().loadDatabase(temp, value[0]);
         //   System.out.println("File= "+value[0]);
          }
        }

      }
    }
  }
    
         public static  void processfile(String FileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            String line;
            boolean first= false;
            while ((line = br.readLine()) != null) {
         
               String value[]=line.split(",");
                      if (value.length==10){
  
            String year=value[3].substring(0, 4);
                   String month=value[3].substring(4, 6);
                         String day=value[3].substring(6, 8);
        //ArrayList value2= new MainDatabase().selectTemporary();
        
       if (new MainDatabase().selectTemporary(value[1])==0){
            
       
           new MainDatabase().createTable(value[1]);
      new MainDatabase().insertTemporaryTable(value[0],value[1]);
  }
           
               new MainDatabase().insertDatabase( value,year+"-"+month+"-"+day);
  
            first=true;          
                      
                      }
            }} catch (IOException ex) {
                    System.out.println("processfile table"+ex);
        }
  }
}