package org.ncu.movieappcollege.Model;

public class Booking {

    private int  bookingId;
    private int  customerId;
    private String bookingDate;
    private String bookingTime;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Booking() {
    }

    public Booking(int bookingId, int customerId) {
        this.bookingId = bookingId;
        this.customerId = customerId;
    }

}
