import java.sql.Date;
import java.sql.Time;

public class Main {


    public static void main(String[] args) {
        DatabaseConnection connection = DatabaseConnection.getInstance();

        String title = "Avengers 4: Eng game";
        String description = "Bom tan cua Marvel";
        Time duration = Time.valueOf("03:00:00");
        String language = "English";
        Date released = Date.valueOf("2021-11-18");
        String country = "US";
        String gerne = "Action";

        Movie newMovie = new Movie(title,description,duration,language,released,country,gerne);
        connection.addMovie(newMovie);
    }
}
