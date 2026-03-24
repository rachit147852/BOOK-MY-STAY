import java.util.*;

/* Reservation Class */
class Reservation {

    String reservationId;
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

/* Cancellation Service */
class CancellationService {

    // Inventory
    Map<String, Integer> inventory = new HashMap<>();

    // Active bookings
    Map<String, Reservation> bookings = new HashMap<>();

    // Stack for rollback (released room IDs)
    Stack<String> rollbackStack = new Stack<>();

    public CancellationService() {
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);

        // Pre-existing confirmed bookings
        bookings.put("R1", new Reservation("R1", "Alice", "Single", "S101"));
        bookings.put("R2", new Reservation("R2", "Bob", "Double", "D201"));
    }

    public void cancelBooking(String reservationId) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation not found");
            return;
        }

        Reservation r = bookings.get(reservationId);

        // Push room ID into rollback stack
        rollbackStack.push(r.roomId);

        // Restore inventory
        inventory.put(r.roomType, inventory.get(r.roomType) + 1);

        // Remove booking
        bookings.remove(reservationId);

        System.out.println("Cancellation Successful:");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Released Room ID: " + r.roomId);
        System.out.println("Inventory Restored for: " + r.roomType);
        System.out.println("-----------------------------------");
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Recently Released Rooms): " + rollbackStack);
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Cancellation v10.0\n");

        CancellationService service = new CancellationService();

        // Valid cancellation
        service.cancelBooking("R1");

        // Invalid cancellation
        service.cancelBooking("R3");

        service.showRollbackStack();
    }
}