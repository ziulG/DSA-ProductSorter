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
        }
    }

    /**
     * Ordena a lista encadeada por NOME DO PRODUTO usando Insertion Sort.
     * Baseado no algoritmo InsertionSort visto em aula:
     * Em cada passo a partir de i=2:
     * - Selecione o i-ésimo item da sequência fonte
     * - Coloque-o no lugar apropriado na sequência destino de acordo com o critério de ordenação
     * - Os elementos à esquerda do número eleito estão sempre ordenados de forma crescente
     * 
     * Adaptado para uma lista encadeada, constrói uma nova lista ordenada ("sorted") 
     * inserindo cada nó da lista original em sua posição correta na lista ordenada.
     */
    public void insertionSortByNomeProduto() {
        if (head == null || head.next == null) {
            return; // Lista vazia ou com um único elemento já está ordenada.
        }

        ListNode sortedListHead = null; // Cabeça da nova lista ordenada
        ListNode current = head;     // Nó atual sendo pego da lista original

        while (current != null) {
            ListNode nextNodeToProcess = current.next; // Salva o próximo nó da lista original

            // Isola o nó 'current' para inserção na lista ordenada
            current.next = null;

            // Insere 'current' na lista 'sortedListHead'
            if (sortedListHead == null ||
                current.product.getNome().compareToIgnoreCase(sortedListHead.product.getNome()) <= 0) {
                // Insere no início da lista ordenada
                current.next = sortedListHead;
                sortedListHead = current;
            } else {
                // Encontra a posição correta para inserir 'current' na lista ordenada
                ListNode search = sortedListHead;
                while (search.next != null &&
                       current.product.getNome().compareToIgnoreCase(search.next.product.getNome()) > 0) {
                    search = search.next;
                }
                // Insere 'current' após 'search'
                current.next = search.next;
                search.next = current;
            }
            current = nextNodeToProcess; // Move para o próximo nó da lista original
        }
        this.head = sortedListHead; // Atualiza a cabeça da lista original para a lista ordenada
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