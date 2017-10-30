package arvore;

import main.Fluxo;

public class ArvoreBinaria {

	private NoArvoreBinaria raiz;

	public ArvoreBinaria() {
		this.raiz = null;
	}
	
	public void imprimeFluxos() {
		double limite = this.calculaLimite();
		
	}

	public void insere(Fluxo fluxo) {
		if (this.raiz == null) {
			this.raiz = new NoArvoreBinaria(fluxo.getFluxo());
			this.raiz.tabelaHash.insere(fluxo);
		} else {
			NoArvoreBinaria resultado = buscaRecursiva(fluxo.getFluxo(), this.raiz);
			if (resultado == null){ // o fluxo ainda não existe na árvore
				insercaoRecursiva(fluxo, this.raiz);
			} else { // o fluxo já existe na árvore
				resultado.tabelaHash.insere(fluxo);
			}		
		}
	}
	
	private void insercaoRecursiva(Fluxo fluxo, NoArvoreBinaria noAtual) {
		if (fluxo.getFluxo() < noAtual.fluxo ) {
			if (noAtual.filhoEsquerda == null) {
				NoArvoreBinaria novoNo = new NoArvoreBinaria(fluxo.getFluxo());
				novoNo.tabelaHash.insere(fluxo);
				noAtual.filhoEsquerda = novoNo;
			} else
				insercaoRecursiva(fluxo, noAtual.filhoEsquerda);
		} else {
			if (noAtual.filhoDireita == null) {
				NoArvoreBinaria novoNo = new NoArvoreBinaria(fluxo.getSetor());
				novoNo.tabelaHash.insere(fluxo);
				noAtual.filhoDireita = novoNo;
			} else
				insercaoRecursiva(fluxo, noAtual.filhoDireita);
		}
	}

	private NoArvoreBinaria buscaRecursiva(int fluxo, NoArvoreBinaria noAtual) {
		if (noAtual == null)
			return null;
		if (noAtual.fluxo == fluxo)
			return noAtual;
		else if (fluxo < noAtual.fluxo)
			return buscaRecursiva(fluxo, noAtual.filhoEsquerda);
		else
			return buscaRecursiva(fluxo, noAtual.filhoDireita);
	}

	private double calculaLimite() {
		/*
		Buscar o menor fluxo da arvore
		Buscar o maior fluxo da árvore
		Aplicar na fórmula: lim = fMin + 0.8*(fMax - fMin);
		retornar lim
		*/
		return 0;
	}
	
	private NoArvoreBinaria encontraMenorNo(NoArvoreBinaria no) {
		return null;
	}

	private NoArvoreBinaria encontraMaiorNo(NoArvoreBinaria no) {
		return null;
	}
	
}