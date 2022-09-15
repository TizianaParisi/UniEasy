package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AppelliMateria extends JFrame{
	
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private int h = 15;
	
	static Dimension d1 = new Dimension(240, 20);
	static Dimension d2 = new Dimension(170, 20);
	static Dimension d3 = new Dimension(80, 20);
	static Dimension d4 = new Dimension(600, 40);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	static Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	static Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
	static int w = 10;
	
	
	public LinkedList<Appello> ottieniAppelliMateria(String codMat){
		
		LinkedList<Appello> appelli = new LinkedList<Appello>();
		
		String query = "SELECT * FROM appello WHERE cod_materia =  '" + codMat + "'";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				
				Appello corrente = new Appello();
				
				corrente.setCodice(res.getString("codice_app"));
				
				String dataprova = res.getString("data");
				Timestamp timestampData = Timestamp.valueOf(dataprova);
				corrente.setData(timestampData);
				
				corrente.setAula(res.getString("aula"));
				corrente.setTipo(res.getString("tipo"));
				corrente.setCodiceMateria("cod_materia");
				
				appelli.add(corrente);
				
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return appelli;
		
	}
	
	
	public int ottieniNumeroIscritti(String codAp) {
		
		int numeroIscritti = 0;
		
		String query = "SELECT * FROM prenotazione WHERE cod_appello = '" + codAp + "';";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next())
				numeroIscritti++;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return numeroIscritti;
		
	}
	
	
	public static JPanel getIntestazione(){
		
		JLabel tmp = new JLabel();
		JPanel data = new JPanel();
		JPanel aula = new JPanel();
		JPanel nIscr = new JPanel();
		JPanel tipo = new JPanel();
		
		JPanel intest = new JPanel();
		
		data.setMaximumSize(d2); 		data.setPreferredSize(d2); 		data.setMinimumSize(d2);
		nIscr.setMaximumSize(d3); 		nIscr.setPreferredSize(d3); 	nIscr.setMinimumSize(d3);
		tipo.setMaximumSize(d2); 		tipo.setPreferredSize(d1); 		tipo.setMinimumSize(d1);
		aula.setMaximumSize(d2); 		aula.setPreferredSize(d2); 		aula.setMinimumSize(d2);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Data Esame");
		data.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Iscritti");
		nIscr.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Modalità");
		tipo.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontIntest);
		tmp.setText("Aula");
		aula.add(tmp);
		
		
		intest.add(Box.createHorizontalStrut(w));
		intest.add(data);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(Box.createHorizontalStrut(w));
		intest.add(aula);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(nIscr);
		intest.add(Box.createHorizontalStrut(w));
		intest.add(tipo);
		
		intest.setLayout(new BoxLayout(intest, BoxLayout.X_AXIS));
		
		return intest;
		
	}
	
	
	public JPanel appelloComponent(final Appello ap, final String matricola) {
		
		JPanel complessivo = new JPanel();
		JLabel tmp = new JLabel();
		int nIscrInt = 0;
		JPanel data = new JPanel();
		JPanel nIscr = new JPanel();
		JPanel tipo = new JPanel();
		JPanel aula = new JPanel();
		
		data.setMaximumSize(d2); 		data.setPreferredSize(d2); 		data.setMinimumSize(d2);
		nIscr.setMaximumSize(d3); 		nIscr.setPreferredSize(d3); 	nIscr.setMinimumSize(d3);
		tipo.setMaximumSize(d2); 		tipo.setPreferredSize(d1); 		tipo.setMinimumSize(d1);
		aula.setMaximumSize(d2); 		aula.setPreferredSize(d2); 		aula.setMinimumSize(d2);

		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(dateFormat.format(ap.getData()));
		data.add(tmp);
		
		nIscrInt = ottieniNumeroIscritti(ap.getCodice());
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(String.valueOf(nIscrInt));
		nIscr.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getTipo());
		tipo.add(tmp);
		
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(ap.getAula());
		tipo.add(tmp);
		aula.add(tmp);
		
		
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(data);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(aula);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(nIscr);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(tipo);
		complessivo.add(Box.createHorizontalStrut(w));
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.X_AXIS));

		complessivo.setBorder(new LineBorder(Color.BLACK, 1));

		
		
		return complessivo;
		
	}
	
	
	public void visualizzaAppelliMateriaDisponibili(String codMateria, String matricola) {
		
		LinkedList<Appello> appelli = new LinkedList<Appello>();
		
		JPanel complessivo = new JPanel();
		JPanel appelliPanel = new JPanel();
		JPanel center = new JPanel();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		this.setVisible(true);
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		
		appelliPanel.setLayout(new BoxLayout(appelliPanel, BoxLayout.Y_AXIS));
		
		appelliPanel.add(getIntestazione());
		appelliPanel.add(Box.createVerticalStrut(h));
		
		
		appelli = ottieniAppelliMateria(codMateria);
		
		if(appelli.size() > 0){
			
			for(int i=0; i<appelli.size(); ++i){
				
				JPanel corrente = appelloComponent(appelli.get(i), matricola);
				appelliPanel.add(corrente);
				appelliPanel.add(Box.createVerticalStrut(h));
				
			}
			
		} else {
			
			appelliPanel.add(new JLabel(" NESSUN APPELLO DISPONIBILE "));
			
		}
		
		complessivo.add(Box.createVerticalStrut(h));
		complessivo.add(Box.createVerticalStrut(h));
		complessivo.add(appelliPanel, BorderLayout.CENTER);
		contentPane.add(Box.createVerticalStrut(h));
		contentPane.add(Box.createVerticalGlue());
		
		center.add(complessivo);
		contentPane.add(center, BorderLayout.CENTER);
	
	}
	
}
