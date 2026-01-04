package models;

public class Request {
    public int startupId;
    public int investorId;
    public String status;

    public Request(int startupId, int investorId, String status) {
        this.startupId = startupId;
        this.investorId = investorId;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Startup ID: " + startupId +
                ", Investor ID: " + investorId +
                ", Status: " + status;
    }
}


