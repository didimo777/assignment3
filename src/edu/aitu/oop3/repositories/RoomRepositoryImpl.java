package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Room;
import edu.aitu.oop3.exceptions.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public void create(Room room) {
        String sql = "insert into rooms (room_type, price, available) values (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, room.getRoomType());
            st.setDouble(2, room.getPrice());
            st.setBoolean(3, room.isAvailable());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Room findById(int id) {
        String sql = "select * from rooms where id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next())
                throw new NotFoundException("Room not found");

            return new Room(
                    rs.getInt("id"),
                    rs.getString("room_type"),
                    rs.getDouble("price"),
                    rs.getBoolean("available")
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "select * from rooms";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("id"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                ));
            }
            return rooms;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
