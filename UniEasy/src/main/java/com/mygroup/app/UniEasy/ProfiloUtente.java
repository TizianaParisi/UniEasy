package com.mygroup.app.UniEasy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfiloUtente extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	public Docente ottieniProfiloDocente(String username) {
	
		Docente doc = new Docente();
		
		String query = "SELECT * FROM docente WHERE username = " + " '"+ username +"';";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			if(res.next()){
				
				doc.setCognome(res.getString("Cognome"));
				doc.setEmail(res.getString("Email"));
				doc.setNome(res.getString("Nome"));
				doc.setTelefono(res.getString("Telefono"));
				doc.setUsername(res.getString("Username"));
				doc.setPassword(res.getString("Password"));
				
			} else {
				
				doc = null;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return doc;
		
	}
	
	public Studente ottieniProfiloStudente(String matricola) {
		
		Studente stud = new Studente();
		
		String query = "SELECT * FROM studente WHERE matricola = " + " '"+ matricola +"';";

		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			if(res.next()){
				
				stud.setMatricola(res.getString("matricola"));
				stud.setPassword(res.getString("password"));
				stud.setNome(res.getString("nome"));
				stud.setCognome(res.getString("cognome"));
				stud.setEmail(res.getString("email"));
				stud.setTelefono(res.getString("telefono"));
				stud.setCorso(res.getString("cod_corso"));
				
				
			} else {
				
				stud = null;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stud;
		
	}
	
	
	public void visualizzaProfiloDocente(final Docente docente) {
		
		int h = 15;
		Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
		Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
		
		JPanel datiDoc = new JPanel();		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel pulsanti = new JPanel();
		JPanel complessivo = new JPanel();

		JButton modifica = new JButton("Modifica Dati");
		
		datiDoc.setLayout(new BoxLayout(datiDoc, BoxLayout.X_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		left.add(Box.createVerticalStrut(h));
		JLabel id = new JLabel("ID Utente: ");			id.setFont(fontIntest);
		JLabel nome = new JLabel("Nome: ");				nome.setFont(fontIntest);
		JLabel cognome = new JLabel("Cognome: ");		cognome.setFont(fontIntest);
		JLabel email = new JLabel("E-mail: ");			email.setFont(fontIntest);
		JLabel telefono = new JLabel("Telefono: ");		telefono.setFont(fontIntest);
		left.add(id);								
		left.add(Box.createVerticalStrut(h));
		left.add(nome);
		left.add(Box.createVerticalStrut(h));
		left.add(cognome);
		left.add(Box.createVerticalStrut(h));
		left.add(email);
		left.add(Box.createVerticalStrut(h));
		left.add(telefono);
		left.add(Box.createVerticalStrut(h));
		
		JLabel matricolaV = new JLabel("    " + docente.getUsername());
		JLabel nomeV = new JLabel("    " + docente.getNome());
		JLabel cognomeV = new JLabel("    " + docente.getCognome());
		JLabel emailV = new JLabel("    " + docente.getEmail());
		JLabel telefonoV = new JLabel("    "+ docente.getTelefono());
		
		matricolaV.setFont(fontValue);
		nomeV.setFont(fontValue);
		cognomeV.setFont(fontValue);
		emailV.setFont(fontValue);
		telefonoV.setFont(fontValue);
		
		right.add(Box.createVerticalStrut(h));
		right.add(matricolaV);
		right.add(Box.createVerticalStrut(h));
		right.add(nomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(cognomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(emailV);
		right.add(Box.createVerticalStrut(h));
		right.add(telefonoV);
		right.add(Box.createVerticalStrut(h));
		
		datiDoc.add(left);
		datiDoc.add(right);
		
		pulsanti.add(modifica);
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
    	complessivo.add(new JLabel("IL TUO PROFILO"));
		complessivo.add(Box.createVerticalStrut(2*h));
		complessivo.add(datiDoc);
		complessivo.add(Box.createVerticalStrut(5*h));
		complessivo.add(pulsanti);
		complessivo.add(Box.createVerticalGlue());
		
		InterfPrincipale.setCenterPanel(complessivo);
		
		modifica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ModificaUtente modifica = new ModificaUtente();
				
				modifica.modificaProfiloDocenteComponent(docente);
				
			}
			
		});
		
	}
	
		
		
	
	
	public void visualizzaProfiloStudente(final Studente studente) {
		
		int h = 15;
		Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
		Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
		
		JPanel datiStud = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel pulsanti = new JPanel();
		JPanel complessivo = new JPanel();
		
		JButton modifica = new JButton("Modifica Dati");
		
		datiStud.setLayout(new BoxLayout(datiStud, BoxLayout.X_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

		left.add(Box.createVerticalStrut(h));
		JLabel matricola = new JLabel("Matricola: ");	matricola.setFont(fontIntest);
		JLabel nome = new JLabel("Nome: ");				nome.setFont(fontIntest);
		JLabel cognome = new JLabel("Cognome: ");		cognome.setFont(fontIntest);
		JLabel email = new JLabel("E-mail: ");			email.setFont(fontIntest);
		JLabel telefono = new JLabel("Telefono: ");		telefono.setFont(fontIntest);
		left.add(matricola);								
		left.add(Box.createVerticalStrut(h));
		left.add(nome);
		left.add(Box.createVerticalStrut(h));
		left.add(cognome);
		left.add(Box.createVerticalStrut(h));
		left.add(email);
		left.add(Box.createVerticalStrut(h));
		left.add(telefono);
		left.add(Box.createVerticalStrut(h));
		
		JLabel matricolaV = new JLabel("    " + studente.getMatricola());
		JLabel nomeV = new JLabel("    " + studente.getNome());
		JLabel cognomeV = new JLabel("    " + studente.getCognome());
		JLabel emailV = new JLabel("    " + studente.getEmail());
		JLabel telefonoV = new JLabel("    "+ studente.getTelefono());
		
		matricolaV.setFont(fontValue);
		nomeV.setFont(fontValue);
		cognomeV.setFont(fontValue);
		emailV.setFont(fontValue);
		telefonoV.setFont(fontValue);
		
		right.add(Box.createVerticalStrut(h));
		right.add(matricolaV);
		right.add(Box.createVerticalStrut(h));
		right.add(nomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(cognomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(emailV);
		right.add(Box.createVerticalStrut(h));
		right.add(telefonoV);
		right.add(Box.createVerticalStrut(h));
		
		datiStud.add(left);
		datiStud.add(right);
		
		pulsanti.add(modifica);
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(new JLabel("IL TUO PROFILO"));
		complessivo.add(Box.createVerticalStrut(2*h));
		complessivo.add(datiStud);
		complessivo.add(Box.createVerticalStrut(5*h));
		complessivo.add(pulsanti);
		complessivo.add(Box.createVerticalGlue());
		
		InterfPrincipale.setCenterPanel(complessivo);
		
		
		modifica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ModificaUtente modifica = new ModificaUtente();
				modifica.modificaProfiloStudenteComponent(studente);
			}
			
		});
		
		
		
	}
	
}
