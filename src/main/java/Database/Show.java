package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Show {
    private int showID;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int movieID;
    private int cinemaHallID;

    public Show() {}

    public Show(Date date, Time startTime, Time endTime, int movieID, int cinemaHallID) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieID = movieID;
        this.cinemaHallID = cinemaHallID;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getCinemaHallID() {
        return cinemaHallID;
    }

    public void setCinemaHallID(int cinemaHallID) {
        this.cinemaHallID = cinemaHallID;
    }

    public Movie getMovieInfo() {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        return connection.getMovieByID(movieID);
    }

    public List<ShowSeat> getSeats() {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        List<ShowSeat> seats = new ArrayList<>();

        try {
            String query = "select * from bmt_database.show_seat where showID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, this.showID);

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return showID == show.showID && movieID == show.movieID && cinemaHallID == show.cinemaHallID
                && date.equals(show.date) && startTime.equals(show.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showID, date, startTime, movieID, cinemaHallID);
    }
}
