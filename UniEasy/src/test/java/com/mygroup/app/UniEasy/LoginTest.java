package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;


class LoginTest {

	// Test del metodo effettuaAccesso che verifica il caso in cui l'username inserito ha lunghezza maggiore di quella ammessa
	@Test
	void whenUsernameUtenteLengthMaggiore_expectUtenteNonVerificato(){
		
		Login login = new Login();
		String utente = new String("Docente");
		String username = new String("usernameLengthMaggiore10");
		String password = new String("pass1234");
		
		assertFalse(login.effettuaAccesso(utente, username, password));
	}
	
	
	// Test del metodo effettuaAccesso che verifica il caso in cui la password inserito ha lunghezza maggiore di quella ammessa
	@Test
	void whenPasswordUtenteLengthMaggiore_expectUtenteNonVerificato(){
		
		Login login = new Login();
		String utente = new String("Studente");
		String username = new String("147283");
		String password = new String("testpassword12345");
		
		assertFalse(login.effettuaAccesso(utente, username, password));
	}
	
	
	// Test del metodo effettuaAccesso (con dati presenti nel DB) che verifica se l'username e la password per l'utente "Docente" permettono l'autenticazione del Docente
	@Test
	void whenDatiDocenteInDB_expectDocenteVerificato() throws SQLException{
		
		Login login = new Login();
		String utente = new String("Docente");
		String username = new String("MarioR");
		String password = new String("pass4567");
		
		
		assertTrue(login.effettuaAccesso(utente, username, password));
	}
	
	
	// Test del metodo effettuaAccesso (con dati NON presenti nel DB) che verifica se l'username e la password per l'utente "Docente" permettono l'autenticazione del Docente
	@Test 
	void whenDatiDocenteNonInDB_expectDocenteNonVerificato() throws SQLException{
		
		Login login = new Login();
		String utente = new String("Docente");
		String username = new String("Err");
		String password = new String("pass4567");

		assertFalse(login.effettuaAccesso(utente, username, password));
	}
	
	
	//Test (con dati presenti nel DB) che verifica se l'username e la password per l'utente "Studente" permettono l'autenticazione dello Studente
	@Test
	void whenDatiStudenteInDB_expectStudenteVerificato(){
		
		Login login = new Login();
		String utente = new String("Studente");
		String username = new String("147283");
		String password = new String("pass1234");
		
		assertTrue(login.effettuaAccesso(utente, username, password));
	}
	
	
	// Test (con dati NON presenti nel DB) che verifica se l'username e la password per l'utente "Studente" permettono l'autenticazione dello Studente
	@Test
	void whenDatiStudentrNonInDB_expectStudenteNonVerificato(){
		
		Login login = new Login();
		String utente = new String("Studente");
		String username = new String("000000");
		String password = new String("pass1234");
		
		assertFalse(login.effettuaAccesso(utente, username, password));
	}
	
	
	// Test con tipogia utente diversa da Docente o da Studente
	@Test
	public void whenTipoUtenteNonValido_expectUtenteNonRiconosciuto(){
		
		Login login = new Login();
		String utente = new String("Utente");
		String username = new String("Test");
		String password = new String("passTes");
		
		assertFalse(login.effettuaAccesso(utente, username, password));
	}
	
	
	
}
