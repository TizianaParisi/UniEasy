package com.mygroup.app.UniEasy;

import java.util.Date;

public class Esame {

	private String cod_materia;
	private String mat_studente;
	Date data = new Date();
	private String voto;

	
	public Esame(){
		
		cod_materia = new String("ND");
		mat_studente = new String("ND");
		data = null;
		voto = new String("-");
		
	}
	
	public void setCodMateria(String s){
		cod_materia = s;
	}
	
	public void setMatStudente(String m) {
		mat_studente = m;
	}
	
	public void setVoto(String v){
		voto = v;
	}
	
	public String getCodMateria(){
		return cod_materia;
	}
	
	public String getMatStudente(){
		return mat_studente;
	}
	
	public String getVoto(){
		return voto;
	}
	
	public void setData(Date d) {
		data = d;	
	}
	
	public Date getData(){
		return data;
	}
	
}


