/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mark
 */
public class MainDatabase {
        
      
        public  void createTable(String tableName){
        try {
            Connection connection =createConnection();
            Statement statement2 = connection.createStatement();
         
            statement2.executeUpdate("CREATE  TABLE IF NOT EXISTS `stock`.`"+tableName+"` (  `Per` VARCHAR(2) NULL ,  `Date` DATE NOT NULL ,  `Open` DECIMAL(7,3) NULL ,  `High` DECIMAL(7,3) NULL ,  `Low` DECIMAL(7,3) NULL ,  `Close` DECIMAL(7,3) NULL ,  `Volume` INT NULL ,  `o/i` INT NULL ,  PRIMARY KEY (`Date`) )");
       
         
            
            connection.close();
       
        } catch (SQLException ex) {
          System.out.println("create table"+ex);
        }
     
}
         public void insertTemporaryTable(String value1, String Value2){
        try {
            
               Connection connection =createConnection();
            Statement statement2 = connection.createStatement();
                
   PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `stock`.`tablename`(`longName`, `shortName`) VALUES (?,?)");
           
            pstmt.setString(1, value1);
         pstmt.setString(2, Value2);
                 pstmt.executeUpdate();
                 
                   connection.close();
        } catch (SQLException ex) {
          
        }
 }
         
             public ArrayList selectTemporary(){
        int indicator =0;
        ArrayList value= new ArrayList();
        try {
            
                      Connection connection =createConnection();
            Statement statement2 = connection.createStatement();
                    ResultSet resultset=statement2.executeQuery("select shortName from tableName");
                     while (resultset.next()){
                         value.add(resultset.getString(1));
                     }
                       connection.close();
                  return value;
        } catch (SQLException ex) {
         //   Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
 }
             
             
                    
             public int selectTemporary(String name){
        int indicator =0;
         System.out.println("select  ");
        try {
            
                      Connection connection =createConnection();
            Statement statement2 = connection.createStatement();
            System.out.println("select count(shortName) from tableName where shortName = '"+name+"';");
                    ResultSet resultset=statement2.executeQuery("select count(shortName) from tablename where shortName = '"+name+"';");
                   
                    while (resultset.next()){
                      indicator=resultset.getInt(1);
                     }
                    
                     connection.close();
                  return indicator;
        } catch (SQLException ex) {
         System.out.println(ex.toString());
        }
        
         System.out.println(indicator);
       return indicator;
 }
    public  Connection createConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "stock";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "1234";

            Class.forName(driver).newInstance();

            Connection connection = DriverManager.getConnection(url + dbName, userName, password);

            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
  System.out.println("create" +ex);
        }

        return null;
    }       
    
    
    
    
     public  void insertDatabase( String [] value,String date){
          
        try {
   Connection connection =createConnection();
         
            PreparedStatement pstmt = connection.prepareStatement("INSERT IGNORE INTO `stock`.`"+value[1]+"` (`Per`, `Date`, `Open`, `High`, `Low`, `Close`, `Volume`, `o/i`) VALUES (?,?,?,?, ?, ?, ?, ?)");
    
            
  
       
            pstmt.setString(1, value[2]);
            
                 pstmt.setString(2,date);
                 pstmt.setString(3, value[4]);
                 pstmt.setString(4, value[5]);
                 pstmt.setString(5, value[6]);
                 pstmt.setString(6, value[7]);
                 pstmt.setString(7, value[8]);
                 pstmt.setString(8, value[9]);
                
                 pstmt.executeUpdate();
                 
                 connection.close();
        } catch (SQLException ex) {
         
             
        System.out.println("insertt table"+ex);
           
        }
      }

public void loadDatabase(String fileName,String startdb){
        try {    
     Connection connection =createConnection();
            Statement statement2 = connection.createStatement();
                //       System.out.println("load data local infile 'data/"+fileName+"' into table '"+startdb+"' fields terminated by ',' lines terminated by '\n' ");
       
            statement2.executeUpdate("load data local infile 'data/"+fileName+"' into table `"+startdb+"` fields terminated by ','  lines terminated by '\n' ");
             //    System.out.println("load data local infile 'data/"+fileName+"' into table "+startdb+" fields terminated by ',' enclosed by '\"' lines terminated by '\n' ");
            connection.close();
        } catch (SQLException ex) {
          System.out.println(ex);
         //   Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 }     
}
