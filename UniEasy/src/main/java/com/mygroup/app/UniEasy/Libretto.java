package com.mygroup.app.UniEasy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Libretto extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int h = 5; 
	private static int w = 10;
	Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
	
	
	public Corso ottieniCorsoStudente(String matStud) {
		
		Corso corso = new Corso();
		
		corso.setCodice("0000");
		corso.setNome("-");
		
		String query = "SELECT corso.codice, corso.nome FROM corso INNER JOIN studente ON corso.codice = studente.cod_corso and studente.matricola = '" + matStud + "';";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			if(res.next()){
				corso.setCodice(res.getString("codice"));
				corso.setNome(res.getString("nome"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return corso;
		
	}
		
	public LinkedList<Materia> ottieniListaMaterieDelCorso(String cod_corso){
		
		LinkedList<Materia> materie = new LinkedList<Materia>();
		
		String query = "SELECT * FROM materia  WHERE materia.cod_corso = '" + cod_corso + "';";
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				
				Materia matC = new Materia();
				
				matC.setCodice(res.getString("codice_mat"));
				matC.setNome(res.getString("nome"));
				matC.setCfu(res.getString("cfu"));
				matC.setUserDocente(res.getString("user_docente"));
				matC.setCodiceCorso(res.getString("cod_corso"));
				materie.add(matC);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return materie;
		
	}
	
	
	public LinkedList<Esame> ottieniEsamiSostenuti(String cod_corso, String matricola){
		
		LinkedList<Esame> esami = new LinkedList<Esame>();
		
		String query = "SELECT * FROM esame INNER JOIN materia ON materia.codice_mat = esame.cod_materia AND materia.cod_corso='" + cod_corso + "' AND esame.mat_studente='" + matricola + "';";

		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ResultSet res = ps.executeQuery(query);
			
			while(res.next()){
				
				Esame esC = new Esame();
				
				esC.setCodMateria(res.getString("cod_materia"));
				esC.setMatStudente(res.getString("mat_studente"));
				esC.setData(res.getDate("data"));
				esC.setVoto(res.getString("voto"));
				
				esami.add(esC);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return esami;
		
	}
	
	
	public JPanel corsoInfoComponent(Corso c){
		
		JPanel corso = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		this.setLayout(new BoxLayout(corso, BoxLayout.X_AXIS));
		
		JLabel nome = new JLabel("Nome Corso: ");
		JLabel codice = new JLabel("Codice Corso: ");
		nome.setFont(fontIntest);
		codice.setFont(fontIntest);
		
		JLabel nomeV = new JLabel(c.getNome());
		JLabel codiceV = new JLabel(c.getCodice());
		nomeV.setFont(fontValue);
		codiceV.setFont(fontValue);
		
		left.add(Box.createVerticalStrut(h));
		left.add(nome);
		left.add(Box.createVerticalStrut(h));
		left.add(codice);
		left.add(Box.createVerticalStrut(h));
		
		right.add(Box.createVerticalStrut(h));
		right.add(nomeV);
		right.add(Box.createVerticalStrut(h));
		right.add(codiceV);
		right.add(Box.createVerticalStrut(h));
		
		corso.add(left);
		corso.add(right);
		
		corso.setMaximumSize(new Dimension(250,50));
		corso.setPreferredSize(new Dimension(300,50));
		
		return corso;
		
	}

	
	public static JPanel getIntestazione(){
		
		Dimension d1 = new Dimension(180, 20);
		Dimension d2 = new Dimension(60, 20);

		JPanel codice = new JPanel();	
		JPanel nome = new JPanel();
		JPanel cfu = new JPanel();
		JPanel stato = new JPanel();
		JPanel voto = new JPanel();
		JPanel data = new JPanel();
		
		JPanel op = new JPanel();
		
		codice.setMaximumSize(d2);		codice.setPreferredSize(d2); 	codice.setMinimumSize(d2);
		nome.setMaximumSize(d1);		nome.setPreferredSize(d1); 		nome.setMinimumSize(d1);
		cfu.setMaximumSize(d2);			cfu.setPreferredSize(d2); 		cfu.setMinimumSize(d2);
		stato.setMaximumSize(d1);		stato.setPreferredSize(d1); 	stato.setMinimumSize(d1);
		voto.setMaximumSize(d2);		voto.setPreferredSize(d2); 		voto.setMinimumSize(d2);
		op.setMaximumSize(d1);			op.setPreferredSize(d1); 		op.setMinimumSize(d1);
		data.setMaximumSize(d1);		data.setPreferredSize(d1); 		data.setMinimumSize(d1);

		codice.add(new JLabel("ID"));
		nome.add(new JLabel("Nome Materia"));
		cfu.add(new JLabel("CFU"));
		stato.add(new JLabel("Stato"));
		voto.add(new JLabel("Voto"));
		data.add(new JLabel("Data"));
		op.add(new JLabel("Operazione"));
		
		JPanel complessivo = new JPanel();
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.X_AXIS));
		
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(codice);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(nome);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(cfu);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(stato);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(voto);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(data);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(op);
		complessivo.add(Box.createHorizontalStrut(w));
		
		return complessivo;
		
	}
	
	public JPanel materiaComponent(final Materia materia, Esame esame, final String mat) {
	
		Dimension d1 = new Dimension(180, 20);
		Dimension d2 = new Dimension(60, 20);

		JPanel complessivo = new JPanel();
		
		JLabel tmp = new JLabel();
		JPanel codice = new JPanel();	
		JPanel nome = new JPanel();
		JPanel cfu = new JPanel();
		JPanel stato = new JPanel();
		JPanel voto = new JPanel();
		JPanel data = new JPanel();
		JPanel pulsante = new JPanel();
		JButton visualizzaAppelli = new JButton("Visualizza Appelli");
		
		codice.setMaximumSize(d2);	codice.setPreferredSize(d2); 	codice.setMinimumSize(d2);
		nome.setMaximumSize(d1);	nome.setPreferredSize(d1); 		nome.setMinimumSize(d1);
		cfu.setMaximumSize(d2);		cfu.setPreferredSize(d2); 		cfu.setMinimumSize(d2);
		stato.setMaximumSize(d1);	stato.setPreferredSize(d1); 	stato.setMinimumSize(d1);
		voto.setMaximumSize(d2);	voto.setPreferredSize(d2); 		voto.setMinimumSize(d2);
		data.setMaximumSize(d1);	data.setPreferredSize(d1); 		data.setMinimumSize(d1);
		
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(materia.getCodice());
		codice.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(materia.getNome());
		nome.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		tmp.setText(String.valueOf(materia.getCfu()));
		cfu.add(tmp);
		
		tmp = new JLabel();
		tmp.setFont(fontValue);
		
		if(esame.getData() != null){
	
			Format formatter = new SimpleDateFormat("dd/MM/yyyy");
			String s = formatter.format(esame.getData());
			tmp.setText(s);
		
		} else {
			tmp.setText("");
		}
		
		data.add(tmp);
		
		pulsante.add(visualizzaAppelli);
		
		if(!esame.getVoto().equals("-")){

			stato.add(new JLabel("Sostenuto"));
			voto.add(new JLabel(String.valueOf(esame.getVoto())));
			visualizzaAppelli.setEnabled(false);
		
		} else {
			stato.add(new JLabel("/"));
			voto.add(new JLabel("/"));
			visualizzaAppelli.setEnabled(true);
		}
		
	
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.X_AXIS));
		
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(codice);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(nome);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(cfu);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(stato);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(voto);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(data);
		complessivo.add(Box.createHorizontalStrut(w));
		complessivo.add(pulsante);
		complessivo.add(Box.createHorizontalStrut(w));
		
		complessivo.setBorder(new LineBorder(Color.black, 1));
		
		
		visualizzaAppelli.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				AppelliMateria appelliMateria = new AppelliMateria();
				
				appelliMateria.visualizzaAppelliMateriaDisponibili(materia.getCodice(), mat);
				
			}
			
		});
		
		return complessivo;
		
	}
	
	
	public void visualizzaLibrettoStudente(String mat){
		
		Corso corso = new Corso();
		LinkedList<Materia> materie = new LinkedList<Materia>();
		LinkedList<Esame> esamiSostenuti = new LinkedList<Esame>();
		JPanel materiePanel = new JPanel();
		JPanel complessivo = new JPanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		corso = ottieniCorsoStudente(mat);
		materie = ottieniListaMaterieDelCorso(corso.getCodice());
		esamiSostenuti = ottieniEsamiSostenuti(corso.getCodice(), mat);
		
		materiePanel.setLayout(new BoxLayout(materiePanel, BoxLayout.Y_AXIS));
		materiePanel.add(getIntestazione());
		materiePanel.add(Box.createVerticalStrut(h));
		
		
		for(int i=0; i<materie.size(); ++i){
			
			Materia mat_i = materie.get(i);
			Esame es_i = new Esame();
			
			for(int j=0;j<esamiSostenuti.size();j++) {
			
				if(esamiSostenuti.get(j).getCodMateria().equals(mat_i.getCodice())) {
					
					es_i.setCodMateria(esamiSostenuti.get(j).getCodMateria()); 
					
					es_i.setData(esamiSostenuti.get(j).getData());
					es_i.setMatStudente(esamiSostenuti.get(j).getMatStudente());
					es_i.setVoto(esamiSostenuti.get(j).getVoto());
					break;
					
				} else {
					
					es_i.setCodMateria(mat_i.getCodice());
					es_i.setMatStudente(mat);
					es_i.setVoto("-");
					
				}
			}
			
			materiePanel.add(materiaComponent(mat_i, es_i, mat));
			materiePanel.add(Box.createVerticalGlue());
			materiePanel.add(Box.createVerticalStrut(h));
			
		}
		
		
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(corsoInfoComponent(corso));
		complessivo.add(Box.createVerticalStrut(h*3));
		complessivo.add(materiePanel);
		complessivo.add(Box.createVerticalGlue());
		
		InterfPrincipale.setCenterPanel(complessivo);

	}
	
}

