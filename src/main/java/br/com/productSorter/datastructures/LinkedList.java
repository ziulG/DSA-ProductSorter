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
     * Ordena a lista encadeada por nome do produto usando Insertion Sort.
     */
    public void insertionSortByNomeProduto() {
        if (head == null || head.next == null) {
            return;
        }

        ListNode sortedListHead = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNodeToProcess = current.next;
            current.next = null;

            // Insere na lista ordenada
            if (sortedListHead == null ||
                current.product.getNome().compareToIgnoreCase(sortedListHead.product.getNome()) <= 0) {
                current.next = sortedListHead;
                sortedListHead = current;
            } else {
                ListNode search = sortedListHead;
                while (search.next != null &&
                       current.product.getNome().compareToIgnoreCase(search.next.product.getNome()) > 0) {
                    search = search.next;
                }
                current.next = search.next;
                search.next = current;
            }
            current = nextNodeToProcess;
        }
        this.head = sortedListHead;
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