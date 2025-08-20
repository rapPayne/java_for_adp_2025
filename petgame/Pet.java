package petgame;

import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    protected int hunger=5;
    protected int cleanliness=5;
    public int happiness=5;
    private String name;
    private boolean isAlive;
    // #region Timer logic
    private Timer timer = new Timer();
    private TimerTask hungerTask = new TimerTask() {
        public void run() {
            hunger++;
            cleanliness = cleanliness - 1;
            checkHealth();
            System.out.printf("%s is getting hungrier. (%d).\n", name, hunger);
        }
    };
    // #endregion

    // #region Constructors
    public Pet(String name) {
        this.name = name;
        this.isAlive = true;
        System.out.println("Your pet " + name + " has been born.");
        timer.scheduleAtFixedRate(hungerTask, 15000, 15000);
    }

    public Pet() {
        this("Fido");
    }
    // #endregion

    // public void setName(String value) {
    //     name = value;
    // }
    public String getName() {
        return name;
    }
    public boolean getIsAlive() {
        return isAlive;
    }

    public int getHunger() {
        return hunger;
    }
    // public void setHunger(int value) throws Exception {
    //     if (value < 0 || value > 10) {
    //         throw new Exception("Bad hunger value. Must be between 0 and 10");
    //     }
    //     hunger = value;
    // }

    public void wash() {
        cleanliness = 10;
        happiness++;
        hunger++;
        checkHealth();
    }
    public void eat() {
        hunger-=1;
        checkHealth();
    }

    public void play() {
        happiness++;
        cleanliness--;
        hunger++;
        checkHealth();
    }
    int getCleanliness() {
        return cleanliness;
    }

    private void checkHealth() {
        if (hunger > 10) {
            System.out.printf("%s has starved. 💀\n\n", name);
            System.exit(1);
        }
        if (cleanliness <= 0) {
            System.out.printf("%s got an infection and died. 💀\n\n", name);
            System.exit(2);
        }
    }

}
