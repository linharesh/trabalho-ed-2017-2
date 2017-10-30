package arvore;
import hash.TabelaHash;

public class NoArvoreBinaria {
	
	public TabelaHash tabelaHash;
	public int fluxo;
	
	public NoArvoreBinaria filhoEsquerda;
	public NoArvoreBinaria filhoDireita;
	
	public NoArvoreBinaria(int fluxo){
		this.tabelaHash = new TabelaHash();
		this.fluxo = fluxo;
	}
	
}
