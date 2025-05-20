package main.java.br.com.productSorter.datastructures;

import main.java.br.com.productSorter.model.Product;

public class ListNode {
    Product product;
    ListNode next;

    public ListNode(Product product) {
        this.product = product;
        this.next = null;
    }
}
