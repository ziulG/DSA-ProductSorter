package main.java.br.com.productSorter.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//NOME PRODUTO, FABRICANTE, PREÇO, VALIDADE.

public class ProductGenerator {

    private static final String[] FABRICANTES = {
        "NESTLE", "BAUDUCO", "UNIÃO", "YOKI", "SADIA", 
        "PERDIGÃO", "COCA-COLA", "DOVE", "OMO"
    };

    private static final Random random = new Random();

    public static void main(String[] args) {
        String diretorioDestino = "src/main/java/resources/";
        
        // Garantir que o diretório existe
        File dir = new File(diretorioDestino);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        int[] tamanhos = {1000, 10000, 100000, 1000000};
        
        for (int tamanho : tamanhos) {
            String nomeArquivo = diretorioDestino + "produtos_" + tamanho + ".txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
                for (int i = 0; i < tamanho; i++) {
                    writer.println(gerarProduto());
                }
                System.out.println("Arquivo gerado: " + nomeArquivo);
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        }
    }

    private static String gerarProduto() {
        String nome = "PRODUTO_" + (random.nextInt(10000) + 1);
        String fabricante = FABRICANTES[random.nextInt(FABRICANTES.length)];
        double preco = 1.0 + (50.0 - 1.0) * random.nextDouble();
        String dataValidade = gerarDataValidade();

        return String.format(
            "%s # %s # %.2f # %s",
            nome, fabricante, preco, dataValidade
        );
    }

    private static String gerarDataValidade() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date hoje = new Date();
        long milissegundosPorDia = 24 * 60 * 60 * 1000L;
        long diasParaValidade = 30 + random.nextInt(3 * 365 - 30);
        Date dataValidade = new Date(hoje.getTime() + diasParaValidade * milissegundosPorDia);
        return sdf.format(dataValidade);
    }
}
