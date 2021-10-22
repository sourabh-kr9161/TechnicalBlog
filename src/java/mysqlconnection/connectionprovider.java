/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class connectionprovider 
{
     static Connection con=null;
   private  connectionprovider()
   {
       
   }
    
    public static Connection getConnection()
    {
        if(con==null)
        {
             try
        {
        Class.forName(Dbconnectiondata.DATABASE_DRIVER);
        System.out.println("Driver Loaded");
        con = DriverManager.getConnection(Dbconnectiondata.DATABASE_URL,Dbconnectiondata.DATABASE_USER,Dbconnectiondata.DATABASE_PASS);
        System.out.println("connection stablish");
      
          //  con.close();
            
        }
catch(Exception e)
{
    e.printStackTrace();
}
          return con;
    
    }
        // return null;
        return con;
}
}
