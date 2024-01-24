import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String fullName;
    private List<Reservation> reservations;
    private UUID id;

    public Customer(String fullName) {
        this.fullName = fullName;
        this.reservations = new ArrayList<>();
        this.id = UUID.randomUUID();
    }
}
