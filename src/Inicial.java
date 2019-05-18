import java.util.ArrayList;

public class Inicial {

	public static void main(String[] args) {
		
		int alpha, beta;
		double ruido; 
		ArrayList<dispositivo> dispositivos = new ArrayList<>();
		
		dispositivos.add(new dispositivo("a", -25 , 0)); //potencia em dBm
		dispositivos.add(new dispositivo("b", -25, 4));
		dispositivos.add(new dispositivo("c", 0, 5));
		dispositivos.add(new dispositivo("d", 0, 7)); 
		alpha = 3;
		beta = 3;
		ruido = conversor.converte_nW_�W(10); 	//em nanoWatts
		
		if(TestaValores(alpha, beta, dispositivos, ruido))
			System.out.println(dispositivos.toString());
		else
			System.out.println("Not soluble");
	
			//System.out.println(Calculacomunicacao(alpha, beta, dispositivos, ruido));
			//System.out.println(dispositivos.toString());
	}

	public static boolean TestaValores(int alpha, int beta, ArrayList<dispositivo> dispositivos, double ruido) {
		if(Calculacomunicacao(alpha, beta, dispositivos, ruido))
			return true;
		int tentativas = 0;
		int acertos = 0;
		double limite = conversor.converte_dBm_�W(25);
		while(dispositivos.get(0).getPower() < limite) {
			dispositivos.get(0).adddBm(1);
			tentativas++;
			if(Calculacomunicacao(alpha, beta, dispositivos, ruido)) {
				System.out.println("Tentativas: " +tentativas);
				acertos++;
				System.out.println(dispositivos.toString() +acertos);
				//return true;
			}
			while(dispositivos.get(1).getPower() < limite) {
				dispositivos.get(1).adddBm(1);
				tentativas++;
				if(Calculacomunicacao(alpha, beta, dispositivos, ruido)) {
					acertos++;
					System.out.println("Tentativas: " +tentativas);
					System.out.println(dispositivos.toString() +acertos);
					//return true;
				}
				/*while(dispositivos.get(2).getPower() < limite) {
					dispositivos.get(2).adddBm(1);
					tentativas++;
					if(Calculacomunicacao(alpha, beta, dispositivos, ruido))
						return true;
				}
				dispositivos.get(2).setPower(-25);
				*/
			}
			
			dispositivos.get(1).setPower(-25);
		}
		System.out.println("acertos: " +acertos);
	return false;
	}
	
	
	public static boolean Calculacomunicacao(int alpha, int beta, ArrayList<dispositivo> dispositivos, double ruido){
		
		for(int i=0; i<dispositivos.size(); i++) {
			if(i > (dispositivos.size()-1)/2)
				break;
			
			dispositivo a = dispositivos.get(i);
			dispositivo b = dispositivos.get(dispositivos.size()-i-1);
			
			//For�a de comunica��o
			// potencia / distancia ^ alpha
			double forca = (a.getPower() / (Math.pow(Math.abs(a.getPosicao()-b.getPosicao()),alpha)));
			// DIVIDE POR
			// Ruido + potencia / distancia ^ alpha para cada dispositivo
			double interferencia =  CalculaInterferencia(alpha, dispositivos, i);
			
			double result = forca / (ruido+interferencia);
			//System.out.println("SINR(" +a.getNome() +"-" +b.getNome() +") = " +result);

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
