package arvore;

import listaencadeada.ListaSetorDia.NoListaSetorDia;
import main.Fluxo;

public class ArvoreBinaria {

	private NoArvoreBinaria raiz;

	public ArvoreBinaria() {
		this.raiz = null;
	}

	public void imprimeFluxos() {
		double limite = this.calculaLimite();
		System.out.println("Limite: "+limite);
		imprimeFluxosMaioresQueLimite(this.raiz, limite);
	}
	
	private void imprimeFluxosMaioresQueLimite(NoArvoreBinaria no, double limite) {
		if (no != null) {
			if (no.fluxo <= limite) {
				imprimeFluxosMaioresQueLimite(no.filhoDireita, limite);
			} else if (no.fluxo > limite) {
				imprimeFluxosMaioresQueLimite(no.filhoEsquerda, limite);
				imprimeNo(no);
				imprimeFluxosMaioresQueLimite(no.filhoDireita, limite);
			}
		}
	}
	
	private void imprimeNo(NoArvoreBinaria no) {
		if (no != null) {
			NoListaSetorDia noSetorDia = no.listaSetorDia.primeiroNo;
			while (noSetorDia != null) {
				System.out.println(noSetorDia.setor+","+noSetorDia.dia+","+no.fluxo);
				noSetorDia = noSetorDia.proximo;
			}
		}		
	}
	
	public void impressaoPreOrdem() {
		impressaoPreOrdemRec(raiz);
	}

	private void impressaoPreOrdemRec(NoArvoreBinaria no) {
		if (no != null) {
			impressaoPreOrdemRec(no.filhoEsquerda);
			System.out.println(no.fluxo);
			impressaoPreOrdemRec(no.filhoDireita);
		}
	}
	
	public void insere(Fluxo fluxo) {
		if (this.raiz == null) {
			this.raiz = new NoArvoreBinaria(fluxo.getFluxo());
			this.raiz.listaSetorDia.insere(fluxo.getSetor(), fluxo.getDia());
		} else {
			NoArvoreBinaria resultado = buscaRecursiva(fluxo.getFluxo(), this.raiz);
			if (resultado == null) { // o fluxo ainda não existe na árvore
				insercaoRecursiva(fluxo, this.raiz);
			} else { // o fluxo já existe na árvore
				resultado.listaSetorDia.insere(fluxo.getSetor(), fluxo.getDia());
			}
		}
	}

	private void insercaoRecursiva(Fluxo fluxo, NoArvoreBinaria noAtual) {
		if (fluxo.getFluxo() < noAtual.fluxo) {
			if (noAtual.filhoEsquerda == null) {
				NoArvoreBinaria novoNo = new NoArvoreBinaria(fluxo.getFluxo());
				novoNo.listaSetorDia.insere(fluxo.getSetor(), fluxo.getDia());
				noAtual.filhoEsquerda = novoNo;
			} else
				insercaoRecursiva(fluxo, noAtual.filhoEsquerda);
		} else {
			if (noAtual.filhoDireita == null) {
				NoArvoreBinaria novoNo = new NoArvoreBinaria(fluxo.getFluxo());
				novoNo.listaSetorDia.insere(fluxo.getSetor(), fluxo.getDia());
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
		int menor = encontraMenorNo(raiz).fluxo;
		int maior = encontraMaiorNo(raiz).fluxo;
		System.out.println("menor: "+menor);
		System.out.println("maior: "+maior);
		return menor + 0.8 * (maior - menor);
	}

	private NoArvoreBinaria encontraMenorNo(NoArvoreBinaria no) {
		if (no.filhoEsquerda == null) {
			return no;
		} else {
			return encontraMenorNo(no.filhoEsquerda);
		}
	}

	private NoArvoreBinaria encontraMaiorNo(NoArvoreBinaria no) {
		if (no.filhoDireita == null) {
			return no;
		} else {
			return encontraMaiorNo(no.filhoDireita);
		}
	}

}