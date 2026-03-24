
import java.util.*;

/* Service Class */
class Service {

    String serviceName;
    double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public void displayService() {
        System.out.println(serviceName + " - ₹" + cost);
    }
}

/* Add-On Service Manager */
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    public void addService(String reservationId, Service service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added service to Reservation " + reservationId);
    }

    public void displayServices(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services added.");
            return;
        }

        double totalCost = 0;

        System.out.println("\nServices for Reservation " + reservationId + ":\n");

        for (Service s : services) {
            s.displayService();
            totalCost += s.cost;
        }

        System.out.println("\nTotal Add-On Cost: ₹" + totalCost);
    }
}

/* Main Class */
public class RoomInitialization {

    public static void main(String[] args) {

        System.out.println("BOOK MY STAY APP - Add-On Services v7.0\n");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        // Add services
        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Airport Pickup", 1200));
        manager.addService(reservationId, new Service("Spa Access", 1500));

        // Display services
        manager.displayServices(reservationId);
    }
}