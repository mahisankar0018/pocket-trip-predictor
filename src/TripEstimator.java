/**
 * Core Java logic for Pocket Trip Predictor (console version).
 * This file can be used to demonstrate Java-based implementation
 * of the same formulas used in the web UI.
 */
public class TripEstimator {

    // Transport calculation for road (car / bike)
    public static double roadTransport(double distanceKm,
                                       double mileageKmPerLitre,
                                       double fuelPrice,
                                       double tollPerKm) {

        if (mileageKmPerLitre <= 0) mileageKmPerLitre = 1;
        if (fuelPrice <= 0) fuelPrice = 1;
        if (tollPerKm < 0) tollPerKm = 0;

        double fuelRequired = distanceKm / mileageKmPerLitre;
        double fuelCost = fuelRequired * fuelPrice;
        double tollCost = distanceKm * tollPerKm;

        return fuelCost + tollCost;
    }

    // Transport calculation for flight
    public static double flightTransport(double distanceKm,
                                         double averageFarePerKm,
                                         int passengers,
                                         boolean roundTrip) {

        if (averageFarePerKm <= 0) averageFarePerKm = 1;
        if (passengers <= 0) passengers = 1;

        int legs = roundTrip ? 2 : 1;
        double ticketCost = distanceKm * averageFarePerKm * legs;

        return ticketCost * passengers;
    }

    // Stay cost
    public static double stayCost(int nights,
                                  double roomRate,
                                  int travellers,
                                  int peoplePerRoom) {

        if (nights <= 0 || roomRate <= 0 || travellers <= 0) {
            return 0;
        }
        if (peoplePerRoom <= 0) peoplePerRoom = 2;

        int roomsNeeded = (int) Math.ceil(travellers / (double) peoplePerRoom);
        return roomsNeeded * roomRate * nights;
    }

    // Food cost
    public static double foodCost(int nights,
                                  int travellers,
                                  double foodPerPersonPerDay) {

        if (nights <= 0 || travellers <= 0 || foodPerPersonPerDay <= 0) {
            return 0;
        }
        return nights * travellers * foodPerPersonPerDay;
    }

    public static void main(String[] args) {
        // Example run: weekend road trip
        double distance = 570.0;
        int travellers = 4;

        double transport = roadTransport(distance, 17.5, 110, 1.8);
        double stay = stayCost(2, 1600, travellers, 2);
        double food = foodCost(2, travellers, 350);
        double extras = 1200;

        double total = transport + stay + food + extras;
        double perPerson = total / travellers;

        System.out.println("Pocket Trip Predictor – Console Demo");
        System.out.println("====================================");
        System.out.println("Distance      : " + distance + " km");
        System.out.println("Travellers    : " + travellers);
        System.out.println("Transport     : ₹" + Math.round(transport));
        System.out.println("Stay          : ₹" + Math.round(stay));
        System.out.println("Food          : ₹" + Math.round(food));
        System.out.println("Extras        : ₹" + Math.round(extras));
        System.out.println("Grand total   : ₹" + Math.round(total));
        System.out.println("Per traveller : ₹" + Math.round(perPerson));
    }
}
