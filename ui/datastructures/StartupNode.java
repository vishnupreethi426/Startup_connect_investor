package datastructures;

import models.Startup;

public class StartupNode {
    public Startup data;
    public StartupNode next;
    public StartupNode prev;

    public StartupNode(Startup data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

