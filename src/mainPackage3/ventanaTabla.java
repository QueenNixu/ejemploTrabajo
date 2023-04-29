package mainPackage3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ventanaTabla extends JFrame{
	//componente de la tabla
	private String[] titulos = {"Codigo de articulo","descripcion","Cantidad en stock"};
	private JTable tabla = new JTable();
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	
	private JPanel panelBotones = new JPanel();
	
	private JButton Aceptar = new JButton("Aceptar");
	
	private Object[][] datos;
	
	private boolean disposeB = false;
	
	public ventanaTabla (String codigo) {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		model = new DefaultTableModel(datos,titulos){
			  public boolean isCellEditable(int row, int column) {
				  return false;
			  }
	    };
	    
	    //try para rellenar la tabla con los datos
	    try {
	    	
			Connection con = Conexion.getCon();
			
			if(con != null && codigo != null) {
				
				Statement sql = con.createStatement();
				
				String consulta = "select * from factu_full_central_desa.dbo.ARTICULOS_STOCK_DEPO WHERE COD_ARTICULO= '"+codigo+"'";
				//String consulta = "select * from factu_full_central_desa.dbo.ARTICULOS_STOCK_DEPO WHERE COD_ARTICULO LIKE '"+codigo+"%'";
				
				ResultSet resultado = sql.executeQuery(consulta);
				
				if(!resultado.isBeforeFirst()) {
					throw new exception1();
				}
				while(resultado.next()) {
					model.addRow(new Object[] {resultado.getString(1),resultado.getString(2),resultado.getString(3)});
				}
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, ex.toString());
		} catch(exception1 e1) {
			@SuppressWarnings("unused")
			Alerta conf = new Alerta("No hay ninguna articulo con el codigo \""+codigo+"\"");
			disposeB = true;
		}
		
		tabla = new JTable();
		tabla.setModel(model);
		tabla.getTableHeader().setReorderingAllowed(false);
		scrollPane = new JScrollPane(tabla);
		
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		Aceptar.addActionListener(new AceptarListener());
		panelBotones.add(Aceptar);
		
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		setTitle("Tabla de stock");
		setSize(600,300);
		setResizable(false);
		setLocationRelativeTo(null);
		  
		tabla.getColumn("Codigo de articulo").setResizable(false);
		tabla.getColumn("descripcion").setResizable(false);
		tabla.getColumn("Cantidad en stock").setResizable(false);
		
		if(disposeB) {
			mainPrueba.getVInterfaz().setVisible(true);
			dispose();
		}
		else {
			mainPrueba.getVInterfaz().setVisible(false);
			setVisible(true);
		}
	}
	//configuracion del boton para retroceder
	class AceptarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			mainPrueba.getVInterfaz().setVisible(true);
		}
	}
}