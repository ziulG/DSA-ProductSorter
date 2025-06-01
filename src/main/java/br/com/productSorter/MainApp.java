package main.java.br.com.productSorter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.java.br.com.productSorter.algorithms.HeapSort;
import main.java.br.com.productSorter.algorithms.QuickSort;
import main.java.br.com.productSorter.algorithms.ShellSort;
import main.java.br.com.productSorter.datastructures.LinkedList;
import main.java.br.com.productSorter.model.Product;

public class MainApp {

    private static final String NOME_ARQUIVO_ENTRADA = "src/main/java/resources/produtos_1000000.txt";

    public static void main(String[] args) {
        Product[] productsOriginais = lerProdutosDoArquivo(NOME_ARQUIVO_ENTRADA);

        if (productsOriginais == null || productsOriginais.length == 0) {
            System.out.println("Nenhum produto carregado. Verifique o arquivo " + NOME_ARQUIVO_ENTRADA);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        do {
            System.out.println("\n--- Menu de Ordenação de Produtos ---");
            System.out.println("1. Ordenar por NOME do produto (ShellSort Modificado)");
            System.out.println("2. Ordenar por PREÇO (QuickSort com Mediana de 3 e Particionamento Único Cursor)");
            System.out.println("3. Ordenar por DATA DE VALIDADE (HeapSort Iterativo)");
            System.out.println("4. Ordenar por NOME DO PRODUTO (InsertionSort em Lista Encadeada)");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                scanner.nextLine();

                Product[] productsParaOrdenar;

                switch (escolha) {
                    case 1:
                        System.out.println("\n--- Ordenando por NOME do produto (ShellSort Modificado)... ---");
                        productsParaOrdenar = Arrays.copyOf(productsOriginais, productsOriginais.length);
                        long startTimeShell = System.nanoTime();
                        ShellSort.shellSortByName(productsParaOrdenar);
                        long endTimeShell = System.nanoTime();
                        imprimirProdutos(productsParaOrdenar, productsParaOrdenar.length);
                        System.out.println("Tempo de execução (ShellSort por Nome): " + (endTimeShell - startTimeShell) / 1_000_000 + " ms");
                        break;
                    case 2:
                        System.out.println("\n--- Ordenando por PREÇO (QuickSort Mediana de 3)... ---");
                        productsParaOrdenar = Arrays.copyOf(productsOriginais, productsOriginais.length);
                        long startTimeQuick = System.nanoTime();
                        QuickSort.quickSortByPrice(productsParaOrdenar);
                        long endTimeQuick = System.nanoTime();
                        imprimirProdutos(productsParaOrdenar, productsParaOrdenar.length);
                        System.out.println("Tempo de execução (QuickSort por Preço): " + (endTimeQuick - startTimeQuick) / 1_000_000 + " ms");
                        break;
                    case 3:
                        System.out.println("\n--- Ordenando por DATA DE VALIDADE (HeapSort Iterativo)... ---");
                        productsParaOrdenar = Arrays.copyOf(productsOriginais, productsOriginais.length);
                        long startTimeHeap = System.nanoTime();
                        HeapSort.heapSortByValidity(productsParaOrdenar);
                        long endTimeHeap = System.nanoTime();
                        imprimirProdutos(productsParaOrdenar, productsParaOrdenar.length);
                        System.out.println("Tempo de execução (HeapSort por Validade): " + (endTimeHeap - startTimeHeap) / 1_000_000 + " ms");
                        break;
                    case 4:
                        System.out.println("\n--- Ordenando por NOME DO PRODUTO (InsertionSort em Lista Encadeada)... ---");
                        LinkedList listaProdutos = LinkedList.fromArray(productsOriginais);
                        long startTimeList = System.nanoTime();
                        listaProdutos.insertionSortByNomeProduto();
                        long endTimeList = System.nanoTime();
                        listaProdutos.printList();
                        System.out.println("Tempo de execução (InsertionSort Lista por Nome do Produto): " + (endTimeList - startTimeList) / 1_000_000 + " ms");
                        break;
                    case 5:
                        System.out.println("Saindo do programa.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        } while (escolha != 5);

        scanner.close();
    }

    private static Product[] lerProdutosDoArquivo(String nomeArquivo) {
        List<Product> listaDeProdutos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Product product = Product.fromString(linha);
                    if (product != null) {
                        listaDeProdutos.add(product);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo + ": " + e.getMessage());
            return null;
        }
        return listaDeProdutos.toArray(new Product[0]);
    }

    private static void imprimirProdutos(Product[] products, int limite) {
        System.out.println("Exibindo os primeiros " + Math.min(limite, products.length) + " de " + products.length + " produtos ordenados:");
        for (int i = 0; i < Math.min(limite, products.length); i++) {
            System.out.println(products[i].toString());
        }
        if (limite < products.length) {
            System.out.println("... e mais " + (products.length - limite) + " produtos.");
        }
         System.out.println("--- Fim da Lista ---");
    }
}
