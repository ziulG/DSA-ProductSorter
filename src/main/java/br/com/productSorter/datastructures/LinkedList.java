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
     * Ordena a lista encadeada por NOME DO FABRICANTE usando Insertion Sort.
     * Baseado no conceito de Insertion Sort para arrays (aula5_Ordenacao.pdf, slides 24-29),
     * adaptado para uma lista encadeada.
     * O método constrói uma nova lista ordenada ("sorted") inserindo cada nó
     * da lista original ("this.cabeca") em sua posição correta na lista ordenada.
     */
    public void insertionSortByFabricante() {
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
                current.product.getFabricante().compareToIgnoreCase(sortedListHead.product.getFabricante()) <= 0) {
                // Insere no início da lista ordenada
                current.next = sortedListHead;
                sortedListHead = current;
            } else {
                // Encontra a posição correta para inserir 'current' na lista ordenada
                ListNode search = sortedListHead;
                while (search.next != null &&
                       current.product.getFabricante().compareToIgnoreCase(search.next.product.getFabricante()) > 0) {
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