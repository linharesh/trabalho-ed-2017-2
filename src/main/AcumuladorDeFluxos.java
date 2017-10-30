package main;

import hash.TabelaHash;
import listaencadeada.ListaEncadeada;

public class AcumuladorDeFluxos {

	TabelaHash hash = new TabelaHash();
	
	public void acumular(Fluxo fluxo) {
		hash.insere(fluxo);
	}
	
	public ListaEncadeada getFluxosAcumulados() {
		return hash.getConteudo();
	}
	
}
