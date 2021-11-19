package Database;

import java.util.Objects;

public class ShowSeat {
    private int showSeatID;
    private int status;
    private float price;
    private int cinema_seatID;
    private int showID;
    private int bookingID;

    public ShowSeat() {}

    public ShowSeat(int status, float price, int cinema_seatID, int showID) {
        this.status = status;
        this.price = price;
        this.cinema_seatID = cinema_seatID;
        this.showID = showID;
    }

    public ShowSeat(int status, float price, int cinema_seatID, int showID, int bookingID) {
        this.status = status;
        this.price = price;
        this.cinema_seatID = cinema_seatID;
        this.showID = showID;
        this.bookingID = bookingID;
    }

    public int getShowSeatID() {
        return showSeatID;
    }

    public void setShowSeatID(int showSeatID) {
        this.showSeatID = showSeatID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCinema_seatID() {
        return cinema_seatID;
    }

    public void setCinema_seatID(int cinema_seatID) {
        this.cinema_seatID = cinema_seatID;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowSeat showSeat = (ShowSeat) o;
        return showSeatID == showSeat.showSeatID && cinema_seatID == showSeat.cinema_seatID && showID == showSeat.showID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(showSeatID, cinema_seatID, showID);
    }
}
