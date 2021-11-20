package Database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Booking {
    private int bookingID;
    private int numberOfSeats;
    private Timestamp timestamp;
    private int status;
    private int userID;

    public Booking() {}

    public Booking(int numberOfSeats, Timestamp timestamp, int status, int userID) {
        this.numberOfSeats = numberOfSeats;
        this.timestamp = timestamp;
        this.status = status;
        this.userID = userID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingID == booking.bookingID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID);
    }

    public List<ShowSeat> getBookedSeats() {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        List<ShowSeat> seats = new ArrayList<>();

        try {
            String query = "select * from show_seat where bookingID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, this.bookingID);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ShowSeat seat = new ShowSeat();
                seat.setShowSeatID(result.getInt("showSeatID"));
                seat.setStatus(result.getInt("status"));
                seat.setPrice(result.getFloat("price"));
                seat.setCinema_seatID(result.getInt("cinema_seatID"));
                seat.setShowID(result.getInt("showID"));
                seat.setBookingID(result.getInt("bookingID"));
                seats.add(seat);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seats;
    }

    public void updateStatus(int status) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.booking set " +
                            "status = ? " +
                            "where showSeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, status);
            statement.setInt(2, this.bookingID);

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ShowSeat getSeatByID(int seatID) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        ShowSeat seat = new ShowSeat();

        try {
            String query = "select * from bmt_database.show_seat where showSeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, seatID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (result.getInt("bookingID") != this.bookingID) {
                    conn.close();
                    return null;
                }
                seat.setShowSeatID(result.getInt("showSeatID"));
                seat.setStatus(result.getInt("status"));
                seat.setPrice(result.getFloat("price"));
                seat.setCinema_seatID(result.getInt("cinema_seatID"));
                seat.setShowID(result.getInt("showID"));
                seat.setBookingID(result.getInt("bookingID"));
            } else {
                conn.close();
                return null;
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seat;
    }
}
