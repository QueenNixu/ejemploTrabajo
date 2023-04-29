package mainPackage3;


import javax.swing.JOptionPane;

public class Alerta {
	public Alerta(String a) {
        JOptionPane.showMessageDialog(
                 null, //padre
                 a, //mensaje
                 "ERROR", // título
                 JOptionPane.INFORMATION_MESSAGE); 
                }
	
	public Alerta(String a, String b) {
		
		//if(b.equals("good"))
        JOptionPane.showMessageDialog(
                 null, //padre
                 a, //mensaje
                 "SUCCESS", // título
                 JOptionPane.INFORMATION_MESSAGE);
                }
}
