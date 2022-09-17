package com.mygroup.app.UniEasy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RegistrazioneEsameTest {

	//Inserimento dati di test
	@BeforeEach
	void inserimentoDatiDiTest() {
	
		String dataEora = "2000-01-01 00:00";
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dataEora));
        Timestamp ts = Timestamp.valueOf(localDateTime);
              
		String query1 = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('00000001', '" + ts + "', '0', 'Test', '23632106');";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(query1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
	
	// Test metodo registrazioneEsitoEsame con esame superato
	@Test
	void whenEsamePositivoRegistrato_expectTrue() {
			
		RegistrazioneEsame regEsame = new RegistrazioneEsame();
		Esame esame = mock(Esame.class);
		
		Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		when(esame.getCodMateria()).thenReturn("23632106");
		when(esame.getData()).thenReturn(date);
		when(esame.getMatStudente()).thenReturn("147283");
		when(esame.getVoto()).thenReturn("20");
		
		
		assertTrue(regEsame.registrazioneEsitoEsame(esame, "Promosso"));
		
	}
	
	// Test metodo registrazioneEsitoEsame con esame non superato
	@Test
	void whenEsameNegativoRegistrato_expectTrue() {
		
		
		RegistrazioneEsame regEsame = new RegistrazioneEsame();
		Esame esame = mock(Esame.class);
		
		Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		when(esame.getCodMateria()).thenReturn("23632106");
		when(esame.getData()).thenReturn(date);
		when(esame.getMatStudente()).thenReturn("147283");
		when(esame.getVoto()).thenReturn("20");
		
		
		assertTrue(regEsame.registrazioneEsitoEsame(esame, "Bocciato"));
		
	}
	
	
	// Test metodo registrazioneEsitoEsame con esito non corretto
	@Test
	void whenEsitoEsameNonCorretto_expectFalse() {
			
		RegistrazioneEsame regEsame = new RegistrazioneEsame();
		Esame esame = mock(Esame.class);
			
		Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			
		when(esame.getCodMateria()).thenReturn("23632106");
		when(esame.getData()).thenReturn(date);
		when(esame.getMatStudente()).thenReturn("147283");
		when(esame.getVoto()).thenReturn("20");
			
			
		assertFalse(regEsame.registrazioneEsitoEsame(esame, "Test"));
			
	}
	
	
	// Test metodo rimozionePrenotazione per verificare se la prenotazione è stata eliminata con succcesso
	@Test
	void rimozionePrenotazioneTest() {
	
		RegistrazioneEsame eliminaPrenot = new RegistrazioneEsame();
		Prenotazione prenotazione = mock(Prenotazione.class);
	
		when(prenotazione.getCodAppello()).thenReturn("00000001");
		when(prenotazione.getMatStudente()).thenReturn("147283");
		
		
		assertTrue(eliminaPrenot.rimozionePrenotazione("147283", "00000001"));
	}
	
	
	//cancallazione dei dati di test
	@AfterEach
	void removeExam() {
		
		String query1 = "DELETE FROM esame WHERE cod_materia = '23632106' AND mat_studente = '147283';";
		String query2 = "DELETE FROM appello WHERE codice_app = '00000001';";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			
			ps.executeUpdate(query1);
			ps.executeUpdate(query2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
