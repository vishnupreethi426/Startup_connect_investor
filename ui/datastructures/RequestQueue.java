package datastructures;

import models.Request;
import database.DatabaseOperations;

public class RequestQueue {
    private Request[] queue = new Request[100];
    private int front = -1, rear = -1;

    public void enqueue(Request req) {
        if (rear == 99) {
            System.out.println("Queue Full!");
        } else {
            if (front == -1) front = 0;
            queue[++rear] = req;
        }
    }

    public Request dequeue() {
        if (front == -1) {
            System.out.println("Queue Empty!");
            return null;
        }
        Request req = queue[front];
        if (front == rear) front = rear = -1;
        else front++;
        return req;
    }

    public void display() {
        if (front == -1) {
            System.out.println("Queue Empty!");
        } else {
            System.out.println("--- Connection Requests ---");
            for (int i = front; i <= rear; i++) {
                System.out.println(i + ": " + queue[i]);
            }
        }
    }

    // Added: safely get request by index
    public Request getRequestByIndex(int index) {
        if (front == -1 || index < front || index > rear) {
            return null;
        }
        return queue[index];
    }

    //  Updated: updates both queue and MySQL database
    public void updateRequestStatus(int index, String newStatus) {
        if (front == -1 || index < front || index > rear) {
            System.out.println("Invalid request index!");
        } else {
            queue[index].status = newStatus;

            try {
                DatabaseOperations.updateRequestStatus(
                        queue[index].startupId,
                        queue[index].investorId,
                        newStatus
                );
                System.out.println("Request status updated in database successfully!");
            } catch (Exception e) {
                System.out.println("Database update failed: " + e.getMessage());
            }
        }
    }
}

