package main.java.br.com.productSorter.algorithms;

import main.java.br.com.productSorter.model.Product;

public class QuickSort {

    /**
     * Ordena um array de produtos pelo PREÇO utilizando QuickSort.
     * - Pivô: Mediana de três elementos (primeiro, central, último).
     * - Particionamento: Versão com cursor único (Lomuto), buscando menores para posicioná-los à esquerda do pivô.
     *   (Conforme slide 1, pratica1.2025.pdf: "Nesta versão você deverá modificar o método “particiona”
     *    para um único cursor. Neste caso, buscando os menores para posicioná-los à esquerda do pivô.
     *    Utilize a media de 3 para definir o pivô.")
     *
     * Referências:
     * - QuickSort (geral): aula6_Ordenacao_cont.pdf, slides 22-39.
     * - Mediana de três: aula6_Ordenacao_cont.pdf, slide 35.
     * - Particionamento Lomuto (cursor único): aula6_Ordenacao_cont.pdf, slide 30 (PARTITION(A, p, r)).
     *   Este é o pseudocódigo de Cormen para o particionamento de Lomuto.
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
     * Particiona o subarray produtos[esq..dir] em torno de um pivô (escolhido pela mediana de três).
     * Utiliza a estratégia de particionamento de Lomuto (cursor único 'i' para o final da seção dos menores).
     * Elementos menores ou iguais ao pivô ficam à esquerda, maiores à direita.
     * Retorna o índice final do pivô.
     */
    private static int partitionPrice(Product[] products, int left, int right) {
        // 1. Escolha do Pivô: Mediana de três (elementos nas posições left, middle, right)
        int middle = left + (right - left) / 2;

        // Ordena left, middle, right para encontrar a mediana e colocar no final (right) para ser o pivô
        if (products[left].getPreco() > products[middle].getPreco()) {
            swap(products, left, middle);
        }
        if (products[left].getPreco() > products[right].getPreco()) {
            swap(products, left, right);
        }
        if (products[middle].getPreco() > products[right].getPreco()) {
            swap(products, middle, right);
        }
        // Agora produtos[right] é a mediana, mas o algoritmo de Lomuto do Cormen (slide 30)
        // espera o pivô em produtos[dir] (ou produtos[r] no pseudocódigo).
        // O pseudocódigo fornecido no pratica1.pdf para o particionamento com um único cursor
        // ("buscando os menores para posicioná-los à esquerda do pivô")
        // é consistente com a ideia do Lomuto.
        // O pivô será o elemento produtos[dir] (após a mediana de 3 ter sido movida para lá).

        Product pivot = products[right]; // Pivô é o último elemento após a mediana de 3.
        int i = left - 1; // Índice do último elemento menor que o pivô (inicializa antes do início)

        // 2. Particionamento (Lomuto)
        // Percorre o array com 'j' (cursor de varredura)
        // 'i' (cursor de partição) marca o final da seção de elementos menores que o pivô
        for (int j = left; j < right; j++) { // Percorre até right-1 porque right é o pivô
            if (products[j].getPreco() <= pivot.getPreco()) {
                i++; // Incrementa i para expandir a seção dos menores
                swap(products, i, j); // Move produtos[j] para a seção dos menores
            }
        }

        // 3. Coloca o pivô em sua posição final correta
        // O pivô (originalmente em produtos[dir]) deve ir para a posição i+1
        swap(products, i + 1, right);
        return i + 1; // Retorna o índice onde o pivô foi colocado
    }

    private static void swap(Product[] products, int i, int j) {
        Product temp = products[i];
        products[i] = products[j];
        products[j] = temp;
    }
}