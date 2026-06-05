package PractisesExercises;

class Animal {

    String type;

    // Parent constructor
    Animal(String type) {
        this.type = type;
        System.out.println("PractisesExercises.Animal constructor called");
    }

    void display() {
        System.out.println("PractisesExercises.Animal type: " + type);
    }
}

