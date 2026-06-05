package PractisesExercises;

class Dog extends Animal {

    String breed;

    // Child constructor
    Dog(String type, String breed) {

        // calling parent constructor
        super(type);

        this.breed = breed;
        System.out.println("PractisesExercises.Dog constructor called");
    }

    void showDetails() {

        // calling parent method
        super.display();

        System.out.println("PractisesExercises.Dog breed: " + breed);
    }
}

public class MainDog {
    public static void main(String[] args) {

        Dog d = new Dog("Pet PractisesExercises.Animal", "Labrador");

        d.showDetails();
    }
}