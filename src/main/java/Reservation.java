import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Data
@NoArgsConstructor

public class Reservation {
    private String customerName;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int numOfGuests;
    private UUID uuid = UUID.randomUUID();
    private Map<Food, Integer> orderedFood;

    public Reservation(String customerName, LocalDate reservationDate, LocalTime reservationTime, int numOfGuests) {
        this.customerName = customerName;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numOfGuests = numOfGuests;
        this.uuid = UUID.randomUUID();
        this.orderedFood = new LinkedHashMap<>();

    }


    @Override
    public String toString() {
        return "\tCustomer name: " + customerName + "\n\tReservation Date: " + reservationDate
                +"\n\tReservation Time: " + reservationTime + "\n\tNumber of guests: " + numOfGuests
                +"\n\tReservation Id: " + uuid;
    }
}
