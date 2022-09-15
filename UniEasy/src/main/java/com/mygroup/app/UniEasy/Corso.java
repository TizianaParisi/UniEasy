package com.mygroup.app.UniEasy;

public class Corso {

	private String codice;
	private String nome;

	
	public void setCodice(String c){
		codice = c;
	}
	
	public void setNome(String n){
		nome = n;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getCodice(){
		return codice;
	}
	
}