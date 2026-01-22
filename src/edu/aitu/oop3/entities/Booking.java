package edu.aitu.oop3.entities;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int roomId;
    private int guestId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking(int id, int roomId, int guestId,
                   LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
