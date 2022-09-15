package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;


import org.junit.jupiter.api.Test;

class MyConnectionTest {

	@Test
	    public void getConnectionTest(){
		
			Connection result = MyConnection.getConnection();
			assertEquals(result != null, true);
			
	    }
	    
	

}
