package petgame;

public class Dog extends Pet {
    public static Dog withName(String name) { return (name.isEmpty() || name == null) ? new Dog() : new Dog(name);}
    public Dog() {
        super("Dave");
    }

    public Dog(String name) {
        super(name);
    }
    @Override
    public void play() {
        super.play();
        System.out.println("Dog is fetching teh stick.");
    }
}
