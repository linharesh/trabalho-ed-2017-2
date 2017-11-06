package main;

import hash.TabelaHash;
import listaencadeada.ListaEncadeadaDeFluxos;

public class AcumuladorDeFluxos {

	TabelaHash hash = new TabelaHash();
	
	public void acumular(Fluxo fluxo) {
		hash.insere(fluxo);
	}
	
	public ListaEncadeadaDeFluxos getFluxosAcumulados() {
		return hash.getConteudo();
	}
	
}
