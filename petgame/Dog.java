package petgame;

public class Dog extends Pet {
    public static Dog withName(String name, PetListener listener) { return (name.isEmpty() || name == null) ? new Dog(listener) : new Dog(name, listener);}
    public Dog(PetListener listener) {
        super("Dave", listener);
    }

    public Dog(String name, PetListener listener) {
        super(name, listener);
    }
    @Override
    public void play() throws DeadPetException{
        super.play();
        listener.emit(String.format("Dog is fetching teh stick."));
    }
}
