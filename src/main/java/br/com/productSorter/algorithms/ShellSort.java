package main.java.br.com.productSorter.algorithms;

import main.java.br.com.productSorter.model.Product;

public class ShellSort {

    /**
     * Ordena um array de produtos pelo nome utilizando ShellSort modificado.
     * Para h > 1: usa SelectionSort nas sublistas.
     * Para h = 1: usa InsertionSort.
     */
    public static void shellSortByName(Product[] products) {
        int n = products.length;
        if (n == 0) return;

        // Sequência de Knuth
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            if (h > 1) {
                // SelectionSort para sublistas h-espaçadas
                for (int i = 0; i < h; i++) {
                    selectionSortSublistH(products, n, h, i);
                }
            } else {
                // InsertionSort para h = 1
                insertionSortSublistH(products, n, h);
            }
            h = h / 3;
        }
    }

    /**
     * Aplica SelectionSort em uma sublista h-espaçada específica.
     */
    private static void selectionSortSublistH(Product[] products, int n, int h, int offset) {
        for (int i = offset; i < n; i += h) {
            int minIndex = i;
            for (int j = i + h; j < n; j += h) {
                if (products[j].getNome().compareToIgnoreCase(products[minIndex].getNome()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Product temp = products[i];
                products[i] = products[minIndex];
                products[minIndex] = temp;
            }
        }
    }

    /**
     * Aplica InsertionSort h-espaçado no array.
     */
    private static void insertionSortSublistH(Product[] products, int n, int h) {
        for (int i = h; i < n; i++) {
            Product key = products[i];
            int j = i;
            while (j >= h && products[j - h].getNome().compareToIgnoreCase(key.getNome()) > 0) {
                products[j] = products[j - h];
                j -= h;
            }
            products[j] = key;
        }
    }
}
