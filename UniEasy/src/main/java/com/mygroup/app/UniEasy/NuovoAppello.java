package com.mygroup.app.UniEasy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class NuovoAppello extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int h = 20;
	private int w = 10;
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	Date today = Calendar.getInstance().getTime();
	JButton aggiungiAppello = new JButton("Aggiungi");
	
	
	public boolean controlloDatiNuovoAppello(String cod_appello, String data, String ora, String aula, String modalita) {
		
		boolean correttezza = false;
		
		String espressioneCodiceAp = "^[0-9]{8}$";
		Pattern pCodiceAp = Pattern.compile(espressioneCodiceAp);	
		Matcher mCodiceAp = pCodiceAp.matcher(cod_appello);
		boolean matchCodiceApFound = mCodiceAp.matches();
		
		String espressioneData = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
		Pattern pData = Pattern.compile(espressioneData);	
		Matcher mData = pData.matcher(data);
		boolean matchDataFound = mData.matches();
		
		String espressioneOra = "^(?:[01]\\d|2[0123]):(?:[012345]\\d)";
		Pattern pOra = Pattern.compile(espressioneOra);	
		Matcher mOra = pOra.matcher(ora);
		boolean matchOraFound = mOra.matches();
		
		if(matchCodiceApFound && matchDataFound && matchOraFound && (aula.length()>1 && aula.length()<=20) && (modalita.length()<=20)) 
			correttezza = true;
		else
			correttezza = false;

		
		return correttezza;
		
	}
	
	
	public boolean aggiungereNuovoAppello(String cod_appello, Timestamp data, String aula, String modalita, String cod_materia) {
		
		Appello ap = new Appello();
		boolean retval = false;
		
		ap.setCodice(cod_appello);
		ap.setData(data);
        ap.setAula(aula);
        ap.setTipo(modalita);
		ap.setCodiceMateria(cod_materia);
		
		String query = "INSERT INTO appello (codice_app, data, aula, tipo, cod_materia) VALUES ('"+ ap.getCodice() + "', '" + ap.getData() + "', '" + ap.getAula() + "', '" + ap.getTipo() + "', '" + ap.getCodiceMateria() + "');";

		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			
			ps.executeUpdate(query);
			retval = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retval;
		
	}
	
	
	public LinkedList<Materia> caricaMaterieDocente(String username) {
		
		LinkedList<Materia> materie = new LinkedList<Materia>();
		
		String query = "SELECT * FROM materia WHERE user_docente = '" + username + "' ;";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				
				Materia corrente = new Materia();
			
				corrente.setCodice(res.getString("codice_mat"));
				corrente.setNome(res.getString("nome"));
				corrente.setCfu(res.getString("cfu"));
				corrente.setUserDocente(res.getString("user_docente"));
				
				materie.add(corrente);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return materie;
		
	}
	
	
	public void inserimentoNuovoAppello(final String username){
		
		final JComboBox<Materia> materieBox = new JComboBox<Materia>();
	
		LinkedList<Materia> materie = new LinkedList<Materia>();
		final JTextArea tipo = new JTextArea(3, 30);
		final JTextField aula = new JTextField();
		final JTextField ora = new JTextField("00:00");
		final JTextField cod_appello = new JTextField("00000000");
		final JTextField dataCh = new JTextField("2000-01-01");
		
		ora.setEnabled(true);
		materie = caricaMaterieDocente(username);
		
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel complessivo = new JPanel();
		
		
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		tipo.setBorder(new LineBorder(Color.BLACK, 1));
		tipo.setLineWrap(true);
		
		for(int i=0; i<materie.size(); ++i){
			materieBox.addItem(materie.get(i));
		} 
		
		left.add(new JLabel("Materia: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Codice appello: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Data: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Ora: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Aula: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Modalità esame: "));
		left.add(Box.createVerticalStrut(h));
		
		right.add(Box.createVerticalStrut(h*4));
		right.add(materieBox);
		right.add(Box.createVerticalStrut(h));
		right.add(cod_appello);
		right.add(Box.createVerticalStrut(h));
		right.add(dataCh);
		right.add(Box.createVerticalStrut(h));
		right.add(ora);
		right.add(Box.createVerticalStrut(h));
		right.add(aula);
		right.add(Box.createVerticalStrut(h));
		right.add(tipo);
		right.add(Box.createVerticalStrut(h));
		right.add(aggiungiAppello);
		right.add(Box.createVerticalStrut(h));

		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.X_AXIS));
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(left);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(right);
		complessivo.add(Box.createHorizontalStrut(w));
		
		JPanel center = new JPanel();
		center.add(complessivo);
		InterfPrincipale.setCenterPanel(center);
		
		aggiungiAppello.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				boolean correttezzaDati = false;
				boolean retvalue;
				Messaggio mess = new Messaggio();
				
				String dataEora = dataCh.getText() + " " + ora.getText();
				DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dataEora));
		        Timestamp ts = Timestamp.valueOf(localDateTime);
				
		        String codice_appello = cod_appello.getText();
		        Timestamp data = ts;
		        String aulaAp = aula.getText();
		        String modalita = tipo.getText();
		        Materia materia = (Materia)materieBox.getSelectedItem();
		        
		        
		        correttezzaDati = controlloDatiNuovoAppello(codice_appello, dataCh.getText(), ora.getText(), aulaAp, modalita);
		        
		        if(correttezzaDati)
		        	retvalue = aggiungereNuovoAppello(codice_appello, data, aulaAp, modalita, materia.getCodice());
		        else
		        	retvalue = false;
				
		        
		        
				if(retvalue) {
					
					mess.generaMessaggio("Appello aggiunto correttamente.");
					
					AppelliDocente visApp = new AppelliDocente();
					visApp.visualizzaElencoAppelliDocente(username);
					
				} else {
					
					mess.generaMessaggio("Impossibile aggiungere l'appello.");
					
				}
				
			}
			
		});
		
	}
	
}




