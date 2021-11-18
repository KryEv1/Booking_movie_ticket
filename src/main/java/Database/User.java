package Database;

import java.sql.Date;
import java.util.Objects;

public class User {
    private int userID;
    private String name;
    private Date birth;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int userType;

    public User() {}

    public User(String name, Date birth, String username, String password, String email, String phoneNumber) {
        this.name = name;
        this.birth = birth;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = 1;
    }

    public User(int userID, String name, Date birth, String username, String password, String email, String phoneNumber, int userType) {
        this.userID = userID;
        this.name = name;
        this.birth = birth;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID &&
                username.equals(user.username) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, email);
    }
}
