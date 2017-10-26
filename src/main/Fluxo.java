package main;

public class Fluxo {
	
	private int setor;
	private int rodovia;
	private int dia;
	private int fluxo;
	
	public Fluxo(int setor, int rodovia, int dia, int fluxo) {
		this.setor = setor;
		this.rodovia = rodovia;
		this.dia = dia;
		this.fluxo = fluxo;
	}

	public int getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	public int getRodovia() {
		return rodovia;
	}

	public void setRodovia(int rodovia) {
		this.rodovia = rodovia;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getFluxo() {
		return fluxo;
	}

	public void setFluxo(int fluxo) {
		this.fluxo = fluxo;
	}

}
