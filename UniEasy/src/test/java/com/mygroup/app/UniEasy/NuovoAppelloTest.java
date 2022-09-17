package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class NuovoAppelloTest {

	// test per verificare che vengano correttamente caricare dal DB le materie associate al docente specificato tramite username
	@Test
	void caricaMaterieDocenteTest() {
		
		NuovoAppello newAppello = new NuovoAppello();
		String username = "MarioR";
		
		assertNotEquals(0, newAppello.caricaMaterieDocente(username).size());
		
	}
	
	
	// 	TEST CHE VERIFICANO LA CORRETTEZZA DEI DATI INSERITI
	
	// tutti i dati sono corretti
	@Test
	void whenTuttiDatiInseritiCorretti_expectTrue() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "11111111";
		String data = "2022-02-16";
		String ora = "09:00";
		String aula = "T01";
		String modalita = "ScrittoTest";
		
		assertTrue(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// tutti i dati non sono corretti
	@Test
	void whenTuttiDatiInseritiNonCorretti_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "1";
		String data = "20-02-16";
		String ora = "0:90";
		String aula = "";
		String modalita = "T";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// tutti i dati sono corretti tranne il codice dell'appello
	@Test
	void whenSoloCodApInseritoNonCorretto_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "1";
		String data = "2022-02-16";
		String ora = "09:00";
		String aula = "T01";
		String modalita = "ScrittoTest";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// tutti i dati sono corretti tranne la data
	@Test
	void whenSoloDataInseritoNonCorretto_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "11111111";
		String data = "20-0";
		String ora = "09:00";
		String aula = "T01";
		String modalita = "ScrittoTest";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// tutti i dati sono corretti tranne l'ora
	@Test
	void whenSoloOraInseritoNonCorretto_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "11111111";
		String data = "2022-02-16";
		String ora = "0:90";
		String aula = "T01";
		String modalita = "ScrittoTest";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// tutti i dati sono corretti tranne l'aula (lunghezza inferiore al minimo)
	@Test
	void whenSoloAulaInseritoNonCorretto1_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "11111111";
		String data = "2022-02-16";
		String ora = "09:00";
		String aula = "_";
		String modalita = "ScrittoTest";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// tutti i dati sono corretti tranne l'aula (lunghezza superiore al massimo)
	@Test
	void whenSoloAulaInseritoNonCorretto2_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "11111111";
		String data = "2022-02-16";
		String ora = "09:00";
		String aula = "0000000000000000000000000000000000000000000";
		String modalita = "ScrittoTest";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	
	// tutti i dati sono corretti tranne il tipo di esame
	@Test
	void whenSoloModalitaInseritoNonCorretto_expectFalse() {
		
		NuovoAppello newAp = new NuovoAppello();
		
		String cod_ap = "11111111";
		String data = "2022-02-16";
		String ora = "09:00";
		String aula = "T01";
		String modalita = "Inserimento modalita di test non corretto............";
		
		assertFalse(newAp.controlloDatiNuovoAppello(cod_ap, data, ora, aula, modalita));
		
	}
	
	// test che verifica il corretto inserimento del nuovo appello nel DB
	@Test
	void aggiungereNuovoAppelloTest() {
		
		
		NuovoAppello newAppello = new NuovoAppello();
		
		String cod_appello = "00000001";
		String dataEora = "2000-01-01 00:00";
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dataEora));
        Timestamp data = Timestamp.valueOf(localDateTime);
		String aula = "A";
		String modalita = "Nessuno";
		String cod_materia = "23632106";
		
		assertTrue(newAppello.aggiungereNuovoAppello(cod_appello, data, aula, modalita, cod_materia));
		
	}
	
	@AfterEach
	void eliminaAppelloTest() {
		
		String query = "DELETE FROM appello WHERE codice_app =  '00000001' ;";
		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
