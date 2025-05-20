package main.java.br.com.productSorter.algorithms;

import main.java.br.com.productSorter.model.Product;

public class HeapSort {

    /**
     * Ordena um array de produtos pela DATA DE VALIDADE utilizando HeapSort.
     * - Utiliza a conversão do Max-Heapify recursivo para iterativo.
     * - Indexação 0-based para o array em Java, adaptada da lógica 1-based de Cormen.
     *
     * Referências:
     * - HeapSort (geral, Cormen): aula7_HeapSort.pdf, slides 10-22.
     * - HEAPSORT(A, n): aula7_HeapSort.pdf, slide 21.
     * - BUILD-MAX-HEAP(A, n): aula7_HeapSort.pdf, slide 19.
     * - MAX-HEAPIFY(A, i, n) recursivo: aula7_HeapSort.pdf, slide 20 (este será convertido para iterativo).
     *   A instrução da pratica1.pdf: "Você deverá transformar o algoritmo recursivo em iterativo."
     */
    public static void heapSortByValidity(Product[] products) {
        int n = products.length;
        if (n == 0) return;

        // 1. Construir Max-Heap
        // Chama maxHeapifyIterativo para todos os nós internos (não folhas)
        // Em um array 0-based, os nós internos vão de (n/2) - 1 até 0.
        // (aula7_HeapSort.pdf, slide 19: for i <- |n/2| downto 1)
        for (int i = (n / 2) - 1; i >= 0; i--) {
            maxHeapifyIterative(products, n, i);
        }

        // 2. Ordenar
        // Extrai elementos um por um do heap e reconstrói o heap
        // (aula7_HeapSort.pdf, slide 21: for i <- n downto 2)
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual (maior elemento) para o final do array (posição i)
            Product temp = products[0];
            products[0] = products[i];
            products[i] = temp;

            // Chama maxHeapifyIterativo no heap reduzido (tamanho i) na raiz (índice 0)
            maxHeapifyIterative(products, i, 0);
        }
    }

    /**
     * Garante a propriedade de Max-Heap para o subárvore com raiz no índice 'indiceRaiz'.
     * Versão ITERATIVA do MAX-HEAPIFY.
     * 'tamanhoHeap' é o tamanho do heap considerado.
     * A lógica é baseada no MAX-HEAPIFY(A, i) de Cormen (aula7_HeapSort.pdf, slide 20),
     * adaptada para ser iterativa e usar indexação 0-based.
     *
     * Pseudocódigo Cormen (1-based):
     * MAX-HEAPIFY(A, i)
     *  l <- LEFT(i)
     *  r <- RIGHT(i)
     *  if l <= A.heap-size and A[l] > A[i]
     *      largest <- l
     *  else largest <- i
     *  if r <= A.heap-size and A[r] > A[largest]
     *      largest <- r
     *  if largest != i
     *      exchange A[i] with A[largest]
     *      MAX-HEAPIFY(A, largest) // Chamada recursiva a ser eliminada
     *
     * Na versão iterativa, continuamos o processo "descendo" na árvore enquanto
     * a propriedade de heap for violada e o nó atual não for uma folha.
     */
    private static void maxHeapifyIterative(Product[] products, int heapSize, int rootIndex) {
        int current = rootIndex; // Começa com o nó raiz fornecido

        while (true) {
            int left = 2 * current + 1; // Filho esquerdo (0-based)
            int right = 2 * current + 2; // Filho direito (0-based)
            int largest = current;       // Assume que o nó atual é o maior

            // Verifica se o filho esquerdo existe e é maior que o 'maior' atual
            if (left < heapSize && products[left].getDataValidadeComparable() > products[largest].getDataValidadeComparable()) {
                largest = left;
            }

            // Verifica se o filho direito existe e é maior que o 'maior' atual
            if (right < heapSize && products[right].getDataValidadeComparable() > products[largest].getDataValidadeComparable()) {
                largest = right;
            }

            // Se o 'maior' não for o nó 'atual', então a propriedade de Max-Heap está violada
            if (largest != current) {
                // Troca o nó 'atual' com o 'maior' filho
                Product temp = products[current];
                products[current] = products[largest];
                products[largest] = temp;

                // Move para o subárvore afetado para continuar o heapify "descendo"
                current = largest;
            } else {
                // A propriedade de Max-Heap está satisfeita para o subárvore com raiz em 'current'
                break; // Sai do loop
            }
        }
    }
}
