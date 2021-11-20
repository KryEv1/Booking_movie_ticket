package Database;

import java.sql.Timestamp;
import java.util.Objects;

public class Payment {
    private int paymentID;
    private int amount;
    private Timestamp timestamp;
    private String discountCoupon;
    private int paymentMethod;
    private int bookingID;

    public Payment() {
    }

    public Payment(int amount, Timestamp timestamp, String discountCoupon, int paymentMethod, int bookingID) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.discountCoupon = discountCoupon;
        this.paymentMethod = paymentMethod;
        this.bookingID = bookingID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(String discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
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
        Payment payment = (Payment) o;
        return paymentID == payment.paymentID && bookingID == payment.bookingID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentID, bookingID);
    }
}
