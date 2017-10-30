package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.ArrayList;

import arvore.ArvoreBinaria;

public class LeitorDeFluxos {
	
	private String path;
	
	private int fluxoMinimo = 0;
	private int fluxoMaximo = 0;
	
	private int count = 0;

	public LeitorDeFluxos(String path) {
		super();
		this.path = path;
	}
	
	public ArvoreBinaria leFluxos() throws Exception{
		File folder = new File(path);
		if (!folder.isDirectory()){
			throw new Exception("Erro: O caminho informado não é uma pasta válida!");
		}
		File[] files = folder.listFiles();
		ArvoreBinaria fluxos = new ArvoreBinaria();
		for (File f : files){
			if (f.isFile()){
				BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
				String line = null; 
				while ((line = bufferedReader.readLine()) != null) {
					String[] splitLine = line.split(",");
					int setor = Integer.parseInt(splitLine[0]);
					int rodovia = Integer.parseInt(splitLine[1]);
					int dia = Integer.parseInt(splitLine[2]);
					int fluxo = Integer.parseInt(splitLine[3]);
					if(count == 0) {
						fluxoMinimo = fluxo;
						count++;
					}
					if (fluxo > fluxoMaximo) {
						fluxoMaximo = fluxo;
					}
					if (fluxo < fluxoMinimo) {
						fluxoMinimo = fluxo;
					}
					fluxos.insere(new Fluxo(setor, rodovia, dia, fluxo));
				}
				bufferedReader.close();
			}
		}
		return fluxos;
	}

	public int getFluxoMinimo() {
		return fluxoMinimo;
	}

	public int getFluxoMaximo() {
		return fluxoMaximo;
	}
}