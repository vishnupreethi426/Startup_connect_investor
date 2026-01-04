package models;

public class Startup {
    public int id;
    public String name;
    public String category;
    public String location;
    public double investmentNeeded;
    public double revenue;

    public Startup(int id, String name, String category, String location, double investmentNeeded, double revenue) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.investmentNeeded = investmentNeeded;
        this.revenue= revenue;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + category + " | " + location
                + " | $" + investmentNeeded + " | Revenue: $" + revenue;
    }
}

