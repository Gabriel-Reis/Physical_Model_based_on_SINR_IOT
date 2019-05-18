
public class conversor {

	public static double converte_dBm_W(double dBm) {
		return ( Math.pow(10, (dBm)/10) /1000);
	}
	
	public static double converte_W_dBm(double W) {
		//return ( 10*Math.log10(1000*W) );
		return 10*Math.log10(W*1000);
	}
	
	public static double converte_dBm_�W(double dBm) {
		return converte_W_�W(converte_dBm_W(dBm));
	}
	
	public static double converte_�W_dBm(double �W) {
		if(�W == 0)
			return 0;
		else
			return converte_W_dBm(converte_�W_W(�W));
	}
	
	public static double converte_W_nW(double W) {
		return ( W*1e+9 );
	}
	
	public static double converte_W_�W(double W) {
		return ( W*1000000 );
	}
	
	public static double converte_nW_�W(double nW) {
		return ( nW*0.001);
	}
	
	public static double converte_�W_W(double �W) {
		return ( �W*1e-6 );
	}
	
}
