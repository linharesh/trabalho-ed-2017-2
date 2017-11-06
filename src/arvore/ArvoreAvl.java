package arvore;

import listaencadeada.NoListaSetorDia;
import main.Fluxo;

public class ArvoreAvl {

	public NoArvoreAvl raiz;

	public int numeroDeInsercoes;
	public int numeroDeRotacoesIndividuais;
	public int numeroDeRotacoesDuplas;

	public ArvoreAvl() {
		raiz = null;
		numeroDeInsercoes = 0;
		numeroDeRotacoesIndividuais = 0;
		numeroDeRotacoesDuplas = 0;
	}

	public int altura(NoArvoreAvl no) {
		return no == null ? -1 : no.altura;
	}

	public int maior(int primeiro, int segundo) {
		if (primeiro > segundo)
			return primeiro;
		return segundo;
	}

	public boolean inserir(Fluxo fluxo) {
		try {
			NoArvoreAvl resultado = buscaRecursiva(fluxo.getFluxo(), this.raiz);
			if (resultado != null)
				resultado.listaSetorDia.insere(fluxo.getSetor(), fluxo.getDia());
			else {
				this.raiz = insercaoRecursiva(fluxo, raiz);
				numeroDeInsercoes++;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected NoArvoreAvl insercaoRecursiva(Fluxo fluxo, NoArvoreAvl no) throws Exception {
		if (no == null) {
			no = new NoArvoreAvl(fluxo.getFluxo());
			no.listaSetorDia.insere(fluxo.getSetor(), fluxo.getDia());
		} else {
			if (fluxo.getFluxo() < no.fluxo) {
				no.filhoEsquerda = insercaoRecursiva(fluxo, no.filhoEsquerda);
				if (altura(no.filhoEsquerda) - altura(no.filhoDireita) == 2) {
					if (fluxo.getFluxo() < no.filhoEsquerda.fluxo) {
						no = rotacionaComFilhoEsquerda(no);
						numeroDeRotacoesIndividuais++;
					} else {
						no = rotacaoDuplaComFilhoEsquerda(no);
						numeroDeRotacoesDuplas++;
					}
				}
			} else if (fluxo.getFluxo() > no.fluxo) {
				no.filhoDireita = insercaoRecursiva(fluxo, no.filhoDireita);

				if (altura(no.filhoDireita) - altura(no.filhoEsquerda) == 2)
					if (fluxo.getFluxo() > no.filhoDireita.fluxo) {
						no = rotacionaComFilhoDireita(no);
						numeroDeRotacoesIndividuais++;
					} else {
						no = rotacaoDuplaComFilhoDireita(no);
						numeroDeRotacoesDuplas++;
					}
			}
		}
		no.altura = maior(altura(no.filhoEsquerda), altura(no.filhoDireita)) + 1;
		return no;
	}

	private NoArvoreAvl buscaRecursiva(int fluxo, NoArvoreAvl noAtual) {
		if (noAtual == null)
			return null;
		if (noAtual.fluxo == fluxo)
			return noAtual;
		else if (fluxo < noAtual.fluxo)
			return buscaRecursiva(fluxo, noAtual.filhoEsquerda);
		else
			return buscaRecursiva(fluxo, noAtual.filhoDireita);
	}

	protected NoArvoreAvl rotacionaComFilhoEsquerda(NoArvoreAvl k2) {
		NoArvoreAvl k1 = k2.filhoEsquerda;
		k2.filhoEsquerda = k1.filhoDireita;
		k1.filhoDireita = k2;
		k2.altura = maior(altura(k2.filhoEsquerda), altura(k2.filhoDireita)) + 1;
		k1.altura = maior(altura(k1.filhoEsquerda), k2.altura) + 1;
		return (k1);
	}

	protected NoArvoreAvl rotacaoDuplaComFilhoEsquerda(NoArvoreAvl k3) {
		k3.filhoEsquerda = rotacionaComFilhoDireita(k3.filhoEsquerda);
		return rotacionaComFilhoEsquerda(k3);
	}

	protected NoArvoreAvl rotacionaComFilhoDireita(NoArvoreAvl k1) {
		NoArvoreAvl k2 = k1.filhoDireita;
		k1.filhoDireita = k2.filhoEsquerda;
		k2.filhoEsquerda = k1;
		k1.altura = maior(altura(k1.filhoEsquerda), altura(k1.filhoDireita)) + 1;
		k2.altura = maior(altura(k2.filhoDireita), k1.altura) + 1;
		return (k2);
	}

	protected NoArvoreAvl rotacaoDuplaComFilhoDireita(NoArvoreAvl k1) {
		k1.filhoDireita = rotacionaComFilhoEsquerda(k1.filhoDireita);
		return rotacionaComFilhoDireita(k1);
	}

	public boolean estaVazia() {
		return (raiz == null);
	}

	private NoArvoreAvl encontraMenorFluxo(NoArvoreAvl no) {
		if (no == null)
			return no;
		while (no.filhoEsquerda != null)
			no = no.filhoEsquerda;
		return no;
	}

	private NoArvoreAvl encontraMaiorFluxo(NoArvoreAvl no) {
		if (no == null)
			return no;
		while (no.filhoDireita != null)
			no = no.filhoDireita;
		return no;
	}

	public void imprimeFluxos() {
		double limite = this.calculaLimite();
		//System.out.println("Limite: " + limite);
		imprimeFluxosMaioresQueLimite(this.raiz, limite);
	}

	private double calculaLimite() {
		int menor = this.encontraMenorFluxo(this.raiz).fluxo;
		int maior = this.encontraMaiorFluxo(this.raiz).fluxo;
		//System.out.println("menor: " + menor);
		//System.out.println("maior: " + maior);
		return menor + 0.8 * (maior - menor);
	}

	private void imprimeFluxosMaioresQueLimite(NoArvoreAvl no, double limite) {
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

	private void imprimeNo(NoArvoreAvl no) {
		if (no != null) {
			NoListaSetorDia noSetorDia = (NoListaSetorDia) no.listaSetorDia.primeiroNo;
			while (noSetorDia != null) {
				System.out.println(noSetorDia.setor + "," + noSetorDia.dia + "," + no.fluxo);
				noSetorDia = (NoListaSetorDia) noSetorDia.proximo;
			}
		}
	}

	public void impressaoPreOrdem() {
		impressaoPreOrdemRec(raiz);
	}

	private void impressaoPreOrdemRec(NoArvoreAvl no) {
		if (no != null) {
			impressaoPreOrdemRec(no.filhoEsquerda);
			System.out.println(no.fluxo);
			impressaoPreOrdemRec(no.filhoDireita);
		}
	}

	public void remove(Fluxo fluxo) {
		raiz = remocaoRecursiva(fluxo.getFluxo(), raiz);
	}

	public NoArvoreAvl remocaoRecursiva(int fluxo, NoArvoreAvl no) {
		if (no == null) {
			return null;
		}
		if (fluxo < no.fluxo) {
			no.filhoEsquerda = remocaoRecursiva(fluxo, no.filhoEsquerda);
			int l = no.filhoEsquerda != null ? no.filhoEsquerda.altura : 0;

			if ((no.filhoDireita != null) && (no.filhoDireita.altura - l >= 2)) {
				int alturaDireita = no.filhoDireita.filhoDireita != null ? no.filhoDireita.filhoDireita.altura : 0;
				int alturaEsquerda = no.filhoDireita.filhoEsquerda != null ? no.filhoDireita.filhoEsquerda.altura : 0;
				if (alturaDireita >= alturaEsquerda)
					no = rotacionaComFilhoEsquerda(no);
				else
					no = rotacaoDuplaComFilhoDireita(no);
			}
		} else if (fluxo > no.fluxo) {
			no.filhoDireita = remocaoRecursiva(fluxo, no.filhoDireita);
			int r = no.filhoDireita != null ? no.filhoDireita.altura : 0;
			if ((no.filhoEsquerda != null) && (no.filhoEsquerda.altura - r >= 2)) {
				int alturaEsquerda = no.filhoEsquerda.filhoEsquerda != null ? no.filhoEsquerda.filhoEsquerda.altura : 0;
				int alturaDireita = no.filhoEsquerda.filhoDireita != null ? no.filhoEsquerda.filhoDireita.altura : 0;
				if (alturaEsquerda >= alturaDireita)
					no = rotacionaComFilhoDireita(no);
				else
					no = rotacaoDuplaComFilhoEsquerda(no);
			}
		} else if (no.filhoEsquerda != null) {
			NoArvoreAvl temp = encontraMaiorFluxo(no.filhoEsquerda);
			no.fluxo = temp.fluxo;
			no.listaSetorDia = temp.listaSetorDia;
			remocaoRecursiva(no.fluxo, no.filhoEsquerda);
			if ((no.filhoDireita != null) && (no.filhoDireita.altura - no.filhoEsquerda.altura >= 2)) {
				int alturaDireita = no.filhoDireita.filhoDireita != null ? no.filhoDireita.filhoDireita.altura : 0;
				int alturaEsquerda = no.filhoDireita.filhoEsquerda != null ? no.filhoDireita.filhoEsquerda.altura : 0;
				if (alturaDireita >= alturaEsquerda)
					no = rotacionaComFilhoEsquerda(no);
				else
					no = rotacaoDuplaComFilhoDireita(no);
			}
		} else
			no = (no.filhoEsquerda != null) ? no.filhoEsquerda : no.filhoDireita;
		if (no != null) {
			int leftHeight = no.filhoEsquerda != null ? no.filhoEsquerda.altura : 0;
			int rightHeight = no.filhoDireita != null ? no.filhoDireita.altura : 0;
			no.altura = Math.max(leftHeight, rightHeight) + 1;
		}
		return no;
	}

}