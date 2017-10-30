package hash;

import listaencadeada.ListaEncadeada;
import listaencadeada.NoLista;
import main.Fluxo;

public class TabelaHash {
	
	private int tamanho = 100;
	
	private ListaEncadeada tabela[] = new ListaEncadeada[this.tamanho];
	
	
	public void insere(Fluxo fluxo) {
		int posicao = ((fluxo.getSetor()*10) + fluxo.getDia()) % this.tamanho;
		if (this.tabela[posicao] == null){	
			ListaEncadeada lista = new ListaEncadeada();
			lista.inserir(fluxo);
			this.tabela[posicao] = lista;
		} else { 
			this.tabela[posicao].inserir(fluxo);
		}
	}
	
	public ListaEncadeada getConteudo(){
		ListaEncadeada lista = new ListaEncadeada();
		for (int i = 0 ; i < this.tamanho ; i++){
			if (this.tabela[i] != null){
				NoLista no = this.tabela[i].primeiroNo;
				do{
					lista.inserir(no.fluxo);
					no = no.proximo;
				}while(no != null);
			}
		}
		return lista;
	}

}
