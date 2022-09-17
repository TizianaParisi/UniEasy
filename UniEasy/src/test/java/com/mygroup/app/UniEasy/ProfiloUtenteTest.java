package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class ProfiloUtenteTest {
	
	// Test che verifica se vengono restituite le informazioni per il docente in base all'username fornito
	@Test
	void whenDocenteEsiste_expectInfoDocente() {
		
		String username = "MarioR";
		ProfiloUtente profilo = new ProfiloUtente();
		
		assertNotNull(profilo.ottieniProfiloDocente(username));
		
	}
	
	// Test che verifica il caso in cui non vengono fornite informazioni dato un username non presente nel DB
	@Test
	void whenDocenteNonEsiste_expectNull() {
		
		String username = "Test";
		ProfiloUtente profilo = new ProfiloUtente();
		
		assertNull(profilo.ottieniProfiloDocente(username));
		
	}
		
	// Test che verifica se vengono restituite le informazioni per lo studente in base alla matricola fornita
	@Test
	void whenStudenteEsiste_expectInfoStudente() {
		
		String username = "147283";

		ProfiloUtente profilo = new ProfiloUtente();
		
		assertNotNull(profilo.ottieniProfiloStudente(username));
			
	}
		
	// Test che verifica il caso in cui non vengono fornite informazioni data una matricola non presente nel DB
	@Test
	void whenStudenteNonEsiste_expectNull() {
		
		String username = "Test";
		ProfiloUtente profilo = new ProfiloUtente();
		
		assertNull(profilo.ottieniProfiloStudente(username));
		
	}

}
