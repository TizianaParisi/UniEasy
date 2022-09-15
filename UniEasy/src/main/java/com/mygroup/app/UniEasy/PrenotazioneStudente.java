package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class PrenotazioneStudente{

	static Dimension d1 = new Dimension(240, 20);
	static Dimension d2 = new Dimension(170, 35);
	static Dimension d3 = new Dimension(80, 20);
	static Dimension d4 = new Dimension(600, 40);
	static private int w = 15;
	static int h = 15;
	static Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	static Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	DateFormat dateFormatPrenotaz = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public LinkedList<Prenotazione> ottieniPrenotazioniStudente(String matricola){
		
		LinkedList<Prenotazione> listaPrenApp = new LinkedList<Prenotazione>();
		
		String query = "SELECT materia.nome, appello.data, appello.aula, appello.tipo, prenotazione.data, prenotazione.cod_appello, prenotazione.mat_studente "
				+ "FROM materia "
				+ "INNER JOIN appello "
				+ "ON appello.cod_materia = materia.codice_mat "
				+ "INNER JOIN prenotazione "
				+ "ON prenotazione.cod_appello = appello.codice_app AND prenotazione.mat_studente='" + matricola + "';";
	
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			
			while(res.next()){
				
				Prenotazione effettuataP = new Prenotazione();
				
				effettuataP.setMateria(res.getString("nome"));
				effettuataP.setData_appello(res.getTimestamp("data"));
				effettuataP.setAula(res.getString("aula"));
				effettuataP.setTipo(res.getString("tipo"));
				effettuataP.setCodAppello(res.getString("cod_appello"));
				effettuataP.setMatStudente(res.getString("mat_studente"));
	
				listaPrenApp.add(effettuataP);
				
			}
			
		}  catch(Exception e){
			e.printStackTrace();
		}
	
		return listaPrenApp;
		
	}
	
	
	public JPanel getIntestazione(){
		JPanel intest = new JPanel();
		
		JPanel nomeMateria = new JPanel();
		JPanel dataAppello = new JPanel();
		JPanel aula = new JPanel();
		JPanel tipo = new JPanel();
		
		JLabel tmp = new JLabel();
		
		nomeMateria.setMaximumSize(d3);		nomeMateria.setMinimumSize(d3);		nomeMateria.setPreferredSize(d3);
		dataAppello.setMaximumSize(d1);		dataAppello.setMinimumSize(d1);		dataAppello.setPreferredSize(d1);
		aula.setMaximumSize(d2); 			aula.setMinimumSize(d2);			aula.setPreferredSize(d2);
		tipo.setMaximumSize(d2); 		tipo.setMinimumSize(d2);  	tipo.setPreferredSize(d2);
	
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Materia");
		nomeMateria.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Data");
		dataAppello.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Aula");
		aula.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Modalità");
		tipo.add(tmp);
		
		
		intest.setLayout(new BoxLayout(intest, BoxLayout.X_AXIS));
		intest.add(Box.createHorizontalStrut(w));
		intest.add(nomeMateria);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(dataAppello);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(aula);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(tipo);
		
		return intest;
		
	}
	
	
	public JPanel visualizzaPrenotazione(final Prenotazione prenotazione) {
		
		JPanel complessivo = new JPanel();
		JLabel tmp = new JLabel();

		JPanel nome_materia = new JPanel();
		JPanel data_app = new JPanel();
		JPanel aula = new JPanel();
		JPanel tipo = new JPanel();
		JPanel operazioni = new JPanel();
		
		
		nome_materia.setMaximumSize(d3);		nome_materia.setMinimumSize(d3);		nome_materia.setPreferredSize(d3);
		data_app.setMaximumSize(d1);		data_app.setMinimumSize(d1);		data_app.setPreferredSize(d1);
		aula.setMaximumSize(d2); 			aula.setMinimumSize(d2);			aula.setPreferredSize(d2);
		tipo.setMaximumSize(d2); 			tipo.setMinimumSize(d2);			tipo.setPreferredSize(d2);
		operazioni.setMaximumSize(d2); 	operazioni.setMinimumSize(d2);  	operazioni.setPreferredSize(d2);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(prenotazione.getMateria());
		nome_materia.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(dateFormat.format(prenotazione.getData_appello()));
		data_app.add(tmp);
		data_app.add(new JLabel());
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(prenotazione.getAula());
		aula.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(prenotazione.getTipo());
		tipo.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.X_AXIS ));
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(nome_materia);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(data_app);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(aula);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(tipo);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(operazioni);
		
		complessivo.setBorder(new LineBorder(Color.BLACK, 1));

		
		return complessivo;
		
	}
	
	public void visualizzaPrenotazioni(String matricola) {
		
		LinkedList<Prenotazione> prenotazioni = new LinkedList<Prenotazione>();
		JPanel complessivo = new JPanel();
		JPanel prenotazioniPanel = new JPanel();
		
		
		prenotazioni = ottieniPrenotazioniStudente(matricola);
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		
		prenotazioniPanel.setLayout(new BoxLayout(prenotazioniPanel, BoxLayout.Y_AXIS));
		
		prenotazioniPanel.add(getIntestazione());
		prenotazioniPanel.add(Box.createVerticalStrut(h));
		
		if(prenotazioni.size() > 0){
			
			for(int i=0; i<prenotazioni.size(); ++i){
				
				JPanel corrente = visualizzaPrenotazione(prenotazioni.get(i));
				prenotazioniPanel.add(corrente);
				prenotazioniPanel.add(Box.createVerticalStrut(h));
				
			}
			
		} else {
			
			prenotazioniPanel.add(new JLabel("Nessuna prenotazione effettuata"));
			
		}
		
		prenotazioniPanel.add(Box.createVerticalStrut(h));
		
		complessivo.add(prenotazioniPanel, BorderLayout.CENTER);
		InterfPrincipale.setCenterPanel(complessivo);
			
	}
	
}

