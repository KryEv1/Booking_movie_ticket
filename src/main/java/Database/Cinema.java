package Database;

import Database.Exception.InputErrorException;
import com.mysql.cj.protocol.x.ReusableOutputStream;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cinema {
    private int cinemaID;
    private String name;
    private int totalHalls;

    public Cinema() {
    }

    public Cinema(String name, int totalHalls) {
        this.name = name;
        this.totalHalls = totalHalls;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHalls() {
        return totalHalls;
    }

    public void setTotalHalls(int totalHalls) {
        this.totalHalls = totalHalls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return cinemaID == cinema.cinemaID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaID);
    }

    public List<CinemaHall> getAllHall() {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        List<CinemaHall> halls = new ArrayList<>();

        try {
            String query = "select * from bmt_database.cinema_hall where cinemaID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, this.cinemaID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                CinemaHall hall = new CinemaHall();
                hall.setHallID(result.getInt("hallID"));
                hall.setName(result.getString("name"));
                hall.setTotalSeats(result.getInt("totalSeats"));
                hall.setCinemaID(result.getInt("cinemaID"));
                halls.add(hall);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return halls;
    }

    public void addHall(CinemaHall hall) throws InputErrorException {
        if (hall.getCinemaID() != this.cinemaID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "insert into bmt_database.cinema_hall (name, totalSeats, cinemaID) " +
                            "values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hall.getName());
            statement.setInt(2, hall.getHallID());
            statement.setInt(3, this.cinemaID);

            statement.execute();
            this.totalHalls++;
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editHall(CinemaHall hall) throws InputErrorException {
        if (hall.getCinemaID() != this.cinemaID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.cinema_hall set " +
                            "name = ?, " +
                            "totalSeats = ?, " +
                            "where hallID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hall.getName());
            statement.setInt(2, hall.getTotalSeats());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteHall(CinemaHall hall) throws InputErrorException {
        if (hall.getCinemaID() != this.cinemaID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "delete from bmt_database.cinema_hall where hallID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, hall.getHallID());

            statement.execute();
            this.totalHalls--;
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public CinemaHall getHallByID(int hallID) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        CinemaHall hall = new CinemaHall();

        try {
            String query = "select * from bmt_database.cinema_hall where hallID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, hallID);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                hall.setHallID(hallID);
                hall.setName(result.getString("name"));
                hall.setTotalSeats(result.getInt("totalSeats"));
                hall.setCinemaID(result.getInt("cinemaID"));
            } else {
                conn.close();
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hall;
    }

}
