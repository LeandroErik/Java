package ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Clases.Tema;
import DAO.TemaDAO;
import DAO.TemaDAOImpl;

public class NuevoTema extends JPanel {
	private JTextField CCodAlf;
	private JTextField CPalClav;
	private JTextField CDescripcion;
	private TemaDAO temaDao = new TemaDAOImpl();
	private Tema tem = new Tema();

	public NuevoTema(marco mar) {
		setLayout(null);
		
		JLabel lblNuevoTema = new JLabel("Nuevo Tema");
		lblNuevoTema.setBounds(174, 40, 80, 14);
		add(lblNuevoTema);
		
		JLabel lblCodigoCaracteres = new JLabel("Codigo (6 caracteres):");
		lblCodigoCaracteres.setBounds(40, 76, 126, 14);
		add(lblCodigoCaracteres);
		
		JLabel lblPalabraClave = new JLabel("Palabra clave:");
		lblPalabraClave.setBounds(40, 101, 109, 14);
		add(lblPalabraClave);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(40, 126, 94, 14);
		add(lblDescripcion);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(40, 151, 46, 14);
		add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(231, 151, 46, 14);
		add(lblHasta);
		
		CCodAlf = new JTextField();
		CCodAlf.setBounds(250, 73, 86, 20);
		add(CCodAlf);
		CCodAlf.setColumns(10);
		
		CPalClav = new JTextField();
		CPalClav.setBounds(250, 98, 86, 20);
		add(CPalClav);
		CPalClav.setColumns(10);
		
		CDescripcion = new JTextField();
		CDescripcion.setBounds(250, 123, 86, 20);
		add(CDescripcion);
		CDescripcion.setColumns(10);
		
		JButton button = new JButton("<--");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mar.setContentPane(new Inicio(mar));
				mar.setVisible(true);
				mar.validate();
			}
		});
		button.setBounds(40, 36, 66, 23);
		add(button);
		 
		JDateChooser Cdesde = new JDateChooser();
		Cdesde.setBounds(91, 151, 87, 20);
		add(Cdesde);
		
		JDateChooser Chasta = new JDateChooser();
		Chasta.setBounds(288, 151, 87, 20);
		add(Chasta);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tem.setCodAlf(CCodAlf.getText());
				tem.setDescribcion(CDescripcion.getText());
				tem.setPalclave(CPalClav.getText());
				tem.setDesde(Cdesde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				tem.setHasta(Chasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				
				temaDao.crearTema(tem);
				
				mar.setContentPane(new Inicio(mar));
				mar.setVisible(true);
				mar.validate();
			}
		});
		btnAceptar.setBounds(165, 226, 89, 23);
		add(btnAceptar);
		
	}
}
