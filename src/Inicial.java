import java.util.ArrayList;

public class Inicial {

	public static void main(String[] args) {
		
		int alpha, beta;
		double ruido; 
		ArrayList<dispositivo> dispositivos = new ArrayList<>();
		
		dispositivos.add(new dispositivo("a", 1 , 0)); //potencia em dBm
		dispositivos.add(new dispositivo("b", -15, 4));
		dispositivos.add(new dispositivo("c", 0, 5));
		dispositivos.add(new dispositivo("d", 0, 7)); 
		alpha = 3;
		beta = 3;
		ruido = conversor.converte_nW_µW(10); 	//em nanoWatts
		
		
		System.out.println(Calculacomunicacao(alpha, beta, dispositivos, ruido));
	}

	public static boolean Calculacomunicacao(int alpha, int beta, ArrayList<dispositivo> dispositivos, double ruido){
		
		for(int i=0; i<dispositivos.size(); i++) {
			if(i > (dispositivos.size()-1)/2)
				break;
			
			dispositivo a = dispositivos.get(i);
			dispositivo b = dispositivos.get(dispositivos.size()-i-1);
			
			//Força de comunicação
			// potencia / distancia ^ alpha
			double forca = (a.getPower() / (Math.pow(Math.abs(a.getPosicao()-b.getPosicao()),alpha)));
			System.out.println(a.getNome() + " " + a.getPower() );
			// DIVIDE POR
			// Ruido + potencia / distancia ^ alpha para cada dispositivo
			double interferencia =  CalculaInterferencia(alpha, dispositivos, i);
			
			double result = forca / (ruido+interferencia);
			System.out.println("SINR(" +a.getNome() +"-" +b.getNome() +") = " +result);

			if ( result < beta)
				return false;
		}
		return true;
	}
	
	public static double CalculaInterferencia(int alpha, ArrayList<dispositivo> dispositivos, int pos) {
		double retorno = 0;
		dispositivo alvo = dispositivos.get(dispositivos.size()-pos-1);
		for(int i=0; i<dispositivos.size(); i++) {
			if (i != pos && i!= dispositivos.size()-pos-1) {	
				dispositivo emissor = dispositivos.get(i);			
				int dist = Math.abs(alvo.getPosicao()-emissor.getPosicao());
				retorno +=  emissor.getPower() / (Math.pow(dist,alpha));
				
			}
		}
		return retorno;
	}
	
}












