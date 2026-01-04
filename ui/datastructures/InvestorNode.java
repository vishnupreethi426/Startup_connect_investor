package datastructures;

import models.Investor;

public class InvestorNode {
    public Investor data;
    public InvestorNode next;
    public InvestorNode prev;

    public InvestorNode(Investor data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

