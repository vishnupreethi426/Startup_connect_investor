package models;

public class Investor {
    public int id;
    public String name;
    public String interestedCategory;
    public double investmentCapacity;
    public double expectedRevenue;

    public Investor(int id, String name, String interestedCategory, double investmentCapacity, double expectedRevenue) {
        this.id = id;
        this.name = name;
        this.interestedCategory = interestedCategory;
        this.investmentCapacity = investmentCapacity;
        this.expectedRevenue = expectedRevenue;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | $" + investmentCapacity + " | Expected Revenue: $" + expectedRevenue
                + " | Category: " + interestedCategory;
    }
}

