import java.util.*;

/* Custom Exception */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

/* Validator */
class BookingValidator {

    public static void validate(String roomType, Map<String, Integer> inventory)
            throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No availability for room type: " + roomType);
        }
    }
}

/* Booking Service */
class BookingService {

    Map<String, Integer> inventory = new HashMap<>();

    public BookingService() {
        inventory.put("Single", 1);
        inventory.put("Double", 0);
        inventory.put("Suite", 2);
    }

    public void bookRoom(String guestName, String roomType) {

        try {
            BookingValidator.validate(roomType, inventory);

            // If validation passes
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booking Confirmed for " + guestName +
                    " | Room Type: " + roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Error Handling v9.0\n");

        BookingService service = new BookingService();

        // Valid booking
        service.bookRoom("Alice", "Single");

        // Invalid room type
        service.bookRoom("Bob", "Deluxe");

        // No availability
        service.bookRoom("Charlie", "Double");
    }
}