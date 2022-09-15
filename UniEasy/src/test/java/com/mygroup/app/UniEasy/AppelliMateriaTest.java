package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppelliMateriaTest {
	
	// Vengono inseriti dati di test al DB per effettuare i test
	@BeforeEach
	void inserimentoDatiDiTest() {
		
		String query = "INSERT INTO materia (codice_mat, nome, cfu, user_docente, cod_corso) VALUES ('10000000', 'MateriaTest', '1', 'FrancG', '5478');";
		String queryApp1 = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('00000005', '2000-01-01 00:00', '00', 'Test', '10000000');";
		String queryApp2 = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('00000006', '2000-01-01 00:00', '00', 'Test', '10000000');";
		String queryIscritto = "INSERT INTO prenotazione (cod_appello, mat_studente, data) VALUES ('00000006', '147283', '2000-01-01 09:00');";
		
		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(query);
			ps.executeUpdate(queryApp1);
			ps.executeUpdate(queryApp2);
			ps.executeUpdate(queryIscritto);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// test per verificare che gli appelli per una specifica materia vengano ottenuti correttamente
	@Test
	void ottieniAppelliMateriaTest() {
		
		AppelliMateria appelliMat = new AppelliMateria();
		
		assertEquals(2, appelliMat.ottieniAppelliMateria("10000000").size());
		
		
	}
	
	// test per verificare che venga ottenuto correttamente il numero di studenti iscritti ad un appello
	@Test
	void ottieniNumeroIscrittiTest() {
		
		AppelliMateria appelliMat = new AppelliMateria();
		
		assertEquals(1, appelliMat.ottieniNumeroIscritti("00000006"));
		
	}
	
	
	// rimozione dei dati di test da DB
	@AfterEach
	void removeDati() {
		
		String queryIscr1 = "DELETE FROM prenotazione WHERE cod_appello='00000006' AND mat_studente = '147283';";
		String queryIscr2 = "DELETE FROM prenotazione WHERE cod_appello='00000005' AND mat_studente = '149630';";
		String query = "DELETE FROM materia WHERE codice_mat='10000000';";
		String queryApp1 = "DELETE FROM appello WHERE codice_app = '00000005';";
		String queryApp2 = "DELETE FROM appello WHERE codice_app = '00000006';";
		try{
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(queryIscr1);
			ps.executeUpdate(queryIscr2);
			ps.executeUpdate(queryApp1);
			ps.executeUpdate(queryApp2);
			ps.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

