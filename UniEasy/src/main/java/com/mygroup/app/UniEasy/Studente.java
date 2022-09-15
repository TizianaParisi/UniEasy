package com.mygroup.app.UniEasy;

public class Studente {
	
	private String matricola;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	private String cod_corso;
	

	public Studente(){
		
		matricola = new String("/");
		password = new String("/");
		nome = new String("/");
		cognome = new String("/");
		email = new String("/");
		telefono = new String("/");
		cod_corso = new String("/");
		
	}
	
	public String getMatricola(){
		return matricola;
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
	
	public String getCorso(){
		return cod_corso;
	}
	
	public void setMatricola(String user){
		matricola = user;
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
	
	public void setCorso(String c){
		cod_corso = c;
	}
	

}

