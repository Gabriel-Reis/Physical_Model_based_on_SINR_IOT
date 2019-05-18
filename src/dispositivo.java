public class dispositivo {
	String nome;
	Double power;
	int posicao;
	
	public dispositivo(String nome, double dBm, int posicao) {
		super();
		this.nome = nome;
		if(dBm == 0)
			this.power = 0.0;
		else
			this.power = conversor.converte_dBm_µW(dBm);
		this.posicao = posicao;
	}
	
	public void adddBm(int dBm) {
		this.power += (1258.9254118)*dBm;
		//this.power = conversor.converte_dBm_µW(conversor.converte_µW_dBm(power).intValue()+1);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double dBm) {
		if(dBm == 0)
			this.power = 0.0;
		else
			this.power = conversor.converte_dBm_µW(dBm);
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	@Override
	public String toString() {
		return "[" + nome + ", power=" + power + " " +conversor.converte_µW_dBm(power).intValue() +"]";
	}
	
	
	
}
