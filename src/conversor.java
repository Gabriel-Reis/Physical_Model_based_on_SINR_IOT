
public class conversor {

	public static double converte_dBm_W(double dBm) {
		return ( Math.pow(10, (dBm)/10) /1000);
	}
	
	public static double converte_W_dBm(double W) {
		//return ( 10*Math.log10(1000*W) );
		return 10*Math.log10(W*1000);
	}
	
	public static double converte_dBm_µW(double dBm) {
		return converte_W_µW(converte_dBm_W(dBm));
	}
	
	public static double converte_µW_dBm(double µW) {
		if(µW == 0)
			return 0;
		else
			return converte_W_dBm(converte_µW_W(µW));
	}
	
	public static double converte_W_nW(double W) {
		return ( W*1e+9 );
	}
	
	public static double converte_W_µW(double W) {
		return ( W*1000000 );
	}
	
	public static double converte_nW_µW(double nW) {
		return ( nW*0.001);
	}
	
	public static double converte_µW_W(double µW) {
		return ( µW*1e-6 );
	}
	
}
