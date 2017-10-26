package arvore;

import listaencadeada.ListaEncadeada;
import listaencadeada.NoLista;
import main.Fluxo;

public class ArvoreBinaria {

	private NoArvoreBinaria raiz;

	public ArvoreBinaria() {
		this.raiz = null;
	}
	
	public void impressaoEmOrdem(){
		this.impressaoEmOrdemRecursiva(this.raiz);
	}
	
	private void impressaoEmOrdemRecursiva(NoArvoreBinaria noAtual){
		if (noAtual != null){
			this.impressaoEmOrdemRecursiva(noAtual.filhoEsquerda);
			ListaEncadeada conteudo = noAtual.tabelaHash.getConteudo();
			NoLista no = conteudo.primeiroNo;
			do{
				/* verificar a condição delta */
				System.out.println(noAtual.setor+","+no.fluxo.getDia()+","+no.fluxo.getFluxo());
				no = no.proximo;
			}while(no != null);
			this.impressaoEmOrdemRecursiva(noAtual.filhoDireita);
		}
	}

	public void insere(Fluxo fluxo) {
		if (this.raiz == null) {
			this.raiz = new NoArvoreBinaria(fluxo.getSetor());
			this.raiz.tabelaHash.insere(fluxo);
		} else {
			NoArvoreBinaria resultado = buscaRecursiva(fluxo.getSetor(), this.raiz);
			if (resultado == null){ // o setor ainda não existe na árvore
				insercaoRecursiva(fluxo, this.raiz);
			} else { // o setor já existe na árvore
				resultado.tabelaHash.insere(fluxo);
			}		
		}
	}
	
	private void insercaoRecursiva(Fluxo fluxo, NoArvoreBinaria noAtual) {
		int setor = fluxo.getSetor();
		if (setor < noAtual.setor ) {
			if (noAtual.filhoEsquerda == null) {
				NoArvoreBinaria novoNo = new NoArvoreBinaria(fluxo.getSetor());
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

	private NoArvoreBinaria buscaRecursiva(int setor, NoArvoreBinaria noAtual) {
		if (noAtual == null)
			return null;
		if (noAtual.setor == setor)
			return noAtual;
		else if (setor < noAtual.setor)
			return buscaRecursiva(setor, noAtual.filhoEsquerda);
		else
			return buscaRecursiva(setor, noAtual.filhoDireita);
	}

}
