package listaencadeada.ListaSetorDia;


public class ListaEncadeadaSetorDia {
	
	public NoListaSetorDia primeiroNo;

	public ListaEncadeadaSetorDia() {
		this.primeiroNo = null;
	}
	
	public void insere(int setor, int dia) {
		NoListaSetorDia novoNo = new NoListaSetorDia();
		novoNo.setor = setor;
		novoNo.dia = dia;
		novoNo.proximo = this.primeiroNo;
		this.primeiroNo = novoNo;
	}
	
}
