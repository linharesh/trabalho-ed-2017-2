package main;

import arvore.ArvoreAvl;
import listaencadeada.ListaEncadeada;
import listaencadeada.NoLista;

public class Launcher {

	public static void main(String[] args) {
		//gerarFluxos();
		try {
			LeitorDeFluxos leitor = new LeitorDeFluxos("arquivos/");
			AcumuladorDeFluxos acumulador = leitor.leFluxos();
			ListaEncadeada fluxosAcumulados = acumulador.getFluxosAcumulados();
			ArvoreAvl arvore = new ArvoreAvl();
			NoLista noAtual = fluxosAcumulados.primeiroNo;
			while (noAtual != null) {
				arvore.inserir(noAtual.fluxo);
				noAtual = noAtual.proximo;
			}
			arvore.imprimeFluxos();
			System.out.println("IMPRESSAO PRE ORDEM:");
			arvore.impressaoPreOrdem();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gerarFluxos() {
		GeradorDeFluxo gerador = new GeradorDeFluxo(5, 20, 2, 60);
		gerador.gerarFluxos(100, "arquivos/fluxos.csv");
	}

}
