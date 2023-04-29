package mainPackage3;

public class mainPrueba {
	
	private static ventanaInterfaz vInter = new ventanaInterfaz();
	private static ventanaConexion vConex = new ventanaConexion();
	
	public static void main(String[] args) {
		vInter.setVisible(true);
	}
	
	public static ventanaInterfaz getVInterfaz() {
		return vInter;
	}
	public static ventanaConexion getVConexion() {
		return vConex;
	}
}