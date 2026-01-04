package datastructures;

public class ActivityStack {
    private String[] stack = new String[100];
    private int top = -1;

    public void push(String activity) {
        if (top < 99) stack[++top] = activity;
    }

    public String pop() {
        if (top == -1) return "No activities";
        return stack[top--];
    }

    public void display() {
        if (top == -1) System.out.println("No activities");
        else {
            System.out.println("--- Recent Activities ---");
            for (int i = top; i >= 0; i--) System.out.println(stack[i]);
        }
    }
}

