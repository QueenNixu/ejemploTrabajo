package mainPackage3;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class ventanaInterfaz extends JFrame{
	
	private Label lbl;
	private TextField tf;
	private Button btn;
	
	private String err;
	
	public ventanaInterfaz() {
		//configuracion de la ventana
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Gestor de stock");
		setSize(400,200);
		setVisible(false);
		setResizable(false);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		//configuracion de los componentes
		lbl= new Label("Ingrese codigo SKU:");
		add(lbl);
		
		tf = new TextField("",30);
		tf.setEditable(true);
		add(tf);
		
		btn = new Button("Ingresar");
		btn.addActionListener(new btnIngresarListener());
		add(btn);
		
		btn = new Button("Conectarse a una base de datos");
		btn.addActionListener(new btnConnectListener());
		add(btn);
	}
	//configuracion boton para conectarse a la base de datos
	class btnConnectListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			mainPrueba.getVConexion().setVisible(true);
		}
	}
	//boton para ingresar un codigo y obtener los articulos (a forma de tabla)
	class btnIngresarListener implements ActionListener {
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				
				Connection con = Conexion.getCon();
				
				if( (con != null) && !(tf.getText().isEmpty() ) && !(tf.getText().isBlank()) ){
					String cod = tf.getText();
					ventanaTabla vTabla = new ventanaTabla(cod);
				}
				else {
					err = "";
					if(con == null) {err+= "No esta conectado a la base de datos.\n";}
					throw new exception1();
				}
			}catch(exception1 e1) {
				if( (tf.getText().isEmpty() ) || (tf.getText().isBlank()) ) {err+= "Campo de texto vacio.";}
				Alerta warning = new Alerta(err);
			}
		}
	}
}