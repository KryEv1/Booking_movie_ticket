import java.sql.Date;
import java.sql.Time;

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


}
