import java.util.*;

/* Reservation Class */
class Reservation {

    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println("Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId);
    }
}

/* Booking History */
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

/* Reporting Service */
class BookingReportService {

    public void generateReport(List<Reservation> history) {

        System.out.println("\n===== Booking History Report =====\n");

        if (history.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : history) {
            r.display();
        }

        System.out.println("\nTotal Bookings: " + history.size());
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Booking History v8.0\n");

        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulated confirmed bookings
        bookingHistory.addReservation(new Reservation("Alice", "Single", "S101"));
        bookingHistory.addReservation(new Reservation("Bob", "Double", "D201"));
        bookingHistory.addReservation(new Reservation("Charlie", "Suite", "SU301"));

        // Generate report
        reportService.generateReport(bookingHistory.getHistory());
    }
}