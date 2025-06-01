package main.java.br.com.productSorter.algorithms;

import main.java.br.com.productSorter.model.Product;

public class HeapSort {

    /**
     * Ordena um array de produtos pela data de validade utilizando HeapSort iterativo.
     */
    public static void heapSortByValidity(Product[] products) {
        int n = products.length;
        if (n == 0) return;

        // Construir Max-Heap
        for (int i = (n / 2) - 1; i >= 0; i--) {
            maxHeapifyIterative(products, n, i);
        }

        // Ordenar
        for (int i = n - 1; i > 0; i--) {
            // Move o maior elemento para a posição final
            Product temp = products[0];
            products[0] = products[i];
            products[i] = temp;

            maxHeapifyIterative(products, i, 0);
        }
    }

    /**
     * Versão iterativa do MAX-HEAPIFY para garantir a propriedade de Max-Heap.
     */
    private static void maxHeapifyIterative(Product[] products, int heapSize, int rootIndex) {
        int current = rootIndex;

        while (true) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int largest = current;

            if (left < heapSize && products[left].getDataValidadeComparable() > products[largest].getDataValidadeComparable()) {
                largest = left;
            }

            if (right < heapSize && products[right].getDataValidadeComparable() > products[largest].getDataValidadeComparable()) {
                largest = right;
            }

            if (largest != current) {
                Product temp = products[current];
                products[current] = products[largest];
                products[largest] = temp;

                current = largest;
            } else {
                break;
            }
        }
    }
}
