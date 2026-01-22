package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Room;
import java.util.List;

public interface RoomRepository {
    void create(Room room);
    Room findById(int id);
    List<Room> findAll();
}

