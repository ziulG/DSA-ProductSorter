package main.java.br.com.productSorter.algorithms;

import main.java.br.com.productSorter.model.Product;

public class QuickSort {

    /**
     * Ordena um array de produtos pelo preço utilizando QuickSort com mediana de três.
     */
    public static void quickSortByPrice(Product[] products) {
        if (products == null || products.length == 0) {
            return;
        }
        quickSortRecursive(products, 0, products.length - 1);
    }

    private static void quickSortRecursive(Product[] products, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionPrice(products, left, right);
            quickSortRecursive(products, left, pivotIndex - 1);
            quickSortRecursive(products, pivotIndex + 1, right);
        }
    }

    /**
     * Particiona o array usando mediana de três e particionamento de Lomuto.
     */
    private static int partitionPrice(Product[] products, int left, int right) {
        // Mediana de três
        int middle = left + (right - left) / 2;

        if (products[left].getPreco() > products[middle].getPreco()) {
            swap(products, left, middle);
        }
        if (products[left].getPreco() > products[right].getPreco()) {
            swap(products, left, right);
        }
        if (products[middle].getPreco() > products[right].getPreco()) {
            swap(products, middle, right);
        }

        Product pivot = products[right];
        int i = left - 1;

        // Particionamento de Lomuto
        for (int j = left; j < right; j++) {
            if (products[j].getPreco() <= pivot.getPreco()) {
                i++;
                swap(products, i, j);
            }
        }

        swap(products, i + 1, right);
        return i + 1;
    }

    private static void swap(Product[] products, int i, int j) {
        Product temp = products[i];
        products[i] = products[j];
        products[j] = temp;
    }
}