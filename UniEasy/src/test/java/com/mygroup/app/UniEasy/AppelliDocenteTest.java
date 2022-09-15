package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppelliDocenteTest {

	@BeforeEach
	void inserimentoDatiDiTest() {
	
		java.util.Date data = new java.util.Date(System.currentTimeMillis());
		
		// Inserisco per il test un appello con 2 prenotazioni per la materia con codice 03232106 del docente MarioR
		String queryAp = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('00000001', '2000-01-01 10:00', '0', 'Test', '03232106');";
		String queryPren1 = "INSERT INTO prenotazione (cod_appello, mat_studente, data) VALUES ('00000001','140326','" + data + "');";
		String queryPren2 = "INSERT INTO prenotazione (cod_appello, mat_studente, data) VALUES ('00000001','142536','" + data + "');";
		
		// Inserisco un docente (DocTest0) con una materia con codice 00000002 per cui non sono stati inseriti appelli
		String queryDoc = "INSERT INTO docente (username, password, nome, cognome, email, telefono) VALUES ('DocTest', 'pas0Test', 'NTest', 'CTest', 'email@test.it', '3200000000');";
		String queryMat = "INSERT INTO materia (codice_mat, nome, cfu, user_docente, cod_corso) VALUES ('00000002', 'MatTest', '0', 'DocTest', '5478');";

		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(queryAp);
			ps.executeUpdate(queryPren1);
			ps.executeUpdate(queryPren2);
			
			ps.executeUpdate(queryDoc);
			ps.executeUpdate(queryMat);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Test del metodo ottieniAppelliDelDocente che verifica il caso in cui il docente ha già fissato almeno un appello:
	// la lista ottenuta conterrà almeno un elemento
	
	@Test
	void whenAppelliPresenti_expectListaConElementi() {
		
		AppelliDocente appelliDoc = new AppelliDocente();
		String username = "MarioR";
		
		assertNotEquals(0, appelliDoc.ottieniAppelliDelDocente(username).size());
		
	}
	
	
	// Test del metodo ottieniAppelliDelDocente che verifica il caso in cui il docente non ha ancora fissato nessun un appello:
	// la lista conterrà 0 elementi
	
	@Test
	void whenAppelliNonPresenti_expectListaVuota() {
		
		AppelliDocente appelliDoc = new AppelliDocente();
		String username = "DocTest";
		
		assertEquals(0, appelliDoc.ottieniAppelliDelDocente(username).size());
		
	}
	
	// Test del metodo caricaMateriaDiAppello per verificare che venga restituita la materia relativa all'appello	
	@Test
	void whenOttieniMateriaAppelloTest_expectMateriaDiAppello() {
		
		AppelliDocente appelliDoc = new AppelliDocente();
		
		assertEquals(appelliDoc.caricaMateriaDiAppello("00000001").getCodice(), "03232106");
		
	}
	
	// Test del metodo caricaMateriaDiAppello per verificare il caso in cui non si ottiene nessuna materia associata
	@Test
	void whenNonOttieniMateriaAppelloTest_expectNoMateria() {
		
		AppelliDocente appelliDoc = new AppelliDocente();
		
		Appello appello = mock(Appello.class);
		
		when(appello.getCodice()).thenReturn("11223341");
		when(appello.getCodiceMateria()).thenReturn("00000000");
		
		assertNotEquals(appelliDoc.caricaMateriaDiAppello(appello.getCodice()).getCodice(), appello.getCodiceMateria());
	}
	
	
	//cancallazione dei dati di test
	@AfterEach
	void deleteDatiDiTest() {
		
		String queryAp = "DELETE FROM appello WHERE codice_app =  '00000001'";
		String queryPren1 = "DELETE FROM prenotazione WHERE cod_appello =  '00000001' AND mat_studente = '140326';";
		String queryPren2 = "DELETE FROM prenotazione WHERE cod_appello =  '00000001' AND mat_studente = '142536';";
		
		String queryMat = "DELETE FROM materia WHERE codice_mat =  '00000002';";
		String queryDoc = "DELETE FROM docente WHERE username =  'DocTest';";
		
		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			
			ps.executeUpdate(queryPren1);
			ps.executeUpdate(queryPren2);
			ps.executeUpdate(queryAp);
			
			ps.executeUpdate(queryMat);
			ps.executeUpdate(queryDoc);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
