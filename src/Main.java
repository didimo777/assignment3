import edu.aitu.oop3.entities.Room;
import edu.aitu.oop3.repositories.RoomRepository;
import edu.aitu.oop3.repositories.RoomRepositoryImpl;
import edu.aitu.oop3.services.RoomService;

public class Main {
    public static void main(String[] args) {

        RoomRepository repo = new RoomRepositoryImpl();
        RoomService service = new RoomService(repo);

        service.addRoom(new Room("Single", 15000, true));

        service.getAllRooms().forEach(r ->
                System.out.println(r.getId() + " | " + r.getRoomType() + " | " + r.getPrice())
        );

        Room room = service.getRoom(1);
        System.out.println("Found room: " + room.getRoomType());
    }
}
