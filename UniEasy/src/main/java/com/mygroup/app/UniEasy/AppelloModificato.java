package com.mygroup.app.UniEasy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AppelloModificato {
	
	private int h = 10;
	private int hL = (8/7)*h;
	Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
	Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
	
	
	public Appello verificaCorrettezzaDatiInseriti(Appello appello, String cod_materia, String data, String ora, String aula, String modalita) {
		
		Appello appelloMod = new Appello();
		
		appelloMod.setCodice(appello.getCodice());
		appelloMod.setCodiceMateria(cod_materia);
		
		String espressioneData = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
		Pattern pData = Pattern.compile(espressioneData);	
		Matcher mData = pData.matcher(data);
		boolean matchDataFound = mData.matches();
		
		String espressioneOra = "^(?:[01]\\d|2[0123]):(?:[012345]\\d)";
		Pattern pOra = Pattern.compile(espressioneOra);	
		Matcher mOra = pOra.matcher(ora);
		boolean matchOraFound = mOra.matches();
		
		
		if(matchDataFound && matchOraFound) {
			
			String dataEora = data + " " + ora;
			DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dataEora));
	        Timestamp ts = Timestamp.valueOf(localDateTime);
			appelloMod.setData(ts);
			
		} else {
			appelloMod.setData(appello.getData());
		}	
		
		
		if(aula.length()>1 && aula.length()<=20)
			appelloMod.setAula(aula);
		else
			appelloMod.setAula(appello.getAula());
		
		
		if(modalita.length()<=20)
			appelloMod.setTipo(modalita);
		else
			appelloMod.setTipo(appello.getTipo());
		
		return appelloMod;
		
	}
	
	public boolean modificaInformazioniAppello(Appello appello, Appello appelloMod) {
		
		boolean resp = false;
		
		if(appello.getData()!=appelloMod.getData() || appello.getAula()!=appelloMod.getAula() || appello.getTipo()!=appelloMod.getTipo()) {
		
			String query = "UPDATE appello SET data='" + appelloMod.getData() + "', aula='" + appelloMod.getAula()
							+ "', tipo='" + appelloMod.getTipo() + "', cod_materia='" + appelloMod.getCodiceMateria()
							+"' WHERE codice_app='" 
							+ appelloMod.getCodice() +"';";
		
			try{
			
				Statement ps = MyConnection.getConnection().createStatement(); 
			
				ps.executeUpdate(query); 
				resp = true;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} else {
			
			// l'appello non ha subito modifiche quindi non viene aggiornato
			resp = true;
			
		}
		
		return resp;
		
	}
	
	
	public void modificaAppelloSwing(final Appello app, final Materia mat, final String userDoc) {
		
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
		JLabel codice = new JLabel("Codice: ");	codice.setFont(fontIntest);
		JLabel insegnamento = new JLabel("Insegnamento: ");				insegnamento.setFont(fontIntest);
		JLabel data = new JLabel("Data: ");	data.setFont(fontIntest);
		JLabel ora = new JLabel("Ora: ");				ora.setFont(fontIntest);
		JLabel aula = new JLabel("Aula: ");		aula.setFont(fontIntest);
		JLabel modalita = new JLabel("Modalità: ");			modalita.setFont(fontIntest);
		
		left.add(codice);								
		left.add(Box.createVerticalStrut(hL));
		left.add(insegnamento);								
		left.add(Box.createVerticalStrut(hL));
		left.add(data);								
		left.add(Box.createVerticalStrut(hL));
		left.add(ora);
		left.add(Box.createVerticalStrut(hL));
		left.add(aula);
		left.add(Box.createVerticalStrut(hL));
		left.add(modalita);
		left.add(Box.createVerticalStrut(hL));
		
		
		JTextField codiceJ = new JTextField(app.getCodice());
		JTextField insegnamentoJ = new JTextField(mat.getNome());		
		
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat oraFormat = new SimpleDateFormat("HH:mm");
		
		final JTextField dataJ = new JTextField(dataFormat.format(app.getData()));
		final JTextField oraJ = new JTextField(oraFormat.format(app.getData()));
		final JTextField aulaJ = new JTextField(app.getAula());
		final JTextField modalitaJ = new JTextField(app.getTipo());
		
		codiceJ.setEnabled(false);
		insegnamentoJ.setEnabled(false);
		dataJ.setBounds(35, 63, 36, 20);
		oraJ.setEnabled(true);
		modalitaJ.setEnabled(true);
		
		right.add(Box.createVerticalStrut(h));
		right.add(codiceJ);
		right.add(Box.createVerticalStrut(h));
		right.add(insegnamentoJ);
		right.add(Box.createVerticalStrut(h));
		right.add(dataJ);
		right.add(Box.createVerticalStrut(h));
		right.add(oraJ);
		right.add(Box.createVerticalStrut(h));
		right.add(aulaJ);
		right.add(Box.createVerticalStrut(h));
		right.add(modalitaJ);
		
		right.add(Box.createVerticalStrut(h));
		
		datiDoc.add(left);
		datiDoc.add(right);
		
		pulsanti.add(annulla);
		pulsanti.add(conferma);
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		complessivo.add(new JLabel("MODIFICA APPELLO"));
		complessivo.add(Box.createVerticalStrut(2*h));
		complessivo.add(datiDoc);
		complessivo.add(Box.createVerticalStrut(5*h));
		complessivo.add(pulsanti);
		complessivo.add(Box.createVerticalGlue());
		
		InterfPrincipale.setCenterPanel(complessivo);
		
		
		conferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Appello apMod = new Appello();
				
				apMod = verificaCorrettezzaDatiInseriti(app, mat.getCodice(), dataJ.getText(), oraJ.getText(), aulaJ.getText(), modalitaJ.getText());
				
				modificaInformazioniAppello(app, apMod);
				
				
				AppelliDocente listaApp = new AppelliDocente();
				listaApp.visualizzaElencoAppelliDocente(userDoc);
				
			}
		});
		
		
		annulla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AppelliDocente listaApp = new AppelliDocente();
				
				listaApp.visualizzaElencoAppelliDocente(userDoc);
				
			}
		});
		
	}

}
