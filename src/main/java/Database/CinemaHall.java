package Database;

import Database.Exception.InputErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CinemaHall {
    private int hallID;
    private String name;
    private int totalSeats;
    private int cinemaID;

    public CinemaHall() {}

    public CinemaHall(String name, int totalSeats, int cinemaID) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.cinemaID = cinemaID;
    }

    public int getHallID() {
        return hallID;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return hallID == that.hallID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallID);
    }

    public List<CinemaSeat> getALlSeats() {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        List<CinemaSeat> seats = new ArrayList<>();

        try {
            String query = "select * from bmt_database.cinema_seat where cinemaHallID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, this.hallID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                CinemaSeat seat = new CinemaSeat();
                seat.setSeatID(result.getInt("seatID"));
                seat.setSeatNumber(result.getString("seatNumber"));
                seat.setType(result.getInt("type"));
                seat.setCinemaHallID(result.getInt("cinemaHallID"));
                seats.add(seat);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seats;
    }

    public void addSeat(CinemaSeat seat) throws InputErrorException {
        if (seat.getCinemaHallID() != this.hallID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "insert into bmt_database.cinema_seat (seatNumber, type, cinemaHallID) " +
                            "values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, seat.getSeatNumber());
            statement.setInt(2, seat.getType());
            statement.setInt(3, this.hallID);

            statement.execute();
            this.totalSeats++;
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editSeat(CinemaSeat seat) throws InputErrorException {
        if (seat.getCinemaHallID() != this.hallID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.cinema_seat set " +
                            "seatNumber = ?, " +
                            "type = ? " +
                            "where seatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, seat.getSeatNumber());
            statement.setInt(2, seat.getType());
            statement.setInt(3, seat.getSeatID());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteSeat(CinemaSeat seat)  throws InputErrorException {
        if (seat.getCinemaHallID() != this.hallID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "delete from bmt_database.cinema_seat where seatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, seat.getSeatID());

            statement.execute();
            this.totalSeats--;
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public CinemaSeat getSeatByID(int seatID) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        CinemaSeat seat = new CinemaSeat();

        try {
            String query = "select * from bmt_database.cinema_seat where seatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, seatID);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                seat.setSeatID(seatID);
                seat.setSeatNumber(result.getString("seatNumber"));
                seat.setType(result.getInt("type"));
                seat.setCinemaHallID(result.getInt("cinemaHallID"));
            } else {
                conn.close();
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seat;
    }
}
