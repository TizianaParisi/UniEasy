package com.mygroup.app.UniEasy;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	
	
	
	public static Connection getConnection(){
		
		String url = "jdbc:postgresql://localhost/gestioneesami";
	    String user = "postgres";
	    String password = "postgresql";
        Connection con = null;
        
        try {
            
            try {
				con = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
	

}
