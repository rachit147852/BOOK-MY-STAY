import java.io.*;
import java.util.*;

/* Reservation Class */
class Reservation implements Serializable {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println(guestName + " | " + roomType + " | " + roomId);
    }
}

/* System State */
class SystemState implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();
    List<Reservation> bookings = new ArrayList<>();
}

/* Persistence Service */
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    public static void save(SystemState state) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("State saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    public static SystemState load() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("State loaded successfully.");
            return (SystemState) ois.readObject();

        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return new SystemState();
        }
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Persistence v12.0\n");

        // Load previous state
        SystemState state = PersistenceService.load();

        // If first run, initialize
        if (state.inventory.isEmpty()) {
            state.inventory.put("Single", 2);
            state.inventory.put("Double", 2);

            state.bookings.add(new Reservation("Alice", "Single", "S101"));
            state.bookings.add(new Reservation("Bob", "Double", "D201"));
        }

        // Display current state
        System.out.println("\nCurrent Bookings:");
        for (Reservation r : state.bookings) {
            r.display();
        }

        System.out.println("\nInventory:");
        for (String key : state.inventory.keySet()) {
            System.out.println(key + ": " + state.inventory.get(key));
        }

        // Save state before exit
        PersistenceService.save(state);
    }
}