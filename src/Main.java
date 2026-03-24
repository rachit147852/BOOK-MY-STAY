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

/* Shared Booking Queue */
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.offer(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}

/* Inventory Service */
class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
    }

    public synchronized boolean allocateRoom(String roomType, String guestName) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " booked " + roomType + " for " + guestName);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed (No rooms) for " + guestName);
            return false;
        }
    }
}

/* Booking Processor Thread */
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private InventoryService inventory;

    public BookingProcessor(String name, BookingQueue queue, InventoryService inventory) {
        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation r;

            synchronized (queue) {
                r = queue.getRequest();
            }

            if (r == null) break;

            inventory.allocateRoom(r.roomType, r.guestName);
        }
    }
}

/* Main Class */
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Concurrent Booking v11.0\n");

        BookingQueue queue = new BookingQueue();
        InventoryService inventory = new InventoryService();

        // Add booking requests
        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Single"));
        queue.addRequest(new Reservation("Charlie", "Single"));

        // Create threads
        Thread t1 = new BookingProcessor("Thread-1", queue, inventory);
        Thread t2 = new BookingProcessor("Thread-2", queue, inventory);

        t1.start();
        t2.start();
    }
}