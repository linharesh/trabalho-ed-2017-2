package listaencadeada;


public class ListaEncadeadaSetorDia extends ListaEncadeada {
	
	public void insere(int setor, int dia) {
		NoListaSetorDia novoNo = new NoListaSetorDia();
		novoNo.setor = setor;
		novoNo.dia = dia;
		novoNo.proximo = this.primeiroNo;
		this.primeiroNo = novoNo;
	}
	
}
