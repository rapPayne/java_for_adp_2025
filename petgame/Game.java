//TODO: Create a PetType enum
//TODO: Decouple the dying via a custom exception.
//TODO: Decouple the business class from the console (Use a listener via Interface)
package petgame;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception{
        Pet pet;
        Scanner scanner = new Scanner(System.in);
        System.out.print("What would you like to name your pet? ");
        String name = scanner.nextLine();
        System.out.print("What kind of pet? ");
        String petType = scanner.nextLine();
        switch (petType) {
            case "cat":
                pet = Cat.withName(name);
                break;
            case "dog":
                pet = Dog.withName(name);
                break;
            default:
                System.err.println("That's not a valid pet type");
                scanner.close();
                throw new Exception("Bad pet type");
        }

        boolean running = true;
        while (running && pet.getIsAlive()) {

            System.out.print("""
                    Your options:
                    1) Wash
                    2) Feed
                    3) Play
                    4) Exit
                    """);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    pet.wash();
                    break;
                case "2":
                    pet.eat();
                    break;
                case "3":
                    pet.play();
                    break;
                case "4":
                    running = false;
                default:
                    System.err.printf("'%s' isn't a valid option. Please try again.\n", input);
            }
            System.out.printf("Pet status: cleanliness: %d, happiness: %d, hunger: %d\n\n", pet.getCleanliness(),
                    pet.getHappiness(), pet.hunger);
        }

        scanner.close();
        // Provide a check status? Or just print it out each time.
    }
}

// OO Concepts