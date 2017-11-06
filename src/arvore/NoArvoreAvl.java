package arvore;

import listaencadeada.ListaEncadeadaSetorDia;

public class NoArvoreAvl {

	public int fluxo;
	public int altura;
	public ListaEncadeadaSetorDia listaSetorDia;
	
	public NoArvoreAvl filhoEsquerda;
	public NoArvoreAvl filhoDireita;
	
	
	public NoArvoreAvl(int fluxo){
		this.fluxo = fluxo;
		this.listaSetorDia = new ListaEncadeadaSetorDia();
	}

}
