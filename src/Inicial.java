import java.util.ArrayList;

public class Inicial {

	public static void main(String[] args) {
		
		int alpha, beta;
		double ruido; 
		ArrayList<dispositivo> dispositivos = new ArrayList<>();
		
		dispositivos.add(new dispositivo("a", -25 , 0)); //potencia em dBm
		dispositivos.add(new dispositivo("b", -25, 3));
		dispositivos.add(new dispositivo("c", -25, 6));
		dispositivos.add(new dispositivo("d", 0, 9)); 
		dispositivos.add(new dispositivo("e", 0, 20));
		dispositivos.add(new dispositivo("f", 0, 19));
		alpha = 3;
		beta = 3;
		ruido = conversor.converte_nW_µW(10); 	//em nanoWatts
		
		if(!TestaValores(alpha, beta, dispositivos, ruido))
			System.out.println("Not soluble");
	
		//Teste Isolado
		/*System.out.println(Calculacomunicacao(alpha, beta, dispositivos, ruido));
		System.out.println(dispositivos.toString());*/
	}

	public static boolean TestaValores(int alpha, int beta, ArrayList<dispositivo> dispositivos, double ruido) {
		if(Calculacomunicacao(alpha, beta, dispositivos, ruido))
			return true;
		int tentativas = 0;
		int acertos = 0;
		int aux0 = -25, aux1 =-25, aux2=-25;
		
		while(aux0<25) {
			aux0++;
			dispositivos.get(0).setPower(aux0);
			tentativas++;
			if(Calculacomunicacao(alpha, beta, dispositivos, ruido)) {
				acertos++;
				System.out.println(dispositivos.toString());
			}
			while(aux1<25) {
				aux1++;
				dispositivos.get(1).setPower(aux1);
				tentativas++;
				if(Calculacomunicacao(alpha, beta, dispositivos, ruido)) {
					acertos++;
					System.out.println(dispositivos.toString());
				}
				while(aux2<25) {
					aux2++;
					dispositivos.get(2).setPower(aux2);
					tentativas++;
					if(Calculacomunicacao(alpha, beta, dispositivos, ruido)) {
						acertos++;
						System.out.println(dispositivos.toString());
					}
				}
				aux2=-25;
			}
			aux1=-25;
		}
		System.out.println("Tentativas: " +tentativas +", Acertos: " +acertos);
		if(acertos > 0)
			return true;
		else
			return false;
	}
	
	
	public static boolean Calculacomunicacao(int alpha, int beta, ArrayList<dispositivo> dispositivos, double ruido){
		
		for(int i=0; i<dispositivos.size(); i++) {
			if(i > (dispositivos.size()-1)/2)
				break;
			
			dispositivo a = dispositivos.get(i);
			dispositivo b = dispositivos.get(dispositivos.size()-i-1);
			
			double forca = (a.getPower() / (Math.pow(Math.abs(a.getPosicao()-b.getPosicao()),alpha)));
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
