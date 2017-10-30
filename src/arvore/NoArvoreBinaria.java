package arvore;
import hash.TabelaHash;

public class NoArvoreBinaria {
	
	public TabelaHash tabelaHash;
	public int setor;
//	public int fluxoMinimo;
//	public int fluxoMaximo;
	
	public NoArvoreBinaria filhoEsquerda;
	public NoArvoreBinaria filhoDireita;
	
	public NoArvoreBinaria(int setor){
		this.tabelaHash = new TabelaHash();
		this.setor = setor;
	}
	
}
