package com.mygroup.app.UniEasy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class IscrittiAppello {
	
	static int w = 10, y = 30;
	static Dimension d1 = new Dimension(150, y);
	static Dimension d2 = new Dimension(60, y);
	static Dimension d3 = new Dimension(50, y);
	private int h = 15;
	Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
	
	Materia mat = new Materia();
	LinkedList<Studente> studenti = new LinkedList<Studente>();
	

	public LinkedList<Studente> ottieniStudentiIscritti(Appello ap){
		
		LinkedList<Studente> studenti = new LinkedList<Studente>();
		
		String query = "SELECT matricola,password,nome,cognome,email,telefono FROM studente JOIN prenotazione ON prenotazione.cod_appello = '" + ap.getCodice() + "' AND studente.matricola = prenotazione.mat_studente;";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				
				Studente corrente = new Studente();
			
				corrente.setMatricola(res.getString("matricola"));
				corrente.setPassword(res.getString("password"));
				corrente.setNome(res.getString("nome"));
				corrente.setCognome(res.getString("cognome"));
				corrente.setEmail(res.getString("email"));
				corrente.setTelefono(res.getString("telefono"));
				
				studenti.add(corrente);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return studenti;
		
	}
	
	
	public Materia ottieniMateriaAppello(Appello ap) {
		
		Materia mat = new Materia();
		String codAppello = ap.getCodice();
		
		String query = "SELECT codice_mat,nome,cfu,user_docente FROM appello JOIN materia ON appello.cod_materia = materia.codice_mat AND appello.codice_app = '" + codAppello + "'";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			if(res.next()){
				
				mat.setCodice(res.getString("codice_mat"));
				mat.setNome(res.getString("nome"));
				mat.setCfu(res.getString("cfu"));
				mat.setUserDocente(res.getString("user_docente"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mat;
		
		
	}

	
	public void visualizzaIscrittiAppello(Appello ap) {
		
		JPanel studentiPanel = new JPanel();
		JPanel infoAppello = getInfoAppello(ap);
		JPanel complessivo = new JPanel();
		
		studentiPanel.setLayout(new BoxLayout(studentiPanel, BoxLayout.Y_AXIS));
		studentiPanel.add(getIntestazione());
		studentiPanel.add(Box.createVerticalStrut(h));
		
		studenti = ottieniStudentiIscritti(ap);
		
		for(int i=0; i<studenti.size(); ++i){

			studentiPanel.add(dettagliStudente(studenti.get(i), mat, ap));
			studentiPanel.add(Box.createVerticalStrut(h));
			
		}
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(Box.createVerticalStrut(3*h));
		complessivo.add(infoAppello);
		complessivo.add(Box.createVerticalStrut(h*3));
		complessivo.add(studentiPanel);
		
		InterfPrincipale.setCenterPanel(complessivo);
		
	}
	
	
	public JPanel dettagliStudente(Studente stud, Materia mat, Appello ap) {
		
		JLabel tmp = new JLabel();
		JPanel matricola = new JPanel();	
		JPanel nome = new JPanel();
		JPanel cognome = new JPanel();
		JPanel email = new JPanel();
		JPanel telefono = new JPanel();
		
		
		JPanel complex = new JPanel();
		
		matricola.setMaximumSize(d2);		matricola.setPreferredSize(d2); 	matricola.setMinimumSize(d2);
		nome.setMaximumSize(d1);			nome.setPreferredSize(d1); 			nome.setMinimumSize(d1);
		cognome.setMaximumSize(d1);			cognome.setPreferredSize(d1); 		cognome.setMinimumSize(d1);
		email.setMaximumSize(d1);			email.setPreferredSize(d1); 		email.setMinimumSize(d1);
		telefono.setMaximumSize(d1);		telefono.setPreferredSize(d1); 		telefono.setMinimumSize(d1);

		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(stud.getMatricola());
		matricola.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(stud.getCognome());
		cognome.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(stud.getNome());
		nome.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(stud.getEmail());
		email.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(stud.getTelefono());
		telefono.add(tmp);
		
		complex.setLayout(new BoxLayout(complex, BoxLayout.X_AXIS));
		
		complex.add(Box.createHorizontalStrut(w));
		complex.add(matricola);
		complex.add(Box.createHorizontalStrut(w));
		complex.add(cognome);
		complex.add(Box.createHorizontalStrut(w));
		complex.add(nome);
		complex.add(Box.createHorizontalStrut(w));
		complex.add(email);
		complex.add(Box.createHorizontalStrut(w));
		complex.add(telefono);
		complex.add(Box.createHorizontalStrut(w));
		
		complex.setBorder(new LineBorder(Color.black, 1));
		
		
		return complex;
		
	}

	
	public JPanel getIntestazione(){
		
		JLabel tmp = new JLabel();
		JPanel matricola = new JPanel();	
		JPanel nome = new JPanel();
		JPanel cognome = new JPanel();
		JPanel email = new JPanel();
		JPanel telefono = new JPanel();
		JPanel intest = new JPanel();
		
		matricola.setMaximumSize(d2);		matricola.setPreferredSize(d2); 	matricola.setMinimumSize(d2);
		nome.setMaximumSize(d1);			nome.setPreferredSize(d1); 			nome.setMinimumSize(d1);
		cognome.setMaximumSize(d1);			cognome.setPreferredSize(d1); 		cognome.setMinimumSize(d1);
		email.setMaximumSize(d1);			email.setPreferredSize(d1); 		email.setMinimumSize(d1);
		telefono.setMaximumSize(d1);		telefono.setPreferredSize(d1); 		telefono.setMinimumSize(d1);

		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Matricola");
		matricola.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Cognome");
		cognome.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Nome");
		nome.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Email");
		email.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Telefono");
		telefono.add(tmp);
		
		intest.setLayout(new BoxLayout(intest, BoxLayout.X_AXIS));
		
		intest.add(Box.createHorizontalStrut(w));
		intest.add(matricola);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(cognome);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(nome);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(email);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(telefono);
		intest.add(Box.createHorizontalStrut(w));
		
		
		return intest;
		
	}
	
	
	public JPanel getInfoAppello(Appello ap){
		
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel complessivo = new JPanel();
		JLabel tmp = new JLabel();
		
		
		mat = ottieniMateriaAppello(ap);
		
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("CodiceAppello: " );
		left.add(tmp);
		left.add(Box.createVerticalStrut(h));
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Codice Materia: " );
		left.add(tmp);
		left.add(Box.createVerticalStrut(h));
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Materia: " );
		left.add(tmp);
		left.add(Box.createVerticalStrut(h));
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getCodice());
		right.add(tmp);
		right.add(Box.createVerticalStrut(h));
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getCodiceMateria());
		right.add(tmp);
		right.add(Box.createVerticalStrut(h));
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(mat.getNome());
		right.add(tmp);
		right.add(Box.createVerticalStrut(h));
		
		complessivo.add(left);
		complessivo.add(Box.createHorizontalStrut(3*h));
		complessivo.add(right);
		complessivo.add(Box.createHorizontalStrut(h*3));
		
		return complessivo;
		
	}
	
}
