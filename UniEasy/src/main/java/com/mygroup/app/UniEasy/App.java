package com.mygroup.app.UniEasy;

public class App {

	public static void main(String[] args) {
		
		Login login = new Login();
		
		InterfPrincipale finestra = new InterfPrincipale();
		finestra.add(login.loginComponent());	
		
		
		
	}

}
