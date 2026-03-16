
import java.util.ArrayList;

class Room {
    String roomType;
    int price;

    Room(String roomType, int price) {
        this.roomType = roomType;
        this.price = price;
    }

    void display() {
        System.out.println("Room Type: " + roomType + " | Price: ₹" + price);
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Room Inventory v3.0\n");

        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Single Room", 2500));
        rooms.add(new Room("Double Room", 4000));
        rooms.add(new Room("Suite Room", 7500));

        for (Room r : rooms) {
            r.display();
        }
    }
}
