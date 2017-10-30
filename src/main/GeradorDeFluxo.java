package main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeradorDeFluxo {
	
	private int numeroDeSetores;
	private int numeroDeRodovias;
	private int fluxoMinimo;
	private int fluxoMaximo;
	
	private FileWriter fw;
	private BufferedWriter bw;
	
	
	public GeradorDeFluxo(int numeroDeSetores, int numeroDeRodovias, int fluxoMinimo, int fluxoMaximo) {
		super();
		this.numeroDeSetores = numeroDeSetores;
		this.numeroDeRodovias = numeroDeRodovias;
		this.fluxoMinimo = fluxoMinimo;
		this.fluxoMaximo = fluxoMaximo;
	}
	
	public void gerarFluxos(int numeroDeFluxos, String nomeDoArquivo){

		try {
			fw = new FileWriter(nomeDoArquivo);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < numeroDeFluxos ; i++){
				bw.write(this.gerarFluxo());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private String gerarFluxo(){
		Random rand = new Random();
		int setor = rand.nextInt(this.numeroDeSetores)+1;
		int rodovia = rand.nextInt(this.numeroDeRodovias)+1;
		String ano = String.format("%04d", rand.nextInt(100)+1920);
		String mes = String.format("%02d", rand.nextInt(12)+1);
		String dia = String.format("%02d", rand.nextInt(31)+1);
		int fluxo = rand.nextInt(this.fluxoMaximo-this.fluxoMinimo)+fluxoMinimo;
		return setor+","+rodovia+","+ano+mes+dia+","+fluxo;
	}
	
	

}
