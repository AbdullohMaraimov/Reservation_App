import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {
    private String foodName;
    private int price;
    private String ingredients;
    private String briefDescription;

    @Override
    public String toString() {
        return "Meal: " + foodName + " | Price: $" + price + " | Ingredients: " + ingredients
                + " | Description: " + briefDescription;
    }
}
