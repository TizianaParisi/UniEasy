package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RegistrazioneEsame {

	
	public boolean registrazioneEsitoEsame(Esame esame, String esito) {
		
		String matr = esame.getMatStudente();
		String codMat = esame.getCodMateria();
		String voto = esame.getVoto();
		java.util.Date oggi = new java.util.Date(System.currentTimeMillis());
		java.sql.Date oggiSq = new java.sql.Date(oggi.getTime());
		boolean result = false;
		
		if(esito.equals("Promosso")) {
		
			String query = "INSERT INTO esame (cod_materia, mat_studente, data, voto) VALUES ('"+ codMat + "','" + matr + "','" + oggiSq + "','" + voto + "');";

			try{
			
				Statement ps = MyConnection.getConnection().createStatement(); 	
				ps.executeUpdate(query);
				
				result = true;
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else if (esito.equals("Bocciato")){
			
			// non viene inserito nessun esame perchè l'esame non è stato superato
			result = true;
		}
		
		return result;
	
	}

	
	public boolean rimozionePrenotazione(String mat_studente, String cod_appello) {
		
		boolean result = false;
		
		String query = "DELETE FROM prenotazione WHERE cod_appello =  '" + cod_appello + "' AND mat_studente = '" + mat_studente + "';";		
		
		try{
			
			Statement ps = MyConnection.getConnection().createStatement(); 
			ps.executeUpdate(query);
			result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public void visualizzaRegistrazioneEsame(Studente stud, Appello ap, Materia mat){
		
		JPanel complessivo = new JPanel();
		JPanel center = new JPanel();
		JFrame frame = new JFrame("Registrazione Esame");
		JPanel contentPane = new JPanel();
		int h = 10;
		DatiDaInserire dati = new DatiDaInserire(stud, ap, mat);
		InfoStudente infoStud = new InfoStudente(stud, ap);
				
		complessivo.setLayout(new BoxLayout(complessivo, BoxLayout.Y_AXIS));
		
		complessivo.add(infoStud);
		complessivo.add(Box.createVerticalStrut(h));
		complessivo.add(dati);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(new Rectangle(800, 400));
		frame.setVisible(true);
		
		
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		
		center.add(complessivo);
		frame.getContentPane().add(center, BorderLayout.CENTER);
		
	}
	
	private class InfoStudente extends JPanel{
		
		private static final long serialVersionUID = 1L;
		Font fontIntest = new Font("Times New Roman", Font.BOLD, 14);
		Font fontValue = new Font("Times New Roman", Font.ITALIC, 14);
		private int h = 10;
		
		public InfoStudente(Studente stud, Appello ap){
			
			JPanel left = new JPanel();
			JPanel right = new JPanel();
			JLabel tmp = new JLabel();
			
			left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
			right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
			
			tmp = new JLabel();
			tmp.setFont(fontIntest);
			tmp.setText("Matricola");
			left.add(Box.createVerticalStrut(h));
			left.add(tmp);
			
			tmp = new JLabel();
			tmp.setFont(fontIntest);
			tmp.setText("Cognome");
			left.add(Box.createVerticalStrut(h));
			left.add(tmp);
			
			tmp = new JLabel();
			tmp.setFont(fontIntest);
			tmp.setText("Nome");
			left.add(Box.createVerticalStrut(h));
			left.add(tmp);
			
			tmp = new JLabel();
			tmp.setFont(fontValue);
			tmp.setText(stud.getMatricola());
			right.add(Box.createVerticalStrut(h));
			right.add(tmp);
			
			tmp = new JLabel();
			tmp.setFont(fontValue);
			tmp.setText(stud.getCognome());
			right.add(Box.createVerticalStrut(h));
			right.add(tmp);
			
			tmp = new JLabel();
			tmp.setFont(fontValue);
			tmp.setText(stud.getNome());
			right.add(Box.createVerticalStrut(h));
			right.add(tmp);
						
			this.add(left);
			this.add(Box.createHorizontalStrut(h*2));
			this.add(right);
			
		}
		
	}
	
	private class DatiDaInserire extends JPanel{
		
		private static final long serialVersionUID = 1L;
		Dimension d2 = new Dimension(100, 25);
		private int h = 10;
		
		public DatiDaInserire(final Studente stud, final Appello ap, final Materia m){
			
			JPanel left = new JPanel();
			JPanel right = new JPanel();
			final JButton conferma = new JButton("Conferma");
			final JComboBox<String> esito = new JComboBox<String>();
			final JComboBox<String> voto = new JComboBox<String>();
			
			voto.setMaximumSize(d2);
			voto.setMinimumSize(d2);
			voto.setPreferredSize(d2);
			
			esito.addItem(new String("Promosso"));
			esito.addItem(new String("Bocciato"));
			
			for(int i=18; i<=30; ++i){
				voto.addItem(String.valueOf(i));
			}
			
			voto.addItem(new String("30L"));
			
			left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
			right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
			
			left.add(new JLabel("Esito: "));
			left.add(Box.createVerticalStrut(h*5/4));
			left.add(new JLabel("Voto: "));
			left.add(Box.createVerticalStrut(h*5/4));
			
			right.add(Box.createVerticalStrut(h*3));
			right.add(esito);
			right.add(Box.createVerticalStrut(h));
			right.add(voto);
			right.add(Box.createVerticalStrut(h));
			right.add(conferma);
			right.add(Box.createVerticalStrut(h));

			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			this.add(left);
			this.add(Box.createHorizontalStrut(3*h));
			this.add(right);
			
			esito.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {

					String current = new String();
					
					current = (String) esito.getSelectedItem();
					
					if(current.equalsIgnoreCase("Bocciato")){
						voto.setEnabled(false);
					} else {
						voto.setEnabled(true);
					}
				}
			});
			
			
			conferma.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					boolean retval1 = true;
					boolean retval2 = true;
					Messaggio mess = new Messaggio();
					Esame es = new Esame();
					String esitoString = (String) esito.getSelectedItem();
					String votoString = (String) voto.getSelectedItem();
					
					Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
					
					
					es.setCodMateria(m.getCodice());
					es.setMatStudente(stud.getMatricola());
					es.setData(date);
					es.setVoto(votoString);
					
					retval1 = registrazioneEsitoEsame(es, esitoString);
					retval2 = rimozionePrenotazione(stud.getMatricola(), ap.getCodice());
					
					
					conferma.setEnabled(false);
					voto.setEnabled(false);
					esito.setEnabled(false);
					
					if(retval1 && retval2)
						mess.generaMessaggio("Esame registrato con successo.");
					else
						mess.generaMessaggio("Errore registrazione esame.");
					
					IscrittiAppello iscritti = new IscrittiAppello();
					iscritti.visualizzaIscrittiAppello(ap);
					
				}
			});
		}
		
	}
	
}
