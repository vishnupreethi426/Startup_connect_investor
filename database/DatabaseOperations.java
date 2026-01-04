package database;

import java.sql.*;
import java.util.*;
import models.Startup;
import models.Investor;
import models.Request;

public class DatabaseOperations {

    private static final String URL = "jdbc:mysql://localhost:3306/startupconnectdb";
    private static final String USER = "root";
    private static final String PASS = "Root@2006";

    // ---------- Get Connection ----------
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ---------- Insert Startup ----------
    public static void insertStartup(Startup s) {
        String sql = "INSERT INTO startups (id, name, category, location, investmentNeeded, revenue) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
          //  ps.setInt(1, s.id);
            ps.setString(2, s.name);
            ps.setString(3, s.category);
            ps.setString(4, s.location);
            ps.setDouble(5, s.investmentNeeded);
            ps.setDouble(6, s.revenue);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                i.id = generatedId;
                System.out.println("Inserted investor with ID: " + generatedId);
            }
        } catch (SQLException e) {
            System.out.println(" Error inserting startup: " + e.getMessage());
        }
    }

    // ---------- Insert Investor ----------
    public static void insertInvestor(Investor i) {
        String sql = "INSERT INTO investors (id, name, interestedCategory, investmentCapacity, expectedRevenue) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
          //  ps.setInt(1, i.id);
            ps.setString(2, i.name);
            ps.setString(3, i.interestedCategory);
            ps.setDouble(4, i.investmentCapacity);
            ps.setDouble(5, i.expectedRevenue);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                s.id = generatedId; // update object
                System.out.println("Inserted startup with ID: " + generatedId);
            }
        } catch (SQLException e) {
            System.out.println(" Error inserting investor: " + e.getMessage());
        }
    }

    // ---------- Insert Request ----------
    public static void insertRequest(int startupId, int investorId, String status) {
        String sql = "INSERT INTO requests (startupId, investorId, status) VALUES (?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, startupId);
            ps.setInt(2, investorId);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting request: " + e.getMessage());
        }
    }

    // ---------- Update Request Status ----------
    public static void updateRequestStatus(int startupId, int investorId, String status) {
        String sql = "UPDATE requests SET status=? WHERE startupId=? AND investorId=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, startupId);
            ps.setInt(3, investorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" Error updating request: " + e.getMessage());
        }
    }

    // ---------- Get Pending Requests ----------
    public static List<Request> getPendingRequests() {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE status='Pending'";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Request(
                        rs.getInt("startupId"),
                        rs.getInt("investorId"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching pending requests: " + e.getMessage());
        }
        return list;
    }

    // ---------- Get All Startups ----------
    public static List<Startup> getAllStartups() {
        List<Startup> list = new ArrayList<>();
        String sql = "SELECT * FROM startups";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Startup(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("location"),
                        rs.getDouble("investmentNeeded"),
                        rs.getDouble("revenue")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching startups: " + e.getMessage());
        }
        return list;
    }

    // ---------- Get All Investors ----------
    public static List<Investor> getAllInvestors() {
        List<Investor> list = new ArrayList<>();
        String sql = "SELECT * FROM investors";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Investor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("interestedCategory"),
                        rs.getDouble("investmentCapacity"),
                        rs.getDouble("expectedRevenue")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching investors: " + e.getMessage());
        }
        return list;
    }

    // ---------- Get Investors by Category ----------
    public static List<Investor> getInvestorsByCategory(String category) {
        List<Investor> list = new ArrayList<>();
        String sql = "SELECT * FROM investors WHERE interestedCategory=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Investor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("interestedCategory"),
                        rs.getDouble("investmentCapacity"),
                        rs.getDouble("expectedRevenue")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching investors by category: " + e.getMessage());
        }
        return list;
    }

    // ---------- Get All Requests ----------
    public static List<Request> getAllRequests() {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM requests";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Request(
                        rs.getInt("startupId"),
                        rs.getInt("investorId"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println(" Error fetching requests: " + e.getMessage());
        }
        return list;
    }
}





