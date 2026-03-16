

import java.util.HashMap;
import java.util.Map;

/* Room Domain Class */
class Room {

    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price + " per night");
    }
}

/* Search Service */
class RoomSearchService {

    public static void searchAvailableRooms(
            Map<String, Integer> inventory,
            Map<String, Room> roomCatalog) {

        System.out.println("\nAvailable Rooms\n");

        for (String type : inventory.keySet()) {

            int available = inventory.get(type);

            // Defensive check
            if (available > 0) {

                Room room = roomCatalog.get(type);

                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("----------------------------------");
            }
        }
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("     Hotel Booking System v4.0");
        System.out.println("====================================");

        /* Room Catalog (Domain Objects) */
        Map<String, Room> roomCatalog = new HashMap<>();

        roomCatalog.put("Single", new Room("Single Room", 1, 2500));
        roomCatalog.put("Double", new Room("Double Room", 2, 4000));
        roomCatalog.put("Suite", new Room("Suite Room", 3, 7500));

        /* Inventory (State Holder) */
        Map<String, Integer> inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 0);
        inventory.put("Suite", 2);

        /* Search Operation (Read Only) */
        RoomSearchService.searchAvailableRooms(inventory, roomCatalog);
    }
}
