import java.util.*;

/* Reservation Class */
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

/* Booking Queue (FIFO) */
class BookingQueue {

    Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(Reservation r) {
        requestQueue.offer(r);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasRequests() {
        return !requestQueue.isEmpty();
    }
}

/* Inventory Service */
class InventoryService {

    Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

/* Booking Service */
class BookingService {

    Map<String, Set<String>> allocatedRooms = new HashMap<>();
    Set<String> usedRoomIds = new HashSet<>();

    public void allocateRoom(Reservation r, InventoryService inventory) {

        if (!inventory.isAvailable(r.roomType)) {
            System.out.println("No rooms available for " + r.roomType);
            return;
        }

        String roomId;

        do {
            roomId = r.roomType.substring(0,1).toUpperCase() + new Random().nextInt(100);
        } while (usedRoomIds.contains(roomId));

        usedRoomIds.add(roomId);

        allocatedRooms
                .computeIfAbsent(r.roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.decrementRoom(r.roomType);

        System.out.println("Reservation Confirmed:");
        System.out.println("Guest: " + r.guestName);
        System.out.println("Room Type: " + r.roomType);
        System.out.println("Allocated Room ID: " + roomId);
        System.out.println("-----------------------------");
    }
}

/* Main Class */
public class Main {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Room Allocation v6.0\n");

        BookingQueue queue = new BookingQueue();
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService();

        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Double"));
        queue.addRequest(new Reservation("Charlie", "Suite"));

        while (queue.hasRequests()) {

            Reservation r = queue.getNextRequest();
            bookingService.allocateRoom(r, inventory);
        }
    }
}