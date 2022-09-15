package com.mygroup.app.UniEasy;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class InterfPrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel north, east, west, center, south;

	public InterfPrincipale() {
		
		north = new JPanel();
		east = new JPanel();
		west = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(north, BorderLayout.NORTH);
		contentPane.add(east, BorderLayout.EAST);
		contentPane.add(west, BorderLayout.WEST);
		contentPane.add(center, BorderLayout.CENTER);
		contentPane.add(south, BorderLayout.SOUTH);
		
		setContentPane(contentPane);
		
		setVisible(true);
		
	}
	
	public static void setCenterPanel(JPanel p){
		center.removeAll();
		center.add(p);
		center.revalidate();
		center.repaint();
	}
	
	public static void setWestPanel(JPanel p){
		west.removeAll();
		west.add(p);
		west.revalidate();
		west.repaint();
	}
	
	public static void setEastPanel(JPanel p){
		east.removeAll();
		east.add(p);
		east.revalidate();
		east.repaint();
	}
	
	public static void resetPanel(){
		
		east.removeAll();
		east.revalidate();
		east.repaint();
		
		center.removeAll();
		center.revalidate();
		center.repaint();
		
		west.removeAll();
		west.revalidate();
		west.repaint();
		
		south.removeAll();
		south.revalidate();
		south.repaint();
		
	}
	
	public static void add(JPanel p){
		setCenterPanel(p);
	}
	

	public static void resetPanelCenter() {
		center.removeAll();
		center.revalidate();
		center.repaint();
		
	}
	
}

