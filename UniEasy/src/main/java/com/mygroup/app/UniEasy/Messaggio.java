package com.mygroup.app.UniEasy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Messaggio extends JOptionPane{

	private static final long serialVersionUID = 1L;

	public void generaMessaggio(String messaggio) {
		
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, messaggio);
		
	}
	
}
