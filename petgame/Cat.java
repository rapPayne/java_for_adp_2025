package petgame;

public class Cat extends Pet {
    public static Cat withName(String name) { return (name.isEmpty() || name == null) ? new Cat() : new Cat(name);}
    
    public Cat() {
        super("Dave");
    }
    public Cat(String name) {
        super(name);
    }
    @Override
    public void play() {
        happiness++;
        cleanliness--;
        hunger++;
        System.out.println("Cat is kinda ignoring you.");
    }
}
