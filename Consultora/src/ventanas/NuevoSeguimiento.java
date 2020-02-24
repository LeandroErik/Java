package ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Clases.Operador;
import DAO.OperadorDAO;
import DAO.OperadorDAOImpl;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoSeguimiento extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField CCantDeMinxDia;
	private JTextField CCantidadDeMinxHorCentr;
	private JTextField CcantDeNotas;
	private JTextField CcantDeTapas;
	private OperadorDAO op = new OperadorDAOImpl();

	public NuevoSeguimiento(marco mar) {
		setLayout(null);
		
		JLabel lblNuevoSeguimiento = new JLabel("Nuevo Seguimiento");
		lblNuevoSeguimiento.setBounds(165, 24, 102, 14);
		add(lblNuevoSeguimiento);
		
		JLabel lblNombreDelOperador = new JLabel("Nombre del Operador");
		lblNombreDelOperador.setBounds(42, 68, 119, 14);
		add(lblNombreDelOperador);
		
		JComboBox NomDeOperadores = new JComboBox();
		NomDeOperadores.setBounds(317, 65, 73, 20);
		String[] vect = new String[5];
		int i=0;
		try {
			for (Operador o : op.listar()) {
				vect[i]=o.getNombre();
				i++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		NomDeOperadores.setModel(new DefaultComboBoxModel<String>(vect));
		
		add(NomDeOperadores);
		
		JLabel lblTelevision = new JLabel("Television");
		lblTelevision.setBounds(42, 93, 79, 14);
		add(lblTelevision);
		
		JLabel lblCantidadDeMinutos = new JLabel("Cantidad de minutos por dia:");
		lblCantidadDeMinutos.setBounds(52, 118, 138, 14);
		add(lblCantidadDeMinutos);
		
		JLabel lblCantidadDeMinutos_1 = new JLabel("Cantidad de minutos en Horario central");
		lblCantidadDeMinutos_1.setBounds(42, 143, 187, 14);
		add(lblCantidadDeMinutos_1);
		
		CCantDeMinxDia = new JTextField();
		CCantDeMinxDia.setBounds(317, 115, 86, 20);
		add(CCantDeMinxDia);
		CCantDeMinxDia.setColumns(10);
		
		CCantidadDeMinxHorCentr = new JTextField();
		CCantidadDeMinxHorCentr.setBounds(317, 152, 86, 20);
		add(CCantidadDeMinxHorCentr);
		CCantidadDeMinxHorCentr.setColumns(10);
		
		JLabel lblRevistasYDiarios = new JLabel("Revistas y Diarios:");
		lblRevistasYDiarios.setBounds(42, 168, 46, 14);
		add(lblRevistasYDiarios);
		
		JLabel lblCantidadDeNotas = new JLabel("Cantidad de notas");
		lblCantidadDeNotas.setBounds(52, 193, 96, 14);
		add(lblCantidadDeNotas);
		
		JLabel lblCantidadDeTapas = new JLabel("Cantidad de Tapas");
		lblCantidadDeTapas.setBounds(52, 218, 119, 14);
		add(lblCantidadDeTapas);
		
		CcantDeNotas = new JTextField();
		CcantDeNotas.setBounds(317, 190, 86, 20);
		add(CcantDeNotas);
		CcantDeNotas.setColumns(10);
		
		CcantDeTapas = new JTextField();
		CcantDeTapas.setBounds(317, 215, 86, 20);
		add(CcantDeTapas);
		CcantDeTapas.setColumns(10);
		
		JButton button = new JButton("<--");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mar.setContentPane(new Inicio(mar));
				mar.setVisible(true);
				mar.validate();
			}
		});
		button.setBounds(42, 20, 62, 23);
		add(button);
		
	}
}
