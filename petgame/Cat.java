package petgame;

public class Cat extends Pet {
    public static Cat withName(String name, PetListener listener) {
        return (name.isEmpty() || name == null) ? new Cat(listener) : new Cat(name, listener);
    }

    public Cat(PetListener listener) {
        super("Dave", listener);
    }

    public Cat(String name, PetListener listener) {
        super(name, listener);
    }

    @Override
    public void play() throws DeadPetException {
        super.play();
        listener.emit(String.format("Cat is kinda ignoring you."));
    }
}
