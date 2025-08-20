package petgame;

import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    protected int hunger = 5;
    protected int cleanliness = 5;
    private int happiness = 5;
    private String name;

    // #region Timer logic
    private Timer timer = new Timer();
    private TimerTask hungerTask = new TimerTask() {
        public void run() {
            hunger++;
            cleanliness = cleanliness - 1;
            happiness = (hunger + cleanliness) / 2;
            try {
                checkHealth();
            } catch (DeadPetException ex) {
                System.out.printf("%s has died. Sorry!", name);
                this.cancel();
            }
            System.out.printf("%s is getting hungrier. (%d).\n", name, hunger);
        }
    };
    // #endregion

    // #region Constructors
    public Pet(String name) {
        this.name = name;
        System.out.println("Your pet " + name + " has been born.");
        timer.scheduleAtFixedRate(hungerTask, 15000, 15000);
    }

    public Pet() {
        this("Fido");
    }
    // #endregion

    // #region Accessors
    public int getHappiness() {
        return happiness;
    }

    public String getName() {
        return name;
    }

    int getCleanliness() {
        return cleanliness;
    }

    public int getHunger() {
        return hunger;
    }
    // #endregion

    public void wash() throws DeadPetException {
        cleanliness = 10;
        happiness++;
        hunger++;
        checkHealth();
    }

    public void eat() throws DeadPetException {
        hunger -= 1;
        checkHealth();
    }

    public void play() throws DeadPetException {
        happiness++;
        cleanliness--;
        hunger++;
        checkHealth();
    }

    private void checkHealth() throws DeadPetException {
        if (hunger > 10) {
            throw new DeadPetException(String.format("%s has starved. 💀\n\n", name));
        }
        if (cleanliness <= 0) {
            throw new DeadPetException(String.format("%s got an infection and died. 💀\n\n", name));
        }
    }

}
