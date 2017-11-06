package main;

import arvore.ArvoreAvl;
import listaencadeada.ListaEncadeadaDeFluxos;
import listaencadeada.NoListaDeFluxos;

public class Launcher {

	public static void main(String[] args) {
		//gerarFluxos();
		try {
			LeitorDeFluxos leitor = new LeitorDeFluxos("arquivos/");
			AcumuladorDeFluxos acumulador = leitor.leFluxos();
			ListaEncadeadaDeFluxos fluxosAcumulados = acumulador.getFluxosAcumulados();
			ArvoreAvl arvore = new ArvoreAvl();
			NoListaDeFluxos noAtual = (NoListaDeFluxos) fluxosAcumulados.primeiroNo;
			while (noAtual != null) {
				arvore.inserir(noAtual.fluxo);
				noAtual = (NoListaDeFluxos) noAtual.proximo;
			}
			arvore.imprimeFluxos();
			//arvore.impressaoPreOrdem();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gerarFluxos() {
		GeradorDeFluxo gerador = new GeradorDeFluxo(5, 20, 2, 60);
		gerador.gerarFluxos(100, "arquivos/fluxos.csv");
	}

}
