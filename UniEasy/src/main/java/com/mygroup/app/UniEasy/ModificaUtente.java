package com.mygroup.app.UniEasy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModificaUtente {
	
	public Docente modificaDatiProfiloDocente(Docente doc, String email, String tel, String pass) {
		
		Docente docenteModificato = new Docente();
		String username = doc.getUsername();
		
		// L'indirizzo email deve essere composto da numeri o lettere, dal simbolo @, dal dominio composto da numeri e lettere, il simbolo . e da lettere per il paese
		
		String espressioneEmail = "^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-.]?[0-9a-z])*\\.[a-z]{2,4}$";
		Pattern pEmail = Pattern.compile(espressioneEmail);	
		Matcher mEmail = pEmail.matcher(email);
		boolean matchEmailFound = mEmail.matches();
		
		// La password deve avere un minimo di otto caratteri, un massimo di 15 caratteri e almeno una lettera e un numero
		
		String espressionePass = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,15}$";
		Pattern pPass = Pattern.compile(espressionePass);	
		Matcher mPass = pPass.matcher(pass);
		boolean matchPassFound = mPass.matches();
		
		// Il numero di telefono deve essere costituito da 10 cifre e la prima è sempre 3
		
		String espressioneTel = "^3\\d{9}$";
		Pattern pTel = Pattern.compile(espressioneTel);	
		Matcher mTel = pTel.matcher(tel);
		boolean matchTelFound = mTel.matches();
		
		docenteModificato.setNome(doc.getNome());
		docenteModificato.setCognome(doc.getCognome());
		docenteModificato.setUsername(doc.getUsername());
		
		
		// Se i dati inseriti rispettano i formati le informazioni sono aggiornate altrimenti si mantengono quelle attuali
		
		if(matchEmailFound)
			docenteModificato.setEmail(email);
		else
			docenteModificato.setEmail(doc.getEmail());
		
		if(matchTelFound)
			docenteModificato.setTelefono(tel);
		else
			docenteModificato.setTelefono(doc.getTelefono());
		
		if(matchPassFound)
			docenteModificato.setPassword(pass);
		else
			docenteModificato.setPassword(doc.getPassword());
		
		
		String query = "UPDATE docente SET telefono='" + docenteModificato.getTelefono() + "', email='" + docenteModificato.getEmail() +"', password='" + docenteModificato.getPassword() + "' WHERE username='" + username +"';";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return docenteModificato;	
		
	}
	
	
	public Studente modificaDatiProfiloStudente(Studente stud, String email, String tel, String pass) {
		
		Studente studenteModificato = new Studente();
		String matricola = stud.getMatricola();
	
		// L'indirizzo email deve essere composto da numeri o lettere, dal simbolo @, dal dominio composto da numeri e lettere, il simbolo . e da lettere per il paese

		String espressioneEmail = "^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-.]?[0-9a-z])*\\.[a-z]{2,4}$";
		Pattern pEmail = Pattern.compile(espressioneEmail);	
		Matcher mEmail = pEmail.matcher(email);
		boolean matchEmailFound = mEmail.matches();
		
		// La password deve avere un minimo di otto caratteri, un massimo di 15 caratteri e almeno una lettera e un numero

		String espressionePass = "((?=.*[0-9])(?=.*[a-zA-Z]).{8,20})";
		Pattern pPass = Pattern.compile(espressionePass);	
		Matcher mPass = pPass.matcher(pass);
		boolean matchPassFound = mPass.matches();
		
		// Il numero di telefono deve essere costituito da 10 cifre e la prima è sempre 3

		String espressioneTel = "^3\\d{9}$";
		Pattern pTel = Pattern.compile(espressioneTel);	
		Matcher mTel = pTel.matcher(tel);
		boolean matchTelFound = mTel.matches();
		
		studenteModificato.setNome(stud.getNome());
		studenteModificato.setCognome(stud.getCognome());
		studenteModificato.setMatricola(stud.getMatricola());
		
		// Se i dati inseriti rispettano i formati le informazioni sono aggiornate altrimenti si mantengono quelle attuali
		
		if(matchEmailFound)
			studenteModificato.setEmail(email);
		else
			studenteModificato.setEmail(stud.getEmail());
		
		if(matchTelFound)
			studenteModificato.setTelefono(tel);
		else
			studenteModificato.setTelefono(stud.getTelefono());
		
		if(matchPassFound)
			studenteModificato.setPassword(pass);
		else
			studenteModificato.setPassword(stud.getPassword());
			
		String query = "UPDATE studente SET telefono='" + studenteModificato.getTelefono() + "', email='" + studenteModificato.getEmail() +"', password='" + studenteModificato.getPassword() + "' WHERE matricola='" + matricola +"';";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return studenteModificato;
			
	}

	
	public void modificaProfiloDocenteComponent(final Docente doc) {
		
		int h = 10;
		int hL = (8/7)*h;
		Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
		
		JPanel datiDoc = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel pulsanti = new JPanel();
		JPanel complessivo = new JPanel();
		
		JButton conferma = new JButton("Conferma");
		JButton annulla = new JButton("Annulla");
		
		datiDoc.setLayout(new BoxLayout(datiDoc, BoxLayout.X_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

		left.add(Box.createVerticalStrut(h));
		JLabel username = new JLabel("Username: ");	username.setFont(fontIntest);
		JLabel nome = new JLabel("Nome: ");				nome.setFont(fontIntest);
		JLabel cognome = new JLabel("Cognome: ");		cognome.setFont(fontIntest);
		JLabel email = new JLabel("E-mail: ");			email.setFont(fontIntest);
		JLabel telefono = new JLabel("Telefono: ");		telefono.setFont(fontIntest);
		JLabel password = new JLabel("Password: ");		password.setFont(fontIntest); 
		left.add(username);								
		left.add(Box.createVerticalStrut(hL));
		left.add(nome);
		left.add(Box.createVerticalStrut(hL));
		left.add(cognome);
		left.add(Box.createVerticalStrut(hL));
		left.add(email);
		left.add(Box.createVerticalStrut(hL));
		left.add(telefono);
		left.add(Box.createVerticalStrut(hL));
		left.add(password);
		
		left.add(Box.createVerticalStrut(hL));
		
		
		JTextField usernameV = new JTextField(doc.getUsername());
		JTextField nomeV = new JTextField(doc.getNome());		
		JTextField cognomeV = new JTextField(doc.getCognome());
		final JTextField emailV = new JTextField(doc.getEmail());
		final JTextField telefonoV = new JTextField(doc.getTelefono());		
		final JTextField passwordV = new JTextField(doc.getPassword());
		
		usernameV.setEnabled(false);
		nomeV.setEnabled(false);
		cognomeV.setEnabled(false);
		emailV.setEnabled(true);
		telefonoV.setEnabled(true);
		passwordV.setEnabled(true);
		
		right.add(Box.createVerticalStrut(h));
		right.add(usernameV);
		right.add(Box.createVerticalStrut(h));
		right.add(nomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(cognomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(emailV);
		right.add(Box.createVerticalStrut(h));
		right.add(telefonoV);
		right.add(Box.createVerticalStrut(h));
		right.add(passwordV);
		right.add(Box.createVerticalStrut(h));
		
		datiDoc.add(left);
		datiDoc.add(right);
		
		pulsanti.add(annulla);
		pulsanti.add(conferma);
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(new JLabel("MODIFICA DEL PROFILO"));
		complessivo.add(Box.createVerticalStrut(2*h));
		complessivo.add(datiDoc);
		complessivo.add(Box.createVerticalStrut(5*h));
		complessivo.add(pulsanti);
		complessivo.add(Box.createVerticalGlue());
		
		InterfPrincipale.setCenterPanel(complessivo);
		
		
		conferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				String email = new String();
				String telefono = new String();
				String password = new String();
				Docente docAgg = new Docente();
				
				email = emailV.getText();
				telefono = telefonoV.getText();
				password = passwordV.getText();
				
				docAgg = modificaDatiProfiloDocente(doc, email, telefono, password);
		
				ProfiloUtente profilo = new ProfiloUtente();
				profilo.visualizzaProfiloDocente(docAgg);
				
			}
		});
		
		
		annulla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ProfiloUtente profilo = new ProfiloUtente();
				profilo.visualizzaProfiloDocente(doc);
				
			}
		});
		
	}
	
	public void modificaProfiloStudenteComponent(final Studente stud) {
		
		int h = 10;
		int hL = (8/7)*h;
		Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	
		JPanel datiDoc = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel pulsanti = new JPanel();
		JPanel complessivo = new JPanel();
		
		JButton conferma = new JButton("Conferma");
		JButton annulla = new JButton("Annulla");
		
		datiDoc.setLayout(new BoxLayout(datiDoc, BoxLayout.X_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

		left.add(Box.createVerticalStrut(h));
		JLabel username = new JLabel("Username: ");	username.setFont(fontIntest);
		JLabel nome = new JLabel("Nome: ");				nome.setFont(fontIntest);
		JLabel cognome = new JLabel("Cognome: ");		cognome.setFont(fontIntest);
		JLabel email = new JLabel("E-mail: ");			email.setFont(fontIntest);
		JLabel telefono = new JLabel("Telefono: ");		telefono.setFont(fontIntest);
		JLabel password = new JLabel("Password: ");		password.setFont(fontIntest); 
		left.add(username);								
		left.add(Box.createVerticalStrut(hL));
		left.add(nome);
		left.add(Box.createVerticalStrut(hL));
		left.add(cognome);
		left.add(Box.createVerticalStrut(hL));
		left.add(email);
		left.add(Box.createVerticalStrut(hL));
		left.add(telefono);
		left.add(Box.createVerticalStrut(hL));
		left.add(password);
		
		left.add(Box.createVerticalStrut(hL));
		
		
		JTextField usernameV = new JTextField(stud.getMatricola());
		JTextField nomeV = new JTextField(stud.getNome());		
		JTextField cognomeV = new JTextField(stud.getCognome());
		final JTextField emailV = new JTextField(stud.getEmail());
		final JTextField telefonoV = new JTextField(stud.getTelefono());		
		final JTextField passwordV = new JTextField(stud.getPassword());
		
		usernameV.setEnabled(false);
		nomeV.setEnabled(false);
		cognomeV.setEnabled(false);
		emailV.setEnabled(true);
		telefonoV.setEnabled(true);
		passwordV.setEnabled(true);
		
		right.add(Box.createVerticalStrut(h));
		right.add(usernameV);
		right.add(Box.createVerticalStrut(h));
		right.add(nomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(cognomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(emailV);
		right.add(Box.createVerticalStrut(h));
		right.add(telefonoV);
		right.add(Box.createVerticalStrut(h));
		right.add(passwordV);
		right.add(Box.createVerticalStrut(h));
		
		datiDoc.add(left);
		datiDoc.add(right);
		
		pulsanti.add(annulla);
		pulsanti.add(conferma);
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(new JLabel("MODIFICA DEL PROFILO"));
		complessivo.add(Box.createVerticalStrut(2*h));
		complessivo.add(datiDoc);
		complessivo.add(Box.createVerticalStrut(5*h));
		complessivo.add(pulsanti);
		complessivo.add(Box.createVerticalGlue());
		
		InterfPrincipale.setCenterPanel(complessivo);
		
		
		conferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String email = new String();
				String telefono = new String();
				String password = new String();
				Studente studAgg = new Studente();
				
				email = emailV.getText();
				telefono = telefonoV.getText();
				password = passwordV.getText();
				
				studAgg = modificaDatiProfiloStudente(stud, email, telefono, password);		
				
				ProfiloUtente profilo = new ProfiloUtente();
				profilo.visualizzaProfiloStudente(studAgg);
				
			}
		});
		
		
		annulla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ProfiloUtente profilo = new ProfiloUtente();
				profilo.visualizzaProfiloStudente(stud);
				
			}
		});
		
	}
	
}

