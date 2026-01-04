package datastructures;

import models.Startup;

public class StartupLinkedList {
    private StartupNode head = null;
    private StartupNode tail = null;

    public void add(Startup s) {
        StartupNode node = new StartupNode(s);
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
            System.out.println("No startups registered!");
            return;
        }
        System.out.println("--- Startups ---");
        StartupNode temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public StartupNode getHead() {
        return head;
    }
}

