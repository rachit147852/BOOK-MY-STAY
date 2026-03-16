import java.util.LinkedList;
import java.util.Queue;

/* Reservation Class */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

/* Booking Request Queue Manager */
class Main {

    private Queue<Reservation> requestQueue = new LinkedList<>();

    /* Add booking request */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added to queue.");
    }

    /* Display queued requests */
    public void showRequests() {

        System.out.println("\nCurrent Booking Request Queue:\n");

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("     Hotel Booking System v5.0");
        System.out.println("====================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        /* Simulating guest booking requests */
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        bookingQueue.showRequests();
    }
}