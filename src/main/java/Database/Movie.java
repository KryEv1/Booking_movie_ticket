package Database;

import Database.Exception.InputErrorException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {
    private int movieID;
    private String title;
    private String description;
    private Time duration;
    private String language;
    private Date releaseDate;
    private String country;
    private String genre;

    public Movie(String title, String description, Time duration, String language, Date releaseDate, String country, String genre) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.releaseDate = releaseDate;
        this.country = country;
        this.genre = genre;
    }

    public Movie(int movieID, String title, String description, Time duration, String language, Date releaseDate, String country, String genre) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.releaseDate = releaseDate;
        this.country = country;
        this.genre = genre;
    }

    public Movie() {}

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Show> getShows() {
        DatabaseConnection connection = DatabaseConnection.getInstance();

        Connection conn = connection.getConnection();
        List<Show> shows = new ArrayList<>();
        try {
            String query = "select * from bmt_database.show where movieID = ? order by startTime asc";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, this.movieID);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Show show = new Show();
                show.setShowID(result.getInt("showID"));
                show.setDate(result.getDate("date"));
                show.setStartTime(result.getTime("startTime"));
                show.setEndTime(result.getTime("endTime"));
                show.setCinemaHallID(result.getInt("cinemaHallID"));

                shows.add(show);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shows;
    }

    public void addShow(Show show) throws InputErrorException  {
        if (show.getMovieID() != this.movieID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "insert into bmt_database.show (date, startTime, endTime, movieID, cinemaHallID)" +
                    "values (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, show.getDate());
            statement.setTime(2, show.getStartTime());
            statement.setTime(3, show.getEndTime());
            statement.setInt(4, show.getMovieID());
            statement.setInt(5, show.getCinemaHallID());
            statement.execute();

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editShow(Show show) throws InputErrorException {
        if (show.getMovieID() != this.movieID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "update bmt_database.show set " +
                    "date = ?, " +
                    "startTime = ?, " +
                    "endTime = ?, " +
                    "cinemaHallID = ? " +
                    "where showID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, show.getDate());
            statement.setTime(2, show.getStartTime());
            statement.setTime(3, show.getEndTime());
            statement.setInt(4, show.getCinemaHallID());
            statement.setInt(5, show.getShowID());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleatShow(Show show) throws InputErrorException {
        if (show.getMovieID() != this.movieID) {
            throw new InputErrorException();
        }

        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();

        try {
            String query = "delete from bmt_database.show where showID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, show.getShowID());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Show getShowByID(int showID) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection conn = connection.getConnection();
        Show show = new Show();

        try {
            String query = "select * from bmt_database.show where showID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, showID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (result.getInt("movieID") != this.movieID) {
                    conn.close();
                    return null;
                }
                show.setShowID(showID);
                show.setDate(result.getDate("date"));
                show.setStartTime(result.getTime("startTime"));
                show.setEndTime(result.getTime("endTime"));
                show.setMovieID(this.movieID);
                show.setCinemaHallID(result.getInt("cinemaHallID"));
            } else {
                conn.close();
                return null;
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return show;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieID == movie.movieID &&
                title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieID, title);
    }
}
