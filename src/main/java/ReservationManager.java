import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class ReservationManager {
    static Scanner scanner = new Scanner(System.in);
    public static void viewRestaurantDetails() {
        System.out.println("---------- Restaurant Details ----------");
        System.out.println("\tName: Axmad Golden Chicken");
        System.out.println("\tLocation: Sergili district, Highway A17");
        System.out.println("\tPhone: +998971230321");
        System.out.println("\tNational Rank: 19");
        System.out.println("\tMore details: https://axmadgoldenchicken.uz");
        System.out.println("----------------------------------------");
    }

    public static void reserveTable() {
        if (Restaurant.reservations.size() > 10){
            System.out.println("No seats left to reserve!");
        } else {
            System.out.println("--------------- Reserving Table ------------------");
            System.out.print("=> Enter your name: ");
            String customerName = scanner.nextLine();

            int numOfGuests = 0;
            boolean nextCount = true;
            while (nextCount) {
                try {
                    System.out.print("=> Enter number of guests: ");
                    numOfGuests = Integer.parseInt(scanner.nextLine());
                    nextCount = false;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, enter number!");
                }
            }

            DateTimeFormatter formatter = null;
            LocalDate reservationDate = null;

            DateTimeFormatter formatterTime = null;
            LocalTime reservationTime = null;
            Reservation reservation = null;
            boolean nextChoosenTime = true;
            while (nextChoosenTime) {
                boolean nextDate = true;
                while (nextDate){
                    try{
                        System.out.print("=> Enter date (e.g., dd-MM-yyyy): ");
                        String reservationDateStr = scanner.nextLine();
                        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        reservationDate = LocalDate.parse(reservationDateStr, formatter);
                        nextDate = false;
                    } catch (DateTimeParseException e){
                        System.out.println("Input date as shown format (dd-MM-yyyy)!");
                    }
                }
                boolean nextTime = true;
                while (nextTime) {
                    try {
                        System.out.print("=> Enter time (e.g., HH:mm): ");
                        String reservationTimeStr = scanner.nextLine();
                        formatterTime = DateTimeFormatter.ofPattern("HH:mm");
                        reservationTime = LocalTime.parse(reservationTimeStr, formatterTime);
                        nextTime = false;
                    } catch (DateTimeParseException e){
                        System.out.println("Input time as shown pattern (dd-MM-yyyy)!");
                    }
                }

                reservation = new Reservation(customerName, reservationDate, reservationTime, numOfGuests);
                boolean noConflict = true;
                if (Restaurant.reservations.size() > 0) {
                    for (Reservation reservationDone : Restaurant.reservations) {
                        if (reservation.getReservationDate().equals(reservationDone.getReservationDate()) &&
                                reservation.getReservationTime().equals(reservationDone.getReservationTime())) {
                            noConflict = false;
                            break;
                        }
                    }
                    if (noConflict){
                        nextChoosenTime = false;
                    } else {
                        System.out.println("You have time conflict, please choose another date or time!");
                    }
                } else {
                    System.out.println("Reservation successful");
                    nextChoosenTime = false;
                }
            }




            boolean next = true;
            while (next) {
                Menu.displayMenuItems(Menu.foodList);

                boolean foodNext = true;
                int foodNum = 0;
                while (foodNext) {
                    try {
                        System.out.print("=> Choose food by number: ");
                        foodNum = Integer.parseInt(scanner.nextLine());

                        if (foodNum > Menu.foodList.size()) {
                            System.out.println("Invalid input, available enter number!");
                        } else {
                            foodNext = false;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, enter number!");
                    }
                }

                int foodCount = 0;
                boolean nextFoodCount = true;
                while (nextFoodCount){
                    try {
                        System.out.print("=> Enter the count of food: ");
                        foodCount = Integer.parseInt(scanner.nextLine());

                        if (foodCount > 10) {
                            System.out.println("You cant get more that 10 meal");
                        } else if (foodCount == 0){
                            System.out.println("You cant get meal, buy some meal!");
                        } else {
                            nextFoodCount = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, enter number!");
                    }
                }

                Food food = Menu.foodList.get(foodNum - 1);

                reservation.getOrderedFood().put(food, foodCount);

                boolean nextYesNo = true;
                while (nextYesNo) {
                    System.out.print("Do you want to add again (yes/no): ");
                    String answer = scanner.nextLine();
                    if (answer.equals("no")){
                        next = false;
                        nextYesNo = false;
                        break;
                    } else if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("Choosing again...");
                        nextYesNo = false;

                    } else {
                        System.out.println("Invalid input!");
                    }
                }
            }
            Restaurant.reservations.add(reservation);


        }

    }

    public static void viewReservationDetails() {
        System.out.println("------------- Reservation Details --------------");
        System.out.println("------------------------------------------------");
        if (Restaurant.reservations.size() > 0) {
            int totalPrice = 0;
            for (int i = 0; i < Restaurant.reservations.size(); i++) {
                System.out.println(Restaurant.reservations.get(i));
                System.out.println("\n\tMeals:");

                Map<Food, Integer> orderedFood = Restaurant.reservations.get(i).getOrderedFood();

                for (Map.Entry<Food, Integer> entry : orderedFood.entrySet()) {
                    Food food = entry.getKey();
                    Integer count = entry.getValue();
                    System.out.println("\t" + count + " " + food.getFoodName());
                    totalPrice += food.getPrice() * count;
                }
                System.out.println("\t Total price: $" + totalPrice);
                totalPrice = 0;
                System.out.println("------------------------------------------------");
            }
        } else {
            System.out.println("\tNo Reservations Recorded");
        }

    }
    public static void cancelReservation() {
        System.out.println("-------------- Reservations List ---------------");
        System.out.println("------------------------------------------------");
        if (Restaurant.reservations.size() > 0) {
            for (int i = 0; i < Restaurant.reservations.size(); i++) {
                System.out.println(Restaurant.reservations.get(i));
                System.out.println("------------------------------------------------");
            }
            boolean nextCancelReservation = true;
            while (nextCancelReservation) {
                System.out.print("=> Enter ID of reservation to cancel: ");
                String reservationId = scanner.nextLine();

                if (Restaurant.reservations.size() > 0) {
                    boolean reservationFound = false;
                    for (Reservation reservation : Restaurant.reservations) {
                        String id = String.valueOf(reservation.getUuid());
                        if (id.equals(reservationId)){
                            Restaurant.reservations.remove(reservation);
                            System.out.println("\tReservation Cancelled Successfully!");
                            nextCancelReservation = false;
                            reservationFound = true;
                            break;
                        }
                    }

                    if (reservationFound == false) {
                        System.out.println("Error, No reservation with this id: " + reservationId);
                    }

                } else {
                    System.out.println("\tNo reservations Found!");
                    nextCancelReservation = false;
                }
            }
        } else {
            System.out.println("\tNo Reservation ");
        }

    }

    public static void updateReservation() {
        System.out.println("----------------Updating reservation ----------------");
        System.out.println("-------------- Reservations List ---------------");
        System.out.println("------------------------------------------------");
        if (Restaurant.reservations.size() > 0) {
            for (int i = 0; i < Restaurant.reservations.size(); i++) {
                System.out.println(Restaurant.reservations.get(i));
                System.out.println("------------------------------------------------");
            }

            boolean nextUpdate = true;
            while (nextUpdate) {
                System.out.print("=> Enter ID of reservation to update: ");
                String reservationId = scanner.nextLine();
                for (Reservation reservation : Restaurant.reservations) {
                    String strId = String.valueOf(reservation.getUuid());
                    if (strId.equals(reservationId)){

                        boolean nextUpdateDate = true;
                        while (nextUpdateDate) {
                            try {
                                System.out.print("=> Updated date (e.g., dd-MM-yyyy) or leave blank not to update: ");
                                String reservationDateStr = scanner.nextLine();
                                if (!reservationDateStr.equals("")){
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                    LocalDate reservationDate = LocalDate.parse(reservationDateStr, formatter);
                                    reservation.setReservationDate(reservationDate);
                                    nextUpdateDate = false;

                                } else {
                                    nextUpdateDate = false;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid input, enter date correctly (dd-MM-yyyy)");
                            }
                        }


                        boolean nextUpdateTime = true;
                        while (nextUpdateTime) {
                            try {
                                System.out.print("=> Update time (e.g., HH:mm) or leave blank not to update: ");
                                String reservationTimeStr = scanner.nextLine();
                                if (!reservationTimeStr.equals("")){
                                    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
                                    LocalTime reservationTime = LocalTime.parse(reservationTimeStr, formatterTime);
                                    reservation.setReservationTime(reservationTime);
                                    nextUpdateTime = false;
                                } else {
                                    nextUpdateTime = false;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid input, enter valid time format(HH:mm) !");
                            }
                        }




                        boolean nextUpdateGuestCount = true;
                        while (nextUpdateGuestCount) {
                            try {
                                System.out.print("=> Update number of guests or leave blank not to update: ");
                                String numOfCustomerStr = scanner.nextLine();
                                if (!numOfCustomerStr.equals("")){
                                    int numOfGuests = Integer.parseInt(numOfCustomerStr);
                                    reservation.setNumOfGuests(numOfGuests);
                                    nextUpdateGuestCount = false;
                                } else {
                                    nextUpdateGuestCount = false;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input, enter number!");
                            }
                        }


                        boolean next = true;
                        reservation.getOrderedFood().clear();
                        while (next) {
                            Menu.displayMenuItems(Menu.foodList);

                            boolean foodNext = true;
                            int foodNum = 0;
                            while (foodNext) {
                                try {
                                    System.out.print("=> Choose food by number: ");
                                    foodNum = Integer.parseInt(scanner.nextLine());

                                    if (foodNum > Menu.foodList.size()) {
                                        System.out.println("Invalid input, available enter number!");
                                    } else {
                                        foodNext = false;
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input, enter number!");
                                }
                            }

                            int foodCount = 0;
                            boolean nextFoodCount = true;
                            while (nextFoodCount){
                                try {
                                    System.out.print("=> Enter the count of food: ");
                                    foodCount = Integer.parseInt(scanner.nextLine());

                                    if (foodCount > 10) {
                                        System.out.println("You cant get more that 10 meal");
                                    } else if (foodCount == 0){
                                        System.out.println("You cant get meal, buy some meal!");
                                    } else {
                                        System.out.println("Successfully Updated!!!");
                                        nextFoodCount = false;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input, enter number!");
                                }
                            }

                            Food food = Menu.foodList.get(foodNum - 1);

                            reservation.getOrderedFood().put(food, foodCount);

                            boolean nextYesNo = true;
                            while (nextYesNo) {
                                System.out.print("Do you want to add again (yes/no): ");
                                String answer = scanner.nextLine();
                                if (answer.equals("no")){
                                    next = false;
                                    nextYesNo = false;
                                    break;
                                } else if (answer.equalsIgnoreCase("yes")) {
                                    System.out.println("Choosing again...");
                                    nextYesNo = false;

                                } else {
                                    System.out.println("Invalid input!");
                                }
                            }
                        }
//                        Restaurant.reservations.add(reservation);
                    } else {
                        System.out.println("No reservation with id: " + reservationId);
                    }
                    break;
                }
                break;
            }



        } else {
            System.out.println("No Reservations recorded");
        }


    }

    public static void viewReservationDetailsForCustomer() {
    }

    public static void cancelReservationForCustomer() {
        System.out.println("-------------- Reservations List ---------------");
        System.out.println("------------------------------------------------");
        if (Restaurant.reservations.size() > 0) {
            for (int i = 0; i < Restaurant.reservations.size(); i++) {
                System.out.println(Restaurant.reservations.get(i));
                System.out.println("------------------------------------------------");
            }

            boolean nextReservationSpecificCancel = true;
            while (nextReservationSpecificCancel) {
                System.out.print("=> Enter ID of reservation to cancel: ");
                String reservationId = scanner.nextLine();

                if (Restaurant.reservations.size() > 0) {
                    for (Reservation reservation : Restaurant.reservations) {
                        String id = String.valueOf(reservation.getUuid());
                        if (id.equals(reservationId)){
                            Restaurant.reservations.remove(reservation);
                            System.out.println("\tReservation Cancelled Successfully!");
                            nextReservationSpecificCancel = false;
                            break;
                        } else {
                            System.out.println("Wrong id, no reservation with this id: " + reservationId);
                        }
                    }
                } else {
                    System.out.println("\tNo reservations Found!");
                    nextReservationSpecificCancel = false;
                }
            }

        } else {
            System.out.println("\tNo Reservation ");
        }

    }

    public static void viewReservationForCustomer() {
        System.out.println("------------ View Reservation for Specific Customer ----------");

        System.out.print("=> Enter the name of customer: ");
        String customerName = scanner.nextLine();
        boolean reservationFound = false;
        for (Reservation reservation : Restaurant.reservations) {
            System.out.println("----------------------------------------");
            if (reservation.getCustomerName().equals(customerName)){
                System.out.println(reservation);
                System.out.println("----------------------------------------");
                reservationFound = true;
            }
        }
        if (reservationFound == false) {
            System.out.println("No such customer made reservation");
        }

    }
}
