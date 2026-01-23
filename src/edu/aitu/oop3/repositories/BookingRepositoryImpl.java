package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Booking;
import edu.aitu.oop3.exceptions.NotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public void create(Booking booking) {
        String sql = "INSERT INTO bookings (room_id, guest_id, check_in, check_out) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, booking.getRoomId());
            st.setInt(2, booking.getGuestId());
            st.setDate(3, Date.valueOf(booking.getCheckIn()));
            st.setDate(4, Date.valueOf(booking.getCheckOut()));

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create booking", e);
        }
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT id, room_id, guest_id, check_in, check_out FROM bookings WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    throw new NotFoundException("Booking not found: id=" + id);
                }

                return mapBooking(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find booking by id", e);
        }
    }

    @Override
    public List<Booking> findAll() {
        String sql = "SELECT id, room_id, guest_id, check_in, check_out FROM bookings ORDER BY id";
        List<Booking> list = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(mapBooking(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to read bookings", e);
        }
    }

    @Override
    public List<Booking> findByGuestId(int guestId) {
        String sql = "SELECT id, room_id, guest_id, check_in, check_out FROM bookings WHERE guest_id = ? ORDER BY id";
        List<Booking> list = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, guestId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBooking(rs));
                }
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find bookings by guestId", e);
        }
    }

    @Override
    public List<Booking> findByRoomId(int roomId) {
        String sql = "SELECT id, room_id, guest_id, check_in, check_out FROM bookings WHERE room_id = ? ORDER BY id";
        List<Booking> list = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, roomId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBooking(rs));
                }
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find bookings by roomId", e);
        }
    }

    private Booking mapBooking(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int roomId = rs.getInt("room_id");
        int guestId = rs.getInt("guest_id");

        LocalDate checkIn = rs.getDate("check_in").toLocalDate();
        LocalDate checkOut = rs.getDate("check_out").toLocalDate();
        return new Booking(id, roomId, guestId, checkIn, checkOut);
    }
}
