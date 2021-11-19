package Database;

import java.util.Objects;

public class CinemaSeat {
    private int seatID;
    private String seatNumber;
    private int type;
    private int cinemaHallID;

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCinemaHallID() {
        return cinemaHallID;
    }

    public void setCinemaHallID(int cinemaHallID) {
        this.cinemaHallID = cinemaHallID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaSeat that = (CinemaSeat) o;
        return seatID == that.seatID && cinemaHallID == that.cinemaHallID && seatNumber.equals(that.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatID, seatNumber, cinemaHallID);
    }
}