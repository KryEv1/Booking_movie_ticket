package Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ShowSeat {
    private int showSeatID;
    private int status;
    private float price;
    private int cinema_seatID;
    private int showID;
    private int bookingID;

    public ShowSeat() {}

    public ShowSeat(int status, float price, int cinema_seatID, int showID) {
        this.status = status;
        this.price = price;
        this.cinema_seatID = cinema_seatID;
        this.showID = showID;
    }

    public ShowSeat(int status, float price, int cinema_seatID, int showID, int bookingID) {
        this.status = status;
        this.price = price;
        this.cinema_seatID = cinema_seatID;
        this.showID = showID;
        this.bookingID = bookingID;
    }

    public int getShowSeatID() {
        return showSeatID;
    }

    public void setShowSeatID(int showSeatID) {
        this.showSeatID = showSeatID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCinema_seatID() {
        return cinema_seatID;
    }

    public void setCinema_seatID(int cinema_seatID) {
        this.cinema_seatID = cinema_seatID;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowSeat showSeat = (ShowSeat) o;
        return showSeatID == showSeat.showSeatID && cinema_seatID == showSeat.cinema_seatID && showID == showSeat.showID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(showSeatID, cinema_seatID, showID);
    }

    public void updateStatus(int status) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.show_seat set " +
                            "status = ? " +
                            "where showSeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, status);
            statement.setInt(2, this.showSeatID);

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editPrice(float price) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.show_seat set " +
                            "price = ? " +
                            "where showSeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setFloat(1, price);
            statement.setInt(2, this.showSeatID);
            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editShow(int showID) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.show_seat set " +
                            "showID = ? " +
                            "where showSeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, showID);
            statement.setInt(2, this.showSeatID);
            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
