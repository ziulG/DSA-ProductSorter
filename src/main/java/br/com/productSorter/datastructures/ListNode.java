package main.java.br.com.productSorter.datastructures;

import main.java.br.com.productSorter.model.Product;

public class ListNode {
    Product product;
    ListNode next;
    ListNode prev;

    public ListNode(Product product) {
        this.product = product;
        this.next = null;
        this.prev = null;
    }
}
