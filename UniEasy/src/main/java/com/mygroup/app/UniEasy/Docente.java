package com.mygroup.app.UniEasy;

public class Docente {
	
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;

	public Docente(){
		
		username = new String("/");
		password = new String("/");
		nome = new String("/");
		cognome = new String("/");
		email = new String("/");
		telefono = new String("/");
		
	}
	

	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public void setUsername(String user){
		username = user;
	}
	
	public void setPassword(String p){
		password = p;
	}
	
	public void setNome(String n){
		nome = n;
	}
	
	public void setCognome(String c){
		cognome = c;
	}
	
	public void setEmail(String e){
		email = e;
	}
	
	public void setTelefono(String t){
		telefono = t;
	}
	

}

