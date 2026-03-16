abstract class Room {

    protected String roomType;
    protected int beds;
    protected double size;
    protected double price;

    public Room(String roomType, int beds, double size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq.ft");
        System.out.println("Price     : ₹" + price + " per night");
    }
}


/* Single Room */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 2500);
    }
}


/* Double Room */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 4000);
    }
}


/* Suite Room */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 550, 7500);
    }
}


/* Main Class for Use Case 2 */
public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("     Hotel Booking System v2.1");
        System.out.println("====================================");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("\nAvailable Room Types\n");

        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailability);
        System.out.println("------------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailability);
        System.out.println("------------------------------------");

        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
        System.out.println("------------------------------------");
    }
}
