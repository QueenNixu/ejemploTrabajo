package mainPackage3;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class ventanaConexion extends JFrame{
	
	private Label lbl;
	private TextField tf;
	private Button btn;
	private JPasswordField contraseña;
	private static Connection Aux;
	
	public ventanaConexion() {
		//configuracion de la ventana
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Gestor Conexion");
		setSize(300,140);
		setVisible(false);
		setResizable(false);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		//configuracion de los componentes
		lbl= new Label("Usuario");
		add(lbl);
		
		tf = new TextField("",20);
		add(tf);
		
		lbl= new Label("Contrase�a");
		add(lbl);
		
		contraseña = new JPasswordField("",15);
		contraseña.setEditable(true);
		add(contraseña);
		
		btn = new Button("Conectar");
		btn.addActionListener(new btnConectarListener1());
		add(btn);
		
		btn = new Button("Atras");
		btn.addActionListener(new btnAtrasListener());
		add(btn);
	}
	//funcion para obtener la conexion con la base de datos
	/*public Connection getConnection() {
		return Aux;
	}*/
	//configuracion boton para conectarse a la base de datos
	class btnConectarListener1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String contraseñaStr = new String(contraseña.getPassword());
				Aux = Conexion.getConection(tf.getText(),contraseñaStr); //obtenemos la coneccion
				
				if(Aux != null) {
					dispose();
					@SuppressWarnings("unused")
					Alerta conf = new Alerta("Conectado a la base de datos.","good");
					mainPrueba.getVInterfaz().setVisible(true);
					tf.setText("");
					contraseña.setText("");
				}
			} finally {}
		}
	}
	//configuracion del boton para retroceder
	class btnAtrasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
			mainPrueba.getVInterfaz().setVisible(true);
		}
	}
}