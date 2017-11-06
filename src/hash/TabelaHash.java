package hash;

import listaencadeada.ListaEncadeadaDeFluxos;
import listaencadeada.NoListaDeFluxos;
import main.Fluxo;

public class TabelaHash {
	
	private int tamanho = 100;
	
	private ListaEncadeadaDeFluxos tabela[] = new ListaEncadeadaDeFluxos[this.tamanho];
	
	
	public void insere(Fluxo fluxo) {
		int posicao = ((fluxo.getSetor()*10) + fluxo.getDia()) % this.tamanho;
		if (this.tabela[posicao] == null){	
			ListaEncadeadaDeFluxos lista = new ListaEncadeadaDeFluxos();
			lista.inserir(fluxo);
			this.tabela[posicao] = lista;
		} else { 
			this.tabela[posicao].inserir(fluxo);
		}
	}
	
	public ListaEncadeadaDeFluxos getConteudo(){
		ListaEncadeadaDeFluxos lista = new ListaEncadeadaDeFluxos();
		for (int i = 0 ; i < this.tamanho ; i++){
			if (this.tabela[i] != null){
				NoListaDeFluxos no = (NoListaDeFluxos) this.tabela[i].primeiroNo;
				do{
					lista.inserir(no.fluxo);
					no = (NoListaDeFluxos) no.proximo;
				}while(no != null);
			}
		}
		return lista;
	}

}
