package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String query = "insert into movie (title, description, duration, language, releaseDate, country, genre)"
                            + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, movie.getTitle());
            preparedStmt.setString(2, movie.getDescription());
            preparedStmt.setTime(3, movie.getDuration());
            preparedStmt.setString(4, movie.getLanguage());
            preparedStmt.setDate(5, movie.getReleaseDate());
            preparedStmt.setString(6, movie.getCountry());
            preparedStmt.setString(7, movie.getGenre());

            preparedStmt.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editMovie(Movie movie) {
        Connection conn = this.getConnection();

        try {
            String query = "update movie set " +
                            "title = ?, " +
                            "description = ?, " +
                            "duration = ?, " +
                            "language = ?, " +
                            "releaseDate = ?, " +
                            "country = ?, " +
                            "genre = ?" +
                            "where movieID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDescription());
            statement.setTime(3, movie.getDuration());
            statement.setString(4, movie.getLanguage());
            statement.setDate(5, movie.getReleaseDate());
            statement.setString(6, movie.getCountry());
            statement.setString(7, movie.getGenre());
            statement.setInt(8, movie.getMovieID());
            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteMovie(Movie movie) {
        Connection conn = this.getConnection();

        try {
            String query = "delete from movie where movieID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, movie.getMovieID());
            statement.execute();

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Movie> getMovies() {
        Connection conn = this.getConnection();
        List<Movie> movies = new ArrayList<>();
        try {
            String query = "Select * from movie";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Movie movie = new Movie();
                movie.setMovieID(result.getInt("movieID"));
                movie.setTitle(result.getString("title"));
                movie.setDescription(result.getString("description"));
                movie.setDuration(result.getTime("duration"));
                movie.setLanguage(result.getString("language"));
                movie.setReleaseDate(result.getDate("releaseDate"));
                movie.setCountry(result.getString("country"));
                movie.setGenre(result.getString("genre"));

                movies.add(movie);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movies;
    }

    public Movie getMovieByID(int movieID) {
        Connection conn = this.getConnection();
        Movie movie = new Movie();

        try {
            String query = "Select * from movie where movieID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, movieID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                movie.setMovieID(result.getInt("movieID"));
                movie.setTitle(result.getString("title"));
                movie.setDescription(result.getString("description"));
                movie.setDuration(result.getTime("duration"));
                movie.setLanguage(result.getString("language"));
                movie.setReleaseDate(result.getDate("releaseDate"));
                movie.setCountry(result.getString("country"));
                movie.setGenre(result.getString("genre"));
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movie;
    }

    public Movie getMovieByTitle(String title) {
        Connection conn = this.getConnection();
        Movie movie = new Movie();

        try {
            String query = "Select * from movie where title = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                movie.setMovieID(result.getInt("movieID"));
                movie.setTitle(result.getString("title"));
                movie.setDescription(result.getString("description"));
                movie.setDuration(result.getTime("duration"));
                movie.setLanguage(result.getString("language"));
                movie.setReleaseDate(result.getDate("releaseDate"));
                movie.setCountry(result.getString("country"));
                movie.setGenre(result.getString("genre"));
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movie;
    }

    public User getUserIDByEmail(String email) {
        Connection conn = this.getConnection();
        User user = new User();

        try {
            String query = "Select * from user where email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,email);

            ResultSet result = preparedStmt.executeQuery();

            if (result.next()) {
                user.setUserID(result.getInt("userID"));
                user.setName(result.getString("name"));
                user.setBirth(result.getDate("birth"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setPhoneNumber(result.getString("phone"));
                user.setUserType(result.getInt("userType"));
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User getUserByID(int userID) {
        Connection conn = this.getConnection();
        User user = new User();

        try {
            String query = "Select * from user where userID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, userID);

            ResultSet result = preparedStmt.executeQuery();

            if (result.next()) {
                user.setUserID(result.getInt("userID"));
                user.setName(result.getString("name"));
                user.setBirth(result.getDate("birth"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setPhoneNumber(result.getString("phone"));
                user.setUserType(result.getInt("userType"));
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        Connection conn = this.getConnection();

        try {
            String query = "insert into user (name, birth, username, password, email, phone, userType)"
                            + "values (?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setDate(2, user.getBirth());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setInt(7, user.getUserType());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editUser(User user) {
        Connection conn = this.getConnection();
        try {
            String query = "update user set " +
                            "name = ?," +
                            "birth = ?," +
                            "username = ?," +
                            "password = ?," +
                            "email = ?," +
                            "phone = ?" +
                            "where userID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setDate(2, user.getBirth());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setInt(7, user.getUserID());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        Connection conn = this.getConnection();

        try {
            String query = "Delete from user where userID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, user.getUserID());

            statement.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean checkUser(int userID, String email) {
        Connection conn = this.getConnection();
        try {
            String query = "Select * from user where userID = ? and email = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setString(2, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
