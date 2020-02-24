package interfazCliente;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaz.conectarCliente;


public class marcoCliente extends JFrame {

	private static final long serialVersionUID = 1;
	
	static marcoCliente marco = new marcoCliente();
	
	public marcoCliente() 
	{
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		this.setTitle("Cliente");
        this.setSize(250,160);
        this.setLocationRelativeTo(null);                   
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args)
	{
		marco.setContentPane(new conectarCliente(marco));
		marco.validate();
		marco.setVisible(true);
	}


}
