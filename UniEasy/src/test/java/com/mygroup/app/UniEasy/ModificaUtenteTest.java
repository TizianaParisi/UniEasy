package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class ModificaUtenteTest {
	
	// Test del metodo modificaDatiProfiloDocente che verifica il caso in cui, con dati inseriti corretti, l'aggiornamento dei dati del docente 
	// cambia le informazioni precedenti	
	@Test
	void whenDatiCorretti_expectDocenteModificato() {
			
		Docente doc = mock(Docente.class);
		String newEmail = "testcorret1@test.it";
		String newTel = "3201111111";
		String newPass = "newpassTest0";
		ModificaUtente modifica = new ModificaUtente();
		
		when(doc.getUsername()).thenReturn("docTest");
		when(doc.getPassword()).thenReturn("passTest1");
		when(doc.getNome()).thenReturn("NomeTest");
		when(doc.getCognome()).thenReturn("CognomeTest");
		when(doc.getEmail()).thenReturn("emailtest1@test.it");
		when(doc.getTelefono()).thenReturn("3200000000");

		assertNotEquals(modifica.modificaDatiProfiloDocente(doc, newEmail, newTel, newPass), doc);
	
	}
	
	// Test del metodo modificaDatiProfiloDocente che verifica il caso in cui, con dati inseriti non corretti, l'aggiornamento dei dati del docente 
	// non cambia le informazioni precedenti
	@Test
	void whenDatiNonCorretti_expectDocenteNonModificato() {
		
		Docente doc = mock(Docente.class);
		String newEmail = "err_euniversity.it";
		String newTel = "0";
		String newPass = "_";
		ModificaUtente modifica = new ModificaUtente();
		
		when(doc.getUsername()).thenReturn("docTest");
		when(doc.getPassword()).thenReturn("passTest1");
		when(doc.getNome()).thenReturn("NomeTest");
		when(doc.getCognome()).thenReturn("CognomeTest");
		when(doc.getEmail()).thenReturn("emailtest1@test.it");
		when(doc.getTelefono()).thenReturn("3200000000");
		
		
		assertEquals(modifica.modificaDatiProfiloDocente(doc, newEmail, newTel, newPass).getPassword(), doc.getPassword());
		assertEquals(modifica.modificaDatiProfiloDocente(doc, newEmail, newTel, newPass).getEmail(), doc.getEmail());
		assertEquals(modifica.modificaDatiProfiloDocente(doc, newEmail, newTel, newPass).getTelefono(), doc.getTelefono());
	
	}
	
	// Test del metodo modificaDatiProfiloStudente che verifica il caso in cui, con dati inseriti corretti, l'aggiornamento dei dati dello studente 
	// cambia le informazioni precedenti
	@Test
	void whenDatiCorretti_expectStudenteModificato() {		
		
		Studente stud = mock(Studente.class);
		String newEmail = "testcorret1@test.it";
		String newTel = "3201111111";
		String newPass = "newpassTest0";
		ModificaUtente modifica = new ModificaUtente();
		
		when(stud.getMatricola()).thenReturn("111111");
		when(stud.getPassword()).thenReturn("passTest1");
		when(stud.getNome()).thenReturn("NomeTest");
		when(stud.getCognome()).thenReturn("CognomeTest");
		when(stud.getEmail()).thenReturn("emailtest1@test.it");
		when(stud.getTelefono()).thenReturn("3200000000");
		when(stud.getCorso()).thenReturn("1122");

		assertNotEquals(modifica.modificaDatiProfiloStudente(stud, newEmail, newTel, newPass), stud);
	
	}
	
	// Test del metodo modificaDatiProfiloStudente che verifica il caso in cui, con dati inseriti non corretti, l'aggiornamento dei dati dello studente
	// non cambia le informazioni precedenti
	@Test
	void whenDatiNonCorretti_expectStudenteNonModificato() {
		
		
		Studente stud = mock(Studente.class);
		String newEmail = "err_euniversity.it";
		String newTel = "0";
		String newPass = "_";
		ModificaUtente modifica = new ModificaUtente();
		
		when(stud.getMatricola()).thenReturn("111111");
		when(stud.getPassword()).thenReturn("passTest1");
		when(stud.getNome()).thenReturn("NomeTest");
		when(stud.getCognome()).thenReturn("CognomeTest");
		when(stud.getEmail()).thenReturn("emailtest1@test.it");
		when(stud.getTelefono()).thenReturn("3200000000");
		when(stud.getCorso()).thenReturn("1122");
		
		
		assertEquals(modifica.modificaDatiProfiloStudente(stud, newEmail, newTel, newPass).getPassword(), stud.getPassword());
		assertEquals(modifica.modificaDatiProfiloStudente(stud, newEmail, newTel, newPass).getEmail(), stud.getEmail());
		assertEquals(modifica.modificaDatiProfiloStudente(stud, newEmail, newTel, newPass).getTelefono(), stud.getTelefono());
	
	}
	
}
