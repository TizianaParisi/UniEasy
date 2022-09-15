package com.mygroup.app.UniEasy;

import java.sql.Timestamp;
import java.util.Date;

public class Prenotazione {
	
	private String cod_appello;
	private String materia;
	private String aula;
	private String tipo;
	private String mat_studente;
	private Timestamp data_appello;
	private Date data_prenotazione;
	
	
	public String getCodAppello() {
		return cod_appello;
	}
	
	public void setCodAppello(String codAp) {
		this.cod_appello = codAp;
	}
	
	public String getMatStudente() {
		return mat_studente;
	}
	
	public void setMatStudente(String mStud) {
		this.mat_studente = mStud;
	}
	
	

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Timestamp getData_appello() {
		return data_appello;
	}

	public void setData_appello(Timestamp data_appello) {
		this.data_appello = data_appello;
	}

	public Date getData_prenotazione() {
		return data_prenotazione;
	}

	public void setData_prenotazione(Date data_prenotazione) {
		this.data_prenotazione = data_prenotazione;
	}
	
}

