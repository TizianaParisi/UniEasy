package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrenotazioneStudenteTest {

	//inserimentzo dati di test nel DB
	@BeforeEach
	void creazioneDati() {
		
		java.sql.Date oggi = new java.sql.Date(System.currentTimeMillis());
		String queryAp1 = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('00000010', '2000-01-01 09:00', '00', 'Test', '25963144');";
		String queryAp2 = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('00000011', '2000-01-01 09:00', '00', 'Test', '25963144');";
		String queryPren1 = "INSERT INTO prenotazione (cod_appello, mat_studente, data) VALUES ('00000010','222222','" + oggi + "');";
		String queryPren2 = "INSERT INTO prenotazione (cod_appello, mat_studente, data) VALUES ('00000011','222222','" + oggi + "');";
		
		String queryStud1 = "INSERT INTO studente (matricola, password, nome, cognome, email, telefono, cod_corso) VALUES ('111111','pasTest','NTest', 'CTest', 'emailtest1@test.it', '3200000000', '5478');";
		String queryStud2 = "INSERT INTO studente (matricola, password, nome, cognome, email, telefono, cod_corso) VALUES ('222222','pasTest','NTest', 'CTest', 'emailtest2@test.it', '3200000000', '5478');";

		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(queryAp1);
			ps.executeUpdate(queryAp2);
			
			ps.executeUpdate(queryStud1);
			ps.executeUpdate(queryStud2);
			
			ps.executeUpdate(queryPren1);
			ps.executeUpdate(queryPren2);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// test del metodo ottieniPrenotazioniStudente per verificare che nel caso non ci siano prenotazioni per lo studente, venga restituita una lista vuota
	@Test
	void whenNonEsistonoPrenotazioni_expectZeroPrenotazioni() {
		
		PrenotazioneStudente prenStud = new PrenotazioneStudente();
		
		assertEquals(0, prenStud.ottieniPrenotazioniStudente("111111").size());
		
	}
	
	// test del metodo ottieniPrenotazioniStudente per verificare che nel caso ci sia almeno una prenotazione per lo studente, venga restituita una lista con almeno un elemento
	@Test
	void whenEsistonoPrenotazioni_expectPiuPrenotazioni() {
		
		PrenotazioneStudente prenStud = new PrenotazioneStudente();
		
		assertEquals(2, prenStud.ottieniPrenotazioniStudente("222222").size());
		
	}
	
	
	// rimozione dati di test dal DB
	@AfterEach
	void removeDati() {
		
		String queryPren1 = "DELETE FROM prenotazione WHERE cod_appello = '00000010' AND mat_studente = '222222';";
		String queryPren2 = "DELETE FROM prenotazione WHERE cod_appello = '00000011' AND mat_studente = '222222';";
		
		String queryAp1 = "DELETE FROM appello WHERE codice_app = '00000010'";
		String queryAp2 = "DELETE FROM appello WHERE codice_app = '00000011'";
		
		String queryStud1 = "DELETE FROM studente WHERE matricola = '111111';";
		String queryStud2 = "DELETE FROM studente WHERE matricola = '222222';";

		
		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(queryPren1);
			ps.executeUpdate(queryPren2);
			ps.executeUpdate(queryAp1);
			ps.executeUpdate(queryAp2);
			
			ps.executeUpdate(queryStud1);
			ps.executeUpdate(queryStud2);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
