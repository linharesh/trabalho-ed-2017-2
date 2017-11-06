package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import arvore.ArvoreAvl;
import listaencadeada.ListaEncadeadaDeFluxos;
import listaencadeada.NoListaDeFluxos;

public class Launcher3 {

	public static void main(String[] args) {

		Scanner ent = new Scanner(System.in);
		try {
			int option = 0;
			int count = 0;
			AcumuladorDeFluxos acumulador = new AcumuladorDeFluxos();

			// in: Stream que vai receber os dados do arquivo de fluxos corrente
			// (que já foram inseridos na árvore)
			InputStream in;
			// out: Stream que escreve os dados nos arquivos "fluxos_old_n.csv", onde n
			// é o número da execução, no diretório "antigos"
			OutputStream out;

			do {
				System.out.println("Digite 1 para gerar novos fluxos de tráfego ou 0 para sair");
				option = ent.nextInt();
				if (option == 1 && count == 0) {
					// System.out.println("Primeira leitura");
					count++;
					gerarArvore(acumulador).imprimeFluxos();
				} else if (option == 1 && count != 0) {
					// System.out.println("Segunda leitura em diante");
					// System.out.println("count: " + count);
					// arquivos que vamos copiar
					File toFile = new File("arquivos/novosfluxos.csv");
					// destino para onde vamos mover o arquivo
					File fromFile = new File("antigos/fluxos_old_" + count + ".csv");
					// verifica se o arquivo existe
					if (!fromFile.exists()) {
						// verifica se o diretório existe
						if (!fromFile.getParentFile().exists()) {
							// cria o diretório
							fromFile.getParentFile().mkdir();
						}
						// cria o arquivo
						fromFile.createNewFile();
					}
					in = new FileInputStream(toFile);
					out = new FileOutputStream(fromFile);
					// buffer para transportar os dados
					byte[] buffer = new byte[1024];
					int length;
					// enquanto tiver dados para ler..
					while ((length = in.read(buffer)) > 0) {
						// escreve no novo arquivo
						out.write(buffer, 0, length);
					}

					in.close();
					out.close();
					// apaga o arquivo antigo
					toFile.delete();

					count++;
					gerarArvore(acumulador).imprimeFluxos();
				}

			} while (option != 0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ent.close();
		}
		System.out.println("finalizando..");
	}

	public static ArvoreAvl gerarArvore(AcumuladorDeFluxos acumulador) {
		GeradorDeFluxo gerador = new GeradorDeFluxo(5, 20, 2, 60);
		gerador.gerarFluxos(10, "arquivos/novosfluxos.csv");
		ArvoreAvl arvore = new ArvoreAvl();
		try {
			LeitorDeFluxos leitor = new LeitorDeFluxos("arquivos/");
			acumulador = leitor.leFluxos(acumulador);
			ListaEncadeadaDeFluxos fluxosAcumulados = acumulador.getFluxosAcumulados();
			NoListaDeFluxos noAtual = (NoListaDeFluxos) fluxosAcumulados.primeiroNo;
			while (noAtual != null) {
				arvore.inserir(noAtual.fluxo);
				noAtual = (NoListaDeFluxos) noAtual.proximo;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arvore;
	}
}
