package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Room;
import edu.aitu.oop3.repositories.RoomRepository;

import java.util.List;

public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public void addRoom(Room room) {
        if (room.getPrice() <= 0)
            throw new IllegalArgumentException("Price must be positive");
        repository.create(room);
    }

    public Room getRoom(int id) {
        return repository.findById(id);
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }
}

