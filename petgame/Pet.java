package petgame;

import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    public interface PetListener {
        void emit(String message);
    }

    protected int hunger = 5;
    protected int cleanliness = 5;
    private int happiness = 5;
    private String name;
    protected PetListener listener;

    public void setListener(PetListener listener) {
        this.listener = listener;
    }
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
                listener.emit(String.format("%s has died. Sorry!", name));
                this.cancel();
                throw new RuntimeException();
            }
            listener.emit(String.format("%s is getting hungrier. (%d).\n", name, hunger));
        }
    };
    // #endregion

    // #region Constructors
    public Pet(String name, PetListener listener) {
        this.name = name;
        this.listener = listener;
        listener.emit(String.format("Your pet " + name + " has been born."));
        timer.scheduleAtFixedRate(hungerTask, 15000, 15000);
    }

    public Pet(PetListener listener) {
        this("Fido", listener);
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
