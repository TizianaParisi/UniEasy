package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.junit.jupiter.api.Test;

class AppelloModificatoTest {

	// test per verificare che nel caso di formato data e formato ora non corretti, data e ora dell'appello restano invariati rispetto al precedente
	@Test
	void whenFormatoDataEOraNonCorretto_expectDataEOraNonModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String data = "20-9-9";
		String ora = "90:90";
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertEquals(appelloOriginale.getData(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, data, ora, "01", "origTest").getData());
		
	}
	
	
	// test per verificare che nel caso di formato data e formato ora entrambi corretti, data e ora dell'appello saranno diversi rispetto al precedente
	@Test
	void whenFormatoDataEOraCorretto_expectDataEOraModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String data = "2022-06-09";
		String ora = "10:00";
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertNotEquals(appelloOriginale.getData(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, data, ora, "01", "origTest").getData());
		
	}
	
	// test per verificare che nel caso del solo formato data non corretto, data e ora dell'appello saranno invariati rispetto al precedente
	@Test
	void whenFormatoDataNonCorretto_expectDataEOraNonModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String data = "20-0-0";
		String ora = "10:30";
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertEquals(appelloOriginale.getData(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, data, ora, "01", "origTest").getData());
		
	}
	
	// test per verificare che nel caso del solo formato ora non corretto, data e ora dell'appello saranno invariati rispetto al precedente
	@Test
	void whenFormatoOraNonCorretto_expectDataEOraNonModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String data = "2022-02-10";
		String ora = "90:90";
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertEquals(appelloOriginale.getData(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, data, ora, "01", "origTest").getData());
		
	}
	
	// test per verificare che nel caso il formato aula è corretto, aula dell'appello sarà diverso rispetto al precedente
	@Test
	void whenFormatoAulaCorretto_expectAulaModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String aula = "T02";
		
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertNotEquals(appelloOriginale.getAula(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, "2022-02-10", "10:30", aula, "origTest").getAula());
		
	}
	
	// test per verificare che nel caso il formato aula non è corretto (lunghezza supera 20), aula dell'appello sarà uguale rispetto al precedente
	@Test
	void whenFormatoAulaNonCorretto_expectAulaNonModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String aula = "000000000000000000000000";
		
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertEquals(appelloOriginale.getAula(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, "2022-02-10", "10:30", aula, "origTest").getAula());
		
	}
	
	// test per verificare che nel caso il formato aula non è corretto (lunghezza inferiore a 1), aula dell'appello sarà uguale rispetto al precedente
	@Test
	void whenFormatoAulaNonCorretto2_expectAulaNonModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String aula = "";
		
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertEquals(appelloOriginale.getAula(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, "2022-02-10", "10:30", aula, "origTest").getAula());
		
	}
	
	// test per verificare che nel caso il formato della modalità di esame è corretto, la modalità (il tipo) dell'appello sarà diverso rispetto al precedente
	@Test
	void whenFormatoModalitaCorretto_expectModalitaModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String modalita = "Orale Test";
		
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertNotEquals(appelloOriginale.getTipo(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, "2022-02-10", "10:30", "01", modalita).getTipo());
		
	}
	
	// test per verificare che nel caso il formato della modalità di esame non è corretto, la modalità (il tipo) dell'appello sarà uguale rispetto al precedente
	@Test
	void whenFormatoModalitaNonCorretto_expectModalitaNonModificato() {
		
		AppelloModificato modAp = new AppelloModificato();
		Appello appelloOriginale = mock(Appello.class);
		String cod_materia = "23632106";
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		
		String modalita = "Orale Test Non Valido Lunghezza Superata................";
		
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		assertEquals(appelloOriginale.getTipo(), modAp.verificaCorrettezzaDatiInseriti(appelloOriginale, cod_materia, "2022-02-10", "10:30", "01", modalita).getTipo());
		
	}
	
	
	
	// test che verifica l'aggiornamento delle informazioni dell'appello nel DB in caso di Aula, Data e Tipo correttamente modificati
	@Test
	void whenDataAulaTipoModificati_expectAggiornamentoDataAulaTipo() {
		
		AppelloModificato modAppello = new AppelloModificato();
		Appello appelloModificato = mock(Appello.class);
		Appello appelloOriginale = mock(Appello.class);
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
        String dataEora2 = "2000-01-01 00:00";
		DateTimeFormatter formatDateTime2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime2 = LocalDateTime.from(formatDateTime2.parse(dataEora2));
        Timestamp ts2 = Timestamp.valueOf(localDateTime2);
        
		when(appelloModificato.getCodice()).thenReturn("00000002");
		when(appelloModificato.getCodiceMateria()).thenReturn("23632106");
		when(appelloModificato.getData()).thenReturn(ts1);
		when(appelloModificato.getAula()).thenReturn("T01");
		when(appelloModificato.getTipo()).thenReturn("modTest");
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts2);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		
		
		assertTrue(modAppello.modificaInformazioniAppello(appelloOriginale, appelloModificato));
		
	}
	
	// test che verifica che il DB non sia stato modificato con le informazioni in caso di Aula, Data e Tipo non correttamente modificati
	@Test
	void whenDataAulaTipoNonModificati_expectNoAggiornamentoDataAulaTipo() {
		
		AppelloModificato modAppello = new AppelloModificato();
		Appello appelloModificato = mock(Appello.class);
		Appello appelloOriginale = mock(Appello.class);
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
		when(appelloModificato.getCodice()).thenReturn("00000002");
		when(appelloModificato.getCodiceMateria()).thenReturn("23632106");
		when(appelloModificato.getData()).thenReturn(ts1);
		when(appelloModificato.getAula()).thenReturn("01");
		when(appelloModificato.getTipo()).thenReturn("origTest");
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		
		
		assertTrue(modAppello.modificaInformazioniAppello(appelloOriginale, appelloModificato));
		
	}
	
	// test che verifica che il DB sia stato modificato in caso di Data correttamente modificato
	@Test
	void whenDataModificati_expectAggiornamentoData() {
		
		AppelloModificato modAppello = new AppelloModificato();
		Appello appelloModificato = mock(Appello.class);
		Appello appelloOriginale = mock(Appello.class);
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
        String dataEora2 = "2000-01-01 00:00";
		DateTimeFormatter formatDateTime2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime2 = LocalDateTime.from(formatDateTime2.parse(dataEora2));
        Timestamp ts2 = Timestamp.valueOf(localDateTime2);
        
		when(appelloModificato.getCodice()).thenReturn("00000002");
		when(appelloModificato.getCodiceMateria()).thenReturn("23632106");
		when(appelloModificato.getData()).thenReturn(ts1);
		when(appelloModificato.getAula()).thenReturn("01");
		when(appelloModificato.getTipo()).thenReturn("ModTest");
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts2);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
	
		assertTrue(modAppello.modificaInformazioniAppello(appelloOriginale, appelloModificato));
		
	}
	
	
	// test che verifica che il DB sia stato modificato in caso di Aula correttamente modificato
	@Test
	void whenAulaModificati_expectAggiornamentoAula() {
		
		AppelloModificato modAppello = new AppelloModificato();
		Appello appelloModificato = mock(Appello.class);
		Appello appelloOriginale = mock(Appello.class);
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
       
		when(appelloModificato.getCodice()).thenReturn("00000002");
		when(appelloModificato.getCodiceMateria()).thenReturn("23632106");
		when(appelloModificato.getData()).thenReturn(ts1);
		when(appelloModificato.getAula()).thenReturn("M01");
		when(appelloModificato.getTipo()).thenReturn("origTest");
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		
		
		assertTrue(modAppello.modificaInformazioniAppello(appelloOriginale, appelloModificato));
		
	}
	
	// test che verifica che il DB sia stato modificato in caso di Tipo correttamente modificato
	@Test
	void whenTipoModificati_expectAggiornamentoTipo() {
		
		AppelloModificato modAppello = new AppelloModificato();
		Appello appelloModificato = mock(Appello.class);
		Appello appelloOriginale = mock(Appello.class);
		
		String dataEora1 = "2000-02-02 10:00";
		DateTimeFormatter formatDateTime1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.from(formatDateTime1.parse(dataEora1));
        Timestamp ts1 = Timestamp.valueOf(localDateTime1);
		
       
		when(appelloModificato.getCodice()).thenReturn("00000002");
		when(appelloModificato.getCodiceMateria()).thenReturn("23632106");
		when(appelloModificato.getData()).thenReturn(ts1);
		when(appelloModificato.getAula()).thenReturn("01");
		when(appelloModificato.getTipo()).thenReturn("ModTest");
		
		when(appelloOriginale.getCodice()).thenReturn("00000002");
		when(appelloOriginale.getCodiceMateria()).thenReturn("23632106");
		when(appelloOriginale.getData()).thenReturn(ts1);
		when(appelloOriginale.getAula()).thenReturn("01");
		when(appelloOriginale.getTipo()).thenReturn("origTest");
		
		
		
		assertTrue(modAppello.modificaInformazioniAppello(appelloOriginale, appelloModificato));
		
	}
	
	
	
}

