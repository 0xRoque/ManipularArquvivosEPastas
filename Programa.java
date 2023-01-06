
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Produto;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Criar uma lista de produtos
		List<Produto> list = new ArrayList<>();

		// Ler o caminho
		System.out.print("Escrever o nome da pasta: ");
		String nomePasta = sc.nextLine();

		// Criar um obj para passar um caminho
		File caminho = new File(nomePasta);
		// obter o caminho sem o nome do arquivo(o nome do arquivo vai ser desprezado)
		String caminhoDaPasta = caminho.getParent();

		// Criar a pasta
		Boolean sucess = new File(caminhoDaPasta + "\\PastaJ").mkdir();
		// System.out.println("Criar a pasta :" + sucess);

		// Criar o arquivo summary.csv
		String novaPasta = caminhoDaPasta + "\\PastaJ\\Sumario.csv";

		/*
		 * Leitura do arquivo Ler o item para o obter Enquanto(while) a leitura for
		 * diferente de nula, obtemos valores No while vamos ler cada valor, colocar na
		 * variavel e instanciar o produto o parse serve para fazer uma conversão, pois
		 * o fields retorna uma string
		 */
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

			String itemcsv = br.readLine();
			while (itemcsv != null) {
				String[] campos = itemcsv.split(",");
				String name = campos[0];
				Double price = Double.parseDouble(campos[1]);
				int quantity = Integer.parseInt(campos[2]);

				list.add(new Produto(name, price, quantity));

				// Ler a primeira linha, imprimir e passar para a proxima
				itemcsv = br.readLine();
			}
			/*
			 * Depois de ler os valor, vamos gravar no nosso arquivo summarycsv percorrer a
			 * lista, e para cada produto, gravar na linha desse arquivo de saida
			 */
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(novaPasta))) {
				for (Produto p : list) {
					bw.write(p.getName() + ", " + String.format("%.2f", p.total()));
					bw.newLine();
				}
				System.out.println(novaPasta + " Criada");
			} catch (IOException e) {
				System.out.println("Erro ao escrever o ficheiro: " + e.getMessage());

			}

		} catch (IOException e) {
			System.out.println("Erro ao escrever o ficheiro: " + e.getMessage());

		}

		sc.close();
	}

}
