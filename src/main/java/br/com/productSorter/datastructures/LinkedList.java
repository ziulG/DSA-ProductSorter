package main.java.br.com.productSorter.datastructures;

import main.java.br.com.productSorter.model.Product;

public class LinkedList {
    ListNode head;

    public LinkedList() {
        this.head = null;
    }

    public void insertEnd(Product product) {
        ListNode newNode = new ListNode(product);
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current; // Estabelece ligação dupla
        }
    }

    /**
     * Ordena a lista duplamente encadeada por NOME DO PRODUTO usando Insertion Sort.
     * 
     * Implementação otimizada com lista duplamente encadeada:
     * - Considera o primeiro elemento (head) como já ordenado
     * - A partir do segundo elemento (i=2), verifica cada elemento
     * - Usa ponteiro prev para acesso O(1) ao nó anterior
     * - Mantém a parte à esquerda sempre ordenada
     * 
     * Algoritmo tradicional adaptado para lista duplamente encadeada:
     * 1. Primeiro nó (head) é considerado já ordenado
     * 2. Para i=2 até n (percorre a partir do segundo nó):
     *    a) Se o nó atual está ordenado em relação ao anterior, continua
     *    b) Senão, remove o nó e insere na posição correta na parte ordenada
     * 3. Repete até processar todos os nós
     * 
     * Vantagens da lista duplamente encadeada:
     * - Acesso O(1) ao nó anterior (sem necessidade de busca)
     * - Remoção e inserção mais eficientes
     * - Código mais limpo e legível
     * 
     * Complexidade: O(n²) no pior caso, O(n) no melhor caso (lista já ordenada)
     * 
     * Referência: Algoritmo tradicional do InsertionSort (aula5_Ordenacao.pdf, slides 24-32)
     * otimizado para lista duplamente encadeada.
     */
    public void insertionSortByNomeProduto() {
        // Verifica se a lista tem pelo menos 2 elementos para ordenar
        if (head == null || head.next == null) {
            return; // Lista vazia ou com 1 elemento já está "ordenada"
        }

        // Começa do segundo nó (equivalente ao i=2 no algoritmo tradicional)
        ListNode current = head.next;
        
        while (current != null) {
            ListNode nextNode = current.next; // Salva referência para continuar a iteração
            
            // Verifica se current está na posição correta usando o ponteiro prev
            // (se é >= ao nó anterior na parte ordenada)
            if (current.product.getNome().compareToIgnoreCase(current.prev.product.getNome()) >= 0) {
                // Está na posição correta, continua para o próximo
                current = nextNode;
                continue;
            }
            
            // Current precisa ser movido - remove da posição atual usando ligações duplas
            removeNode(current);
            
            // Encontra a posição correta para inserir current na parte ordenada
            if (current.product.getNome().compareToIgnoreCase(head.product.getNome()) < 0) {
                // Insere no início (antes do head)
                insertAtBeginning(current);
            } else {
                // Insere na posição correta no meio da parte ordenada
                ListNode searchPtr = head;
                while (searchPtr.next != null && 
                       searchPtr.next != nextNode && // Não ultrapassa a parte não ordenada
                       current.product.getNome().compareToIgnoreCase(searchPtr.next.product.getNome()) > 0) {
                    searchPtr = searchPtr.next;
                }
                // Insere current após searchPtr
                insertAfter(searchPtr, current);
            }
            
            // Continua com o próximo nó
            current = nextNode;
        }
    }
    
    /**
     * Remove um nó da lista duplamente encadeada
     */
    private void removeNode(ListNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // É o primeiro nó
            head = node.next;
        }
        
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        
        // Limpa as referências do nó removido
        node.next = null;
        node.prev = null;
    }
    
    /**
     * Insere um nó no início da lista
     */
    private void insertAtBeginning(ListNode node) {
        node.next = head;
        node.prev = null;
        
        if (head != null) {
            head.prev = node;
        }
        
        head = node;
    }
    
    /**
     * Insere um nó após um nó específico
     */
    private void insertAfter(ListNode afterNode, ListNode newNode) {
        newNode.next = afterNode.next;
        newNode.prev = afterNode;
        
        if (afterNode.next != null) {
            afterNode.next.prev = newNode;
        }
        
        afterNode.next = newNode;
    }

    public void printList() {
        ListNode current = head;
        while (current != null) {
            System.out.println(current.product.toString());
            current = current.next;
        }
    }

    public static LinkedList fromArray(Product[] products) {
        LinkedList list = new LinkedList();
        for (Product p : products) {
            if (p != null) {
                list.insertEnd(new Product(p.getNome(), p.getFabricante(), p.getPreco(), p.getDataValidadeStr()));
            }
        }
        return list;
    }
}