package datastructures;

import models.Investor;

public class InvestorLinkedList {
    private InvestorNode head = null;
    private InvestorNode tail = null;

    public void add(Investor inv) {
        InvestorNode node = new InvestorNode(inv);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("No investors registered!");
            return;
        }
        System.out.println("--- Investors ---");
        InvestorNode temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public InvestorNode searchById(int id) {
        InvestorNode temp = head;
        while (temp != null) {
            if (temp.data.id == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    public InvestorNode getHead() {
        return head;
    }
}

