import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private static final String url = "jdbc:mysql://localhost:3306/bmt_database";
    private static final String user = "root";
    private static final String password = "root";

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void addMovie(Movie movie) {
        Connection conn = this.getConnection();

        try {
            String query = "insert into movie (title, description, duration, language, releaseDate, country, genre)" + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, movie.getTitle());
            preparedStmt.setString(2, movie.getDescription());
            preparedStmt.setTime(3, movie.getDuration());
            preparedStmt.setString(4, movie.getLanguage());
            preparedStmt.setDate(5, movie.getReleaseDate());
            preparedStmt.setString(6, movie.getCountry());
            preparedStmt.setString(7, movie.getGenre());

            preparedStmt.execute();
            if (conn != null) {
                System.out.println("Movie added!");
                conn.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
