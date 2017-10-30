package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeitorDeFluxos {
	
	private String path;

	public LeitorDeFluxos(String path) {
		super();
		this.path = path;
	}
	
	public AcumuladorDeFluxos leFluxos() throws Exception {
		File folder = new File(path);
		if (!folder.isDirectory()){
			throw new Exception("Erro: O caminho informado não é uma pasta válida!");
		}
		File[] files = folder.listFiles();
		AcumuladorDeFluxos acumulador = new AcumuladorDeFluxos();
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
					acumulador.acumular(new Fluxo(setor, rodovia, dia, fluxo));
				}
				bufferedReader.close();
			}
		}
		return acumulador;
	}

}