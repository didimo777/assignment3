package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Booking;
import java.util.List;

public interface BookingRepository {
    void create(Booking booking);
    Booking findById(int id);
    List<Booking> findAll();
    List<Booking> findByGuestId(int guestId);
    List<Booking> findByRoomId(int roomId);
}
