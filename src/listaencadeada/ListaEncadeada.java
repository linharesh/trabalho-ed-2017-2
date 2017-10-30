package listaencadeada;

import main.Fluxo;

public class ListaEncadeada {

	public NoLista primeiroNo;

	public ListaEncadeada() {
		this.primeiroNo = null;
	}

	public int tamanho() {
		if (this.primeiroNo == null) {
			return 0;
		} else {
			int tamanho = 1;
			NoLista noAtual = this.primeiroNo.proximo;
			while (noAtual != null) {
				noAtual = noAtual.proximo;
				tamanho++;
			}
			return tamanho;
		}
	}

	public void inserir(Fluxo fluxo) {
		NoLista novoNo = new NoLista();
		novoNo.fluxo = fluxo;
		if (this.primeiroNo == null) {
			this.primeiroNo = novoNo;
		} else {
			NoLista resultado = buscar(fluxo);
			if (resultado != null){
				resultado.fluxo.setFluxo(resultado.fluxo.getFluxo()+fluxo.getFluxo());
			} else {
				NoLista noAtual = this.primeiroNo;				
				while (noAtual.proximo != null) {
					noAtual = noAtual.proximo;
				}
				noAtual.proximo = novoNo;
			}
		}
	}

	private NoLista buscar(Fluxo fluxo) {
		NoLista noAtual = this.primeiroNo;
		do {
			if (noAtual.fluxo.getSetor() == fluxo.getSetor() && noAtual.fluxo.getDia() == fluxo.getDia()) {
				return noAtual;
			}
			noAtual = noAtual.proximo;
		} while (noAtual != null);
		return null;
	}
	

}
