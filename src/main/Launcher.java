package main;

import arvore.ArvoreBinaria;

public class Launcher {

	public static void main(String[] args) {
	//	gerarFluxos();
		try {
			LeitorDeFluxos leitor = new LeitorDeFluxos("arquivos/");
			ArvoreBinaria fluxos = leitor.leFluxos();
			fluxos.impressaoEmOrdem();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gerarFluxos(){
		GeradorDeFluxo gerador = new GeradorDeFluxo(5, 20, 2, 60);
		gerador.gerarFluxos(5000, "fluxos.csv");
	}

}
