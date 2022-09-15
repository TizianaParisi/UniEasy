package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuDocente extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int h = 20; 
	
	public MenuDocente(final String user, String pass){
	
		JPanel west = new JPanel();
		JButton profilo = new JButton("Profilo");
		JButton appelli = new JButton("Appelli");
		JButton logout = new JButton("Logout");
		
		this.setLayout(new BorderLayout());
		
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		west.add(Box.createVerticalStrut(h));
		west.add(profilo);
		west.add(Box.createVerticalStrut(h));
		west.add(appelli);
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
		});
		
		
		profilo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Docente docente = new Docente();
				ProfiloUtente vp = new ProfiloUtente();
				
				docente = vp.ottieniProfiloDocente(user);
				vp.visualizzaProfiloDocente(docente);
				
			}
			
		});
		
		
		appelli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AppelliDocente appelliDoc = new AppelliDocente();
				
				appelliDoc.visualizzaElencoAppelliDocente(user);
				
			}
			
		});
		
	}

}
