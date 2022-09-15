package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	Docente doc = new Docente();
	Studente stud = new Studente();
	
	
	public boolean effettuaAccesso(String utente, String id, String password) {
		
		boolean utenteVerificato = false;	
		
		if(id.length()<=10 && password.length()<=15) {
			
			if(utente.equals("Docente")) {
			
				String query = "SELECT username, password FROM docente WHERE username = " + " '"+ id + "' " + " AND password= " + " '" + password + "' ";
		
				try{
				
					Statement ps = MyConnection.getConnection().createStatement(); 
					ResultSet res = ps.executeQuery(query);
				
					if(res.next()) {
						utenteVerificato = true;
					} else {
						utenteVerificato = false;
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			
			} else if(utente.equals("Studente")) {
			
				String query = "SELECT matricola, password FROM studente WHERE matricola = " + " '"+ id +"' " + " AND password= " + " '" + password + "' ";
			
				try{
				
					Statement ps = MyConnection.getConnection().createStatement(); 
					ResultSet res = ps.executeQuery(query);
				
					if(res.next()) {
						utenteVerificato = true;
					} else {
						utenteVerificato = false;
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
			}else {
				utenteVerificato = false;
			}
			
		
		}else {
			utenteVerificato = false;
		}
	
		return utenteVerificato;
		
	}
	
	
	public JPanel loginComponent(){
		
		JPanel login = new JPanel();
		JPanel center = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JButton btnLogin = new JButton("Login");
		int h = 15;
		
		
		login.setLayout(new BorderLayout());
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		
		left.add(Box.createVerticalGlue());
		left.add(new JLabel("Tipo Utente: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Username: "));
		left.add(Box.createVerticalStrut(h));
		left.add(new JLabel("Password: "));
		left.add(Box.createVerticalStrut(h));
		left.add(Box.createVerticalGlue());
		
		
		final JTextField username = new JTextField(16);
		final JPasswordField password = new JPasswordField(16);
		final JComboBox<String> tipoUt = new JComboBox<String>();
		
		tipoUt.addItem(new String("Docente"));
		tipoUt.addItem(new String("Studente"));
		tipoUt.setMaximumSize(new Dimension(300,20));
		tipoUt.setPreferredSize(new Dimension(300,20));
		username.setMaximumSize(new Dimension(300,20));
		username.setPreferredSize(new Dimension(300,20));
		password.setMaximumSize(new Dimension(300,20));
		password.setPreferredSize(new Dimension(300,20));
		
		right.add(Box.createVerticalGlue());
		
		right.add(Box.createVerticalStrut(h*2));
		right.add(tipoUt);
		right.add(Box.createVerticalStrut(h));
		right.add(username);
		right.add(Box.createVerticalStrut(h));
		right.add(password);
		right.add(Box.createVerticalStrut(h));
		right.add(btnLogin);
		right.add(Box.createVerticalGlue());
		
		
		center.add(left);
		center.add(Box.createHorizontalStrut(2*h));
		center.add(right);

		btnLogin.addActionListener(new ActionListener() {
			
			JComboBox<String> cb = tipoUt;
			
			public void actionPerformed(ActionEvent e) {
				
				String tipoUt = (String)cb.getSelectedItem();
				String user = username.getText();
				char[] passChar = password.getPassword();
				String pass = new String(passChar);
				Messaggio mess = new Messaggio();
				boolean retval;
				
				ProfiloUtente profilo = new ProfiloUtente();
				
				retval = effettuaAccesso(tipoUt, user, pass);
				
				if(retval && tipoUt.equals("Docente")){
						
						Docente docP = new Docente();
						MenuDocente menu = new MenuDocente(user, pass);
						
						docP = profilo.ottieniProfiloDocente(user);
						profilo.visualizzaProfiloDocente(docP);
					
						
					} else if(retval && tipoUt.equals("Studente")){
						
						Studente studP = new Studente();
						MenuStudente menu = new MenuStudente(user, pass);
						
						studP = profilo.ottieniProfiloStudente(user);
						profilo.visualizzaProfiloStudente(studP);
							
						
					}else {
						
						mess.generaMessaggio("Username e/o Password Errati.");
						
					}

			}
		});


		login.add(center, BorderLayout.CENTER);
		
		return login;
	
	}

}
