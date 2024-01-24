import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static List<Food> foodList = new ArrayList<>();

    static public void displayMenuItems(List<Food> foodList){
        System.out.println("--------------- Food Menu -----------------");
        if (foodList.size() > 0) {
            for (int i = 0; i < foodList.size(); i++) {
                System.out.println((i + 1) + ". " + foodList.get(i));
            }
        } else {
            System.out.println("The Menu is Empty");
        }
        System.out.println("--------------------------------------------");
    }


    public static void addNewMeal() {
        System.out.println("------------- Adding Meal ----------------");
        System.out.print("Enter the name of new food: ");
        String foodName = scanner.nextLine();
        boolean priceNext = true;
        int price = 0;
        while (priceNext) {
            try {
                System.out.print("Enter price: ");
                price = scanner.nextInt();
                scanner.nextLine();
                priceNext = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter number!");
                scanner.nextLine();
            }
        }
        System.out.print("Enter ingredients: ");
        String ingredients = scanner.nextLine();

        System.out.print("Enter brief description: ");
        String description = scanner.nextLine();

        Food food = new Food(foodName, price, ingredients, description);
        if (foodList.size() < 10) {
            foodList.add(food);
        } else {
            System.out.println("You cant add more than 10 Meal to the Menu!");
        }

        System.out.println("Meal Added Successfully!");
    }

    public static void updateMeal() {
        System.out.println("-------------- Updating Meal ----------------");
        displayMenuItems(foodList);

        boolean updateMealNext = true;
        int foodNumber = 0;
        while (updateMealNext) {
            try {
                System.out.print("=> Enter Food Number to Update: ");
                foodNumber = scanner.nextInt();
                scanner.nextLine();
                if (foodNumber > Menu.foodList.size()) {
                    System.out.println("Invalid input, enter existing food number!");
                } else {
                    updateMealNext = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter number!");
                scanner.nextLine();
            }
        }


        Food foodToUpdate = foodList.get(foodNumber - 1);

        System.out.print("=> Update Food Name or leave it blank if you dont update: ");
        String foodName = scanner.nextLine();
        if (!foodName.equals("")){
            foodToUpdate.setFoodName(foodName);
        }

        System.out.print("=> Update Food Price or leave it blank if you dont update: ");
        String foodPriceStr = scanner.nextLine();
        if (!foodPriceStr.equals("")){
            foodToUpdate.setPrice(Integer.parseInt(foodPriceStr));
        }

        System.out.print("=> Update Food Ingredients or leave it blank if you dont update: ");
        String foodIngredients = scanner.nextLine();
        if (!foodIngredients.equals("")){
            foodToUpdate.setIngredients(foodIngredients);
        }

        System.out.print("=> Update Food Description or leave it blank if you dont update: ");
        String foodDescription = scanner.nextLine();
        if (!foodDescription.equals("")){
            foodToUpdate.setBriefDescription(foodDescription);
        }

        System.out.println("Meal Updated Successfully");

    }

    public static void deleteMeal() {
        System.out.println("-------------- Deleting meal ---------------");
        Menu.displayMenuItems(foodList);

        boolean nextDeleteMeal = true;
        while (nextDeleteMeal) {
            try {
                System.out.print("=> Enter food number to delete: ");
                int foodNumber = scanner.nextInt();
                scanner.nextLine();
                if (foodNumber <= foodList.size()) {
                    foodList.remove(foodNumber - 1);
                    System.out.println("Meal Deleted Successfully!");
                    nextDeleteMeal = false;
                } else {
                    System.out.println("Invalid operation, enter valid number for meal!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter a number!");
                scanner.nextLine();
            }
        }
    }
}
