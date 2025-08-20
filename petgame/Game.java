//TODO: Create a PetType enum
//TODO: Decouple the business class from the console (Use a listener via Interface)
package petgame;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            Pet pet;
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
                    throw new BadPetTypeException("Bad pet type");
            }

            boolean running = true;
            while (running) {
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

        } catch (BadPetTypeException e) {
            System.out.println(e.getMessage());
            System.exit(99);

        } catch (DeadPetException e) {
            System.out.println("Yo, your animal died!" + e.getMessage());
            System.exit(100);

        } finally {
            scanner.close();
        }

    }
}
