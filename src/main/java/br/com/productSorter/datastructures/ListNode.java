package main.java.br.com.productSorter.datastructures;

import main.java.br.com.productSorter.model.Product;

public class ListNode {
    Product product; // Produto armazenado no nó
    ListNode next;   // Ponteiro para o próximo nó
    ListNode prev;   // Ponteiro para o nó anterior

    public ListNode(Product product) {
        this.product = product;
        this.next = null;
        this.prev = null;
    }
}
