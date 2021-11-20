package Database;

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shows;
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
