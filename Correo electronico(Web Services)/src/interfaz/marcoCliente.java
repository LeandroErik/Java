package interfaz;

import javax.swing.JFrame;

public class marcoCliente extends JFrame {

private static final long serialVersionUID = 1;
	
	static marcoCliente marco = new marcoCliente();
	
	public marcoCliente() 
	{
		this.setTitle("Cliente");
        this.setSize(250,150);
        this.setLocationRelativeTo(null);                   
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		marco.setContentPane(new inicio(marco));
		marco.validate();
		marco.setVisible(true);
	}

}
