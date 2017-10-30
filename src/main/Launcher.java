package main;

import arvore.ArvoreBinaria;

public class Launcher {

	public static void main(String[] args) {
		gerarFluxos();
		try {
			LeitorDeFluxos leitor = new LeitorDeFluxos("arquivos/");
			ArvoreBinaria fluxos = leitor.leFluxos();
			fluxos.impressaoEmOrdem(leitor.getFluxoMaximo(), leitor.getFluxoMinimo());
			System.out.println("Fluxo máximo: "+leitor.getFluxoMaximo());
			System.out.println("Fluxo mínimo: "+leitor.getFluxoMinimo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gerarFluxos(){
		GeradorDeFluxo gerador = new GeradorDeFluxo(5, 20, 2, 60);
		gerador.gerarFluxos(100, "arquivos/fluxos.csv");
//		System.out.println("teste");
//		System.out.println("teste2");
	}

}
