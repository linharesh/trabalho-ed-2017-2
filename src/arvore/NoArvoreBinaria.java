package arvore;

import listaencadeada.ListaSetorDia.ListaEncadeadaSetorDia;

public class NoArvoreBinaria {
	
	public int fluxo;
	public ListaEncadeadaSetorDia listaSetorDia;
	
	public NoArvoreBinaria filhoEsquerda;
	public NoArvoreBinaria filhoDireita;
	
	
	public NoArvoreBinaria(int fluxo){
		this.fluxo = fluxo;
		this.listaSetorDia = null;
	}
	
}
