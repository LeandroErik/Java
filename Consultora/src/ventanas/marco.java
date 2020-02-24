package ventanas;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class marco extends JFrame {       

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    static marco mar = new marco();

	public marco() {
        super();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setTitle("Consultora");
        this.setSize(600, 500);                                 
        this.setLocationRelativeTo(null);                       
        getContentPane().setLayout(null);                                   
        this.setResizable(false);                               
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }

    private void inicializarComponentes() {
    	JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu Marchivo = new JMenu("Archivo");
        menuBar.add(Marchivo);
        
        JMenuItem Isalir = new JMenuItem("Salir");
        Isalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.exit(0);
        	}
        });
        
        JMenuItem MNuevoSeguimiento = new JMenuItem("Nuevo Seguimiento");
        MNuevoSeguimiento.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		setContentPane(new NuevoSeguimiento(mar));
        		setVisible(true);
        		validate();
        	}
        });
        Marchivo.add(MNuevoSeguimiento);
        
        JSeparator separator = new JSeparator();
        Marchivo.add(separator);
        Marchivo.add(Isalir);
        
        JMenu mnAyuda = new JMenu("Ayuda");
        menuBar.add(mnAyuda);
        
        JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
        mnAyuda.add(mntmAcercaDe);
    }


    public static void main(String[] args) {

        mar.setContentPane(new Inicio(mar));
        mar.setVisible(true);
    }
}
