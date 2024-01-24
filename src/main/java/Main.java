import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static {
        addDefaultFoodMenu();
        System.out.println("\u001B[33m------------------------------------------- \u001B[0m");
        System.out.println("   \u001B[34m ------    Reservation App   ------ \u001B[0m");
        System.out.println("\u001B[33m------------------------------------------- \u001B[0m");
    }
    public static void main(String[] args) {

        start();

    }



    private static void start() {
        boolean next = true;
        while (next) {
            System.out.println("\t1.Customer");
            System.out.println("\t2.Staff");
            System.out.println("\t0.Exit");
            System.out.print("=> Choose your role (e.g., 1 or 2): ");
            String answer = scanner.nextLine();
            if (answer.equals("1") || answer.equals("2") || answer.equals("0")){
                int roleNum = Integer.parseInt(answer);
                switch (roleNum){
                    case 1:
                        manuForCustomer();
                        break;
                    case 2:
                        manuForStaff();
                        break;
                    case 0:
                        next = false;
                        break;
                }
            } else {
                System.out.println("\t\u001B[31m Invalid input! \u001B[0m");
            }
        }
    }

    private static void manuForCustomer() {
        boolean next = true;
        while (next) {
            System.out.println("========== Menu For Customer ==========");
            System.out.println("\t1.View Restaurant Details");
            System.out.println("\t2.View Food Menu");
            System.out.println("\t3.Reserve Table");
            System.out.println("\t4.Cancel Reservation");
            System.out.println("\t5.Update Reservation");
            System.out.println("\t6.View Reservation Details");
            System.out.println("\t0.Exit Menu");
            System.out.print("=> Choose Action: ");
            String answer = scanner.nextLine();
            if (
                    answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4") ||
                            answer.equals("5") || answer.equals("6") || answer.equals("0")
            ){
                int action = Integer.parseInt(answer);
                switch (action){
                    case 1:
                        ReservationManager.viewRestaurantDetails();
                        break;
                    case 2:
                        Menu.displayMenuItems(Menu.foodList);
                        break;
                    case 3:
                        ReservationManager.reserveTable();
                        break;
                    case 4:
                        ReservationManager.cancelReservation();
                        break;
                    case 5:
                        ReservationManager.updateReservation();
                        break;
                    case 6:
                        ReservationManager.viewReservationDetails();
                        break;
                    case 0:
                        next = false;
                        break;
                }
            } else {
                System.out.println("\t\u001B[31m Invalid input! \u001B[0m");
            }
        }
    }

    private static void addDefaultFoodMenu() {
        Menu.foodList.add(new Food("Palov", 12, "Rice, Carrot", "Choyxona osh"));
        Menu.foodList.add(new Food("Manti", 8, "Meat, Onion", "National Food"));
        Menu.foodList.add(new Food("Somsa", 3, "Cheese, Chicken", "Rich in calorie"));
        Menu.foodList.add(new Food("Sho'rva", 10, "Cabbage, Potato", "Vegetarian meal"));
        Menu.foodList.add(new Food("Lagman", 9, "Meat, Wheat", "Chinese meal"));
        Menu.foodList.add(new Food("Bread", 1, "Wheat", "Uzbek tandir bread"));
        Menu.foodList.add(new Food("Tea", 1, "Green tea", "Indian tea"));
    }


    private static void manuForStaff() {
        boolean next = true;
        while (next) {
            System.out.println("========== Menu For Staff ==========");
            System.out.println("\t1.View Restaurant Details");
            System.out.println("\t2.View Food Menu");
            System.out.println("\t3.View Reservation Details");
            System.out.println("\t4.Add New Meal");
            System.out.println("\t5.Update Meal");
            System.out.println("\t6.Delete Meal");
            System.out.println("\t7.Cancel reservation for specific customer");
            System.out.println("\t8.View Reservation Details for Specific Customer");
            System.out.println("\t0.Exit Menu");
            System.out.print("=> Choose Action: ");
            String answer = scanner.nextLine();

            if (
                    answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4") ||
                            answer.equals("5") || answer.equals("6") || answer.equals("7") || answer.equals("8") ||
                            answer.equals("0")
            ){
                int action = Integer.parseInt(answer);
                switch (action){
                    case 1:
                        ReservationManager.viewRestaurantDetails(); // done
                        break;
                    case 2:
                        Menu.displayMenuItems(Menu.foodList); // done
                        break;
                    case 3:
                        ReservationManager.viewReservationDetails(); // done
                        break;
                    case 4:
                        Menu.addNewMeal(); // done
                        break;
                    case 5:
                        Menu.updateMeal(); // dene
                        break;
                    case 6:
                        Menu.deleteMeal(); // done
                        break;
                    case 7:
                        ReservationManager.cancelReservationForCustomer(); // done
                        break;
                    case 8:
                        ReservationManager.viewReservationForCustomer(); // done
                        break;
                    case 0:
                        next = false;
                        break;
                }
            } else {
                System.out.println("\t\u001B[31m Invalid input! \u001B[0m");
            }
        }
    }
}
