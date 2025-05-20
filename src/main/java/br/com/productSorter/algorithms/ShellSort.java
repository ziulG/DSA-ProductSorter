package main.java.br.com.productSorter.algorithms;

import main.java.br.com.productSorter.model.Product;

public class ShellSort {

    /**
     * Ordena um array de produtos pelo NOME do produto utilizando uma versão modificada do ShellSort.
     * - Sequência de incrementos de Knuth: h = h * 3 + 1.
     * - Para h > 1: utiliza SelectionSort para ordenar as sublistas h-espaçadas.
     *   (Conforme slide 1, pratica1.2025.pdf: "Para valores maiores de h > 1 você deverá utilizar
     *    SelectSort para ordenar as sublistas.")
     * - Para h = 1: utiliza InsertionSort (que é o comportamento padrão do ShellSort na última passada).
     *   (Conforme slide 1, pratica1.2025.pdf: "Para h=1, você deverá utilizar o InsertSort
     *    (padrão já aplicado no ShellSort).")
     *
     * A lógica do SelectionSort nas sublistas para h > 1 é uma interpretação da instrução,
     * pois o ShellSort tradicional usa InsertionSort para as sublistas h-espaçadas.
     * O InsertionSort para h=1 é o comportamento natural do ShellSort.
     *
     * Referências:
     * - ShellSort (geral): aula5_Ordenacao.pdf, slides 33-40.
     * - Sequência de Knuth: aula5_Ordenacao.pdf, slide 37.
     * - SelectionSort (conceito): aula5_Ordenacao.pdf, slides 14-22.
     * - InsertionSort (conceito): aula5_Ordenacao.pdf, slides 24-32.
     */
    public static void shellSortByName(Product[] products) {
        int n = products.length;
        if (n == 0) return;

        // Calcula a sequência de Knuth
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            if (h > 1) {
                // Para h > 1, aplicar SelectionSort nas sublistas h-espaçadas
                // Iterar por cada uma das 'h' sublistas
                for (int i = 0; i < h; i++) {
                    // Aplicar SelectionSort na sublista que começa em 'i' e tem elementos h-espaçados
                    selectionSortSublistH(products, n, h, i);
                }
            } else { // h == 1
                // Para h = 1, aplicar InsertionSort (comportamento padrão do ShellSort)
                // Isso é equivalente a um h-sort com h=1, que é o Insertion Sort padrão.
                insertionSortSublistH(products, n, h);
            }
            h = h / 3; // Próximo valor de h na sequência (decrescente)
        }
    }

    /**
     * Aplica SelectionSort em uma sublista h-espaçada específica.
     * A sublista consiste nos elementos produtos[offset], produtos[offset+h], produtos[offset+2h], ...
     * Referência: Conceito do SelectionSort (aula5_Ordenacao.pdf, slides 14-22) aplicado a uma sublista.
     */
    private static void selectionSortSublistH(Product[] products, int n, int h, int offset) {
        // Determinar o número de elementos nesta sublista específica
        // Não é tão direto quanto um array contínuo, então iteramos pelos índices da sublista
        for (int i = offset; i < n; i += h) {
            int minIndex = i;
            // Encontra o menor elemento no restante da sublista h-espaçada
            for (int j = i + h; j < n; j += h) {
                if (products[j].getNome().compareToIgnoreCase(products[minIndex].getNome()) < 0) {
                    minIndex = j;
                }
            }
            // Troca o elemento encontrado (menor) com o elemento atual da sublista
            if (minIndex != i) {
                Product temp = products[i];
                products[i] = products[minIndex];
                products[minIndex] = temp;
            }
        }
    }

    /**
     * Aplica InsertionSort h-espaçado no array. Quando h=1, é o InsertionSort padrão.
     * Referência: Conceito do InsertionSort (aula5_Ordenacao.pdf, slides 24-32) generalizado para h-sort.
     * O pseudocódigo do ShellSort em aula5_Ordenacao.pdf, slide 38, já implementa este h-sort
     * (que é baseado em InsertionSort).
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
