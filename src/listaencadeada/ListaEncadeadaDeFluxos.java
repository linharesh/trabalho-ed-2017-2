package listaencadeada;

import main.Fluxo;

public class ListaEncadeadaDeFluxos extends ListaEncadeada {

	public void inserir(Fluxo fluxo) {
		NoListaDeFluxos novoNo = new NoListaDeFluxos();
		novoNo.fluxo = fluxo;
		if (this.primeiroNo == null) {
			this.primeiroNo = novoNo;
		} else {
			NoListaDeFluxos resultado = buscar(fluxo);
			if (resultado != null){
				resultado.fluxo.setFluxo(resultado.fluxo.getFluxo()+fluxo.getFluxo());
			} else {
				NoListaDeFluxos noAtual = (NoListaDeFluxos) this.primeiroNo;				
				while (noAtual.proximo != null) {
					noAtual = (NoListaDeFluxos) noAtual.proximo;
				}
				noAtual.proximo = novoNo;
			}
		}
	}

	private NoListaDeFluxos buscar(Fluxo fluxo) {
		NoListaDeFluxos noAtual = (NoListaDeFluxos) this.primeiroNo;
		do {
			if (noAtual.fluxo.getSetor() == fluxo.getSetor() && noAtual.fluxo.getDia() == fluxo.getDia()) {
				return noAtual;
			}
			noAtual = (NoListaDeFluxos) noAtual.proximo;
		} while (noAtual != null);
		return null;
	}
	

}
