package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuStudente extends JPanel{

	private static final long serialVersionUID = 1L;
	private int h = 20;
	
	Studente studente = new Studente();
	
	public MenuStudente(final String mat, String pass){
		
		this.setLayout(new BorderLayout());
	
		JPanel west = new JPanel();
		JButton profilo = new JButton("Profilo");
		JButton libretto = new JButton("Libretto");
		JButton prenotazioni = new JButton("Prenotazioni");
		JButton logout = new JButton("Logout");
		
		
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		west.add(Box.createVerticalStrut(h));
		west.add(profilo);
		west.add(Box.createVerticalStrut(h));
		west.add(libretto);
		west.add(Box.createVerticalStrut(h));
		west.add(prenotazioni);
		west.add(Box.createVerticalStrut(h));
		west.add(logout);
		west.add(Box.createVerticalStrut(h));

		InterfPrincipale.resetPanel();
		InterfPrincipale.setWestPanel(west);
		
		
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Login login = new Login();
				InterfPrincipale.resetPanel();
				InterfPrincipale.add(login.loginComponent());
				
				}
	
			}
		
		);
		
		
		libretto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Libretto libretto = new Libretto();
				
				libretto.visualizzaLibrettoStudente(mat);
			
			}
		});
		
		
		profilo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				ProfiloUtente vp = new ProfiloUtente();
				studente = vp.ottieniProfiloStudente(mat);
				vp.visualizzaProfiloStudente(studente);
				
			}
			
		});
		
		
		prenotazioni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				PrenotazioneStudente prenotazioniStud = new PrenotazioneStudente();
				
				prenotazioniStud.visualizzaPrenotazioni(mat);
				
			}
			
		});
		
	}
	

}

