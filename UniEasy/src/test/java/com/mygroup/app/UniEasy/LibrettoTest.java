package com.mygroup.app.UniEasy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.Test;

class LibrettoTest {

	// test che verifica se il corso a cui lo studente è iscritto viene ottenuto correttamente
	@Test
	void ottieniCorsoStudenteTest() {

		String matricolaStud = "147283";
		String corso = "5478";
		Libretto libretto = new Libretto();
		

		assertEquals(corso, libretto.ottieniCorsoStudente(matricolaStud).getCodice());
		
	}
	
	// test per verificare che le materie associate al corso vengano ottenute correttamente
	@Test
	void ottieniListaMaterieDelCorsoTest() {
		
		String codiceCorso = "5478";
		Libretto libretto = new Libretto();
		
		assertNotEquals(0, libretto.ottieniListaMaterieDelCorso(codiceCorso).size());
		
	}

	// test per verificare, nel caso lo studente abbia già sostenuto almeno un esame, che gli esami vengano ottenuti correttamente
	@Test
	void whenStudenteHaSostenutoAlmenoUnEsame_expectEsami() {
		
		String codiceCorso = "5478";
		String matrStrudente = "147283";
		Libretto libretto = new Libretto();
		
		assertNotEquals(0, libretto.ottieniEsamiSostenuti(codiceCorso, matrStrudente).size());
		
	}
	
	// test per verificare, nel caso lo studente non abbia ancora sostenuto nessun esame, che venga ottenuta una lista vuota
	@Test
	void whenStudenteNonHaSostenutoNessunEsame_expectZeroEsami() {
		
		String codiceCorso = "5478";
		String matrStrudente = "111111";
		Libretto libretto = new Libretto();
		
		assertEquals(0, libretto.ottieniEsamiSostenuti(codiceCorso, matrStrudente).size());
		
	}
	
}
