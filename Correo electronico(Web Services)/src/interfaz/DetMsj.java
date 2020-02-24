package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.Mensaje;
import clases.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class DetMsj extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField receptor = new JTextField();
	private JTextField asunto = new JTextField();
	private JTextField emisor;
	private JTextField textField;


	/**
	 * Create the panel.
	 */
	public DetMsj(marcoCliente marco, Usuario user, Mensaje msj) {
		setLayout(null);
		
		JLabel lblPara = new JLabel("Receptor:");
		lblPara.setBounds(10, 44, 46, 14);
		add(lblPara);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 69, 46, 14);
		add(lblAsunto);
		
		JLabel lblTexto = new JLabel("Texto:");
		lblTexto.setBounds(10, 122, 46, 14);
		add(lblTexto);
		
		receptor.setBounds(66, 39, 162, 20);
		add(receptor);
		receptor.setColumns(10);
		receptor.setText(msj.getReceptor());
		receptor.setEditable(false);
		
		asunto.setBounds(66, 66, 162, 20);
		add(asunto);
		asunto.setColumns(10);
		asunto.setText(msj.getAsunto());
		asunto.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 122, 162, 113);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText(msj.getTexto());
		textArea.setEditable(false);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(460,330);
				marco.setContentPane(new interUser(marco, user));
				marco.validate();
			}
		});
		btnAtras.setBounds(10, 246, 89, 23);
		add(btnAtras);
		
		JLabel lblEmisor = new JLabel("Emisor:");
		lblEmisor.setBounds(10, 11, 46, 14);
		add(lblEmisor);
		
		emisor = new JTextField();
		emisor.setBounds(66, 8, 162, 20);
		add(emisor);
		emisor.setColumns(10);
		emisor.setText(msj.getEmisor());
		emisor.setEditable(false);
		
		JLabel lblFecha = new JLabel("fecha");
		lblFecha.setBounds(10, 94, 46, 14);
		add(lblFecha);
		
		textField = new JTextField();
		textField.setBounds(66, 91, 162, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(msj.getFecha().toString());
		textField.setEditable(false);

	}
}
