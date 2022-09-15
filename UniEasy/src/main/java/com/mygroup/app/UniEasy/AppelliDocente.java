package com.mygroup.app.UniEasy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AppelliDocente extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int h = 10; 
	final static Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	final static Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
	static int w = 10;	
	static Dimension d1 = new Dimension(150, 25);
	static Dimension d2 = new Dimension(115, 25);
	static Dimension d3 = new Dimension(90, 25);
	
	LinkedList<Appello> appelli = new LinkedList<Appello>();
	Materia mat = new Materia();
	
	
	public LinkedList<Appello> ottieniAppelliDelDocente(String username){
			
		LinkedList<Appello> appelli = new LinkedList<Appello>();
		
		String query = "SELECT codice_app,data,aula,tipo,cod_materia FROM appello INNER JOIN materia ON appello.cod_materia = materia.codice_mat AND materia.user_docente='" + username + "';";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				
				Appello corrente = new Appello();
				corrente.setCodice(res.getString("codice_app"));
				Timestamp dataDate = res.getTimestamp("data");
				
				corrente.setData(dataDate);
				corrente.setAula(res.getString("aula"));
				corrente.setTipo(res.getString("tipo"));
				corrente.setCodiceMateria(res.getString("cod_materia"));
				appelli.add(corrente);
					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appelli;
		
	}
	
	
	public Materia caricaMateriaDiAppello(String codAppello){
		
		Materia mat = new Materia();
		
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
	
	
	public int caricaNumeroIscritti(Appello ap) {
		
		String codAp = ap.getCodice();
		int numeroIscritti = 0;
		
		String query = "SELECT * FROM prenotazione WHERE cod_appello = '" + codAp + "';";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				numeroIscritti++;
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return numeroIscritti;
		
	}
	
	
	public JPanel visualizzaSingoloAppello(final Appello ap, String username) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		JPanel complessivo = new JPanel();
		JLabel tmp = new JLabel();
		JPanel codMateria = new JPanel();
		JPanel nome = new JPanel();
		JPanel data = new JPanel();
		JPanel aula = new JPanel();
		JPanel modalita = new JPanel();
		JPanel nIscritti = new JPanel();
		JPanel operazioni = new JPanel();
	
		JButton visualizza = new JButton("Vedi");
		
		codMateria.setMaximumSize(d3); 			codMateria.setMinimumSize(d3); 			codMateria.setPreferredSize(d3);
		nome.setMaximumSize(d1); 				nome.setMinimumSize(d1)	;	 			nome.setPreferredSize(d1);
		data.setMaximumSize(d1); 				data.setMinimumSize(d1);	 			data.setPreferredSize(d1);
		aula.setMaximumSize(d3); 				aula.setMinimumSize(d3);	 			aula.setPreferredSize(d3);
		modalita.setMaximumSize(d3); 				modalita.setMinimumSize(d3); 				modalita.setPreferredSize(d3);
		nIscritti.setMaximumSize(d2); 			nIscritti.setMinimumSize(d2); 			nIscritti.setPreferredSize(d2);

		mat = caricaMateriaDiAppello(ap.getCodice());
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getCodice());
		codMateria.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(mat.getNome());
		nome.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(dateFormat.format(ap.getData()));
		data.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getAula());
		aula.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getTipo());
		modalita.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		int nIsc = caricaNumeroIscritti(ap);
		tmp.setText(String.valueOf(nIsc));
		nIscritti.add(tmp);
		
		
		operazioni.add(visualizza);
		operazioni.add(Box.createHorizontalStrut(w));
					
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(codMateria);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(nome);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(data);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(aula);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(modalita);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(nIscritti);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(operazioni);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.setBorder(new LineBorder(Color.black, 1));
		
		
		visualizza.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				IscrittiAppello visApp = new IscrittiAppello();		
				visApp.visualizzaIscrittiAppello(ap);
				
			}
		});
		
		
		return complessivo;
		
	}
	
	
	public void visualizzaElencoAppelliDocente(String username) {
		
		
		JPanel complessivo = new JPanel();
		JPanel appelliPanel = new JPanel();
		
		appelliPanel.setLayout(new BoxLayout(appelliPanel, BoxLayout.Y_AXIS));
		
		appelliPanel.add(getIntestazione());
		
		appelli = ottieniAppelliDelDocente(username);
		
		for(int i=0; i<appelli.size(); ++i){
			
			appelliPanel.add(visualizzaSingoloAppello(appelli.get(i), username));
			appelliPanel.add(Box.createVerticalStrut(h));
			
		}
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(new JLabel("APPELLI CONFERMATI"));
		complessivo.add(Box.createVerticalStrut(h));
		complessivo.add(appelliPanel);
		complessivo.add(Box.createVerticalStrut(h));
		
		InterfPrincipale.setCenterPanel(complessivo);
		
	}
	
	
	public static JPanel getIntestazione() {
		
		JLabel tmp = new JLabel();
		JPanel insegnamento = new JPanel();
		JPanel codice_materia = new JPanel();
		JPanel data_appello = new JPanel();
		JPanel aula = new JPanel();
		JPanel modalita = new JPanel();
		JPanel nIscritti = new JPanel();
		JPanel opzioni = new JPanel();

		JPanel intest = new JPanel();
		
		codice_materia.setMaximumSize(d3); 				codice_materia.setMinimumSize(d3)	;	 			codice_materia.setPreferredSize(d3);
		insegnamento.setMaximumSize(d1); 			insegnamento.setMinimumSize(d1); 			insegnamento.setPreferredSize(d1);
		data_appello.setMaximumSize(d1); 				data_appello.setMinimumSize(d1); 				data_appello.setPreferredSize(d1);
		aula.setMaximumSize(d3); 				aula.setMinimumSize(d3); 				aula.setPreferredSize(d3);
		modalita.setMaximumSize(d3); 				modalita.setMinimumSize(d3);	 			modalita.setPreferredSize(d3);
		nIscritti.setMaximumSize(d2); 			nIscritti.setMinimumSize(d2); 			nIscritti.setPreferredSize(d2);

		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Codice");
		codice_materia.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Insegnamento");
		insegnamento.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Data");
		data_appello.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Aula");
		aula.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Modalità");
		modalita.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("N. Iscritti");
		nIscritti.add(tmp);
		
	
		intest.setLayout(new BoxLayout(intest, BoxLayout.X_AXIS));
		
		intest.add(Box.createHorizontalStrut(w));
		intest.add(codice_materia);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(insegnamento);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(data_appello);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(aula);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(modalita);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(nIscritti);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(opzioni);
		
		intest.add(Box.createHorizontalStrut(w));
		
		intest.setBorder(new LineBorder(Color.black, 1));
		
		return intest;
		
	}

}
