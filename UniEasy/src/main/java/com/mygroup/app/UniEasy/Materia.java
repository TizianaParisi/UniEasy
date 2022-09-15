package com.mygroup.app.UniEasy;

public class Materia {
	
	private String codice = new String();
	private String nome = new String();
	private String cfu;
	private String user_docente = new String();
	private String codice_corso = new String();
	
	public String getCodice(){
		return codice;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getCfu(){
		return cfu;
	}
	
	public void setCodice(String c){
		codice = c;
	}
	public void setNome(String n){
		nome = n;
	}
	
	public void setCfu(String c){
		cfu = c;
	}
	
	public String toString(){
		return (codice + "  " + nome);
	}
	
	public String getUserDocente(){
		return user_docente;
	}
	
	public void setUserDocente(String ud){
		user_docente = ud;
	}
	
	public String getCodiceCorso(){
		return codice_corso;
	}
	
	public void setCodiceCorso(String ud){
		codice_corso = ud;
	}

}
