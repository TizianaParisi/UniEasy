package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IscrittiAppelloTest {

	
	// Inserimento dei dati di test nel DB
	@BeforeEach
	void inserimentoDatiDiTest() {
		
		String query5 = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('11223344', '2022-02-12 10:00', '00', 'Test', '00000001');";
		String query4 = "INSERT INTO materia (codice_mat, nome, cfu, user_docente, cod_corso) VALUES ('00000001', 'MatTest', '0', 'TestDoc', '5478');";
		String query1 = "INSERT INTO docente (username, password, nome, cognome, email, telefono) VALUES ('TestDoc', 'passTest', 'NomeTest', 'CognTest', 'emailtest11@test.it', '3200000000');";
		String query3 = "INSERT INTO studente (matricola, password, nome, cognome, email, telefono, cod_corso) VALUES ('141414', 'passTest', 'NomeTest', 'CognTest', 'emailtest1@test.it', '3201111111', '5478');";
		String query6 = "INSERT INTO prenotazione (cod_appello, mat_studente, data) VALUES ('11223344', '141414', '2022-02-12 00:00');";

		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			
			ps.executeUpdate(query1);
			ps.executeUpdate(query3);
			ps.executeUpdate(query4);
			ps.executeUpdate(query5);
			ps.executeUpdate(query6);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Test del metodo ottieniStudentiIscritti che verifica il caso in cui un appello senza studenti iscritti fornisce una lista vuota
	@Test
	void whenNoStudentiIscritti_expectNessunIscritto() {
		
		IscrittiAppello iscritti = new IscrittiAppello();
		Appello appello = mock(Appello.class);
		
		when(appello.getCodice()).thenReturn("01000000");
		
		assertEquals(0, iscritti.ottieniStudentiIscritti(appello).size());
		
	}
	
	// Test del metodo ottieniStudentiIscritti che verifica il caso in cui un appello con almeno uno studente iscritto fornisce una lista non vuota
	@Test
	void whenStudentiIscritti_expectListaConIscritti() {
		
		IscrittiAppello iscritti = new IscrittiAppello();
		Appello appello = mock(Appello.class);
		
		when(appello.getCodice()).thenReturn("11223344");
		
		assertNotEquals(0, iscritti.ottieniStudentiIscritti(appello).size());
		
	}
	
	// Test del metodo ottieniMateriaAppello per verificare che venga restituita la materia relativa all'appello	
	@Test
	void whenOttieniMateriaAppelloTest_expectMateriaDiAppello() {
		
		IscrittiAppello iscritti = new IscrittiAppello();
		
		Appello appello = mock(Appello.class);
		
		when(appello.getCodice()).thenReturn("11223344");
		when(appello.getCodiceMateria()).thenReturn("00000001");
		
		assertEquals(iscritti.ottieniMateriaAppello(appello).getCodice(), appello.getCodiceMateria());
	}
	
	// Test del metodo ottieniMateriaAppello per verificare il caso in cui non si ottiene nessuna materia associata
	@Test
	void whenNonOttieniMateriaAppelloTest_expectNoMateria() {
		
		IscrittiAppello iscritti = new IscrittiAppello();
		
		Appello appello = mock(Appello.class);
		
		when(appello.getCodice()).thenReturn("11223341");
		when(appello.getCodiceMateria()).thenReturn("00000000");
		
		assertNotEquals(iscritti.ottieniMateriaAppello(appello).getCodice(), appello.getCodiceMateria());
	}
	
	
	//cancallazione dei dati di test
	@AfterEach
	void deleteDatiDiTest() {
		
		String query2 = "DELETE FROM appello WHERE codice_app = '11223344';";
		String query3 = "DELETE FROM materia WHERE codice_mat = '00000001';";
		String query6 = "DELETE FROM docente WHERE username = 'TestDoc';";
		String query4 = "DELETE FROM studente WHERE matricola = '141414';";
		String query1 = "DELETE FROM prenotazione WHERE cod_appello = '11223344' AND mat_studente = '141414';";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			
			ps.executeUpdate(query1);
			ps.executeUpdate(query2);
			ps.executeUpdate(query3);
			ps.executeUpdate(query4);
			ps.executeUpdate(query6);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
